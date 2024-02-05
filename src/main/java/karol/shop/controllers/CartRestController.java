package karol.shop.controllers;

import karol.shop.models.DeliveryForm;
import karol.shop.models.ShoppingCart;
import karol.shop.services.CartService;
import karol.shop.services.GeneralService;
import karol.shop.services.OrderSummaryPdfGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/cart")
public class CartRestController {
    private final GeneralService generalService;
    private final CartService cartService;
    private final OrderSummaryPdfGenerator orderSummaryPdfGenerator;

    @Autowired
    public CartRestController(GeneralService generalService, CartService cartService, OrderSummaryPdfGenerator orderSummaryPdfGenerator) {
        this.generalService = generalService;
        this.cartService = cartService;
        this.orderSummaryPdfGenerator = orderSummaryPdfGenerator;
    }

    @GetMapping("/get")
    public ResponseEntity<ShoppingCart> getCart() {
        return new ResponseEntity<>(cartService.getCart(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addToCart(@RequestBody Map<String, String> request) {
        Long productId = Long.valueOf(request.get("productId"));
        Long quantity = request.get("quantity") != null ? Long.valueOf(request.get("quantity")) : 1;
        cartService.addToCart(productId, quantity);
        return new ResponseEntity<>("Product added to cart successfully", HttpStatus.OK);
    }

    @PatchMapping("/updateQuantity")
    public ResponseEntity<String> updateCart(@RequestBody Map<String, String> request) {
        try {
            Long productId = Long.valueOf(request.get("productId"));
            Long quantity = Long.valueOf(request.get("newQuantity"));
            cartService.updateCart(productId, quantity);
            return new ResponseEntity<>("Cart updated successfully", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> removeFromCart(@RequestBody Map<String, String> request) {
        try {
            Long productId = Long.valueOf(request.get("productId"));
            cartService.removeFromCart(productId);
            return new ResponseEntity<>("Product removed from cart successfully", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }

    @PostMapping("/checkout")
    public ResponseEntity<byte[]> processCheckout(@RequestBody DeliveryForm deliveryForm) {
        try {
            byte[] pdfBytes = orderSummaryPdfGenerator.generateOrderSummary(
                    cartService.getCart().getCart(),
                    cartService.getCart().getTotalPrice(),
                    cartService.getCart().getTotalPriceWithDelivery(),
                    deliveryForm
            );
            cartService.setSummaryPdf(pdfBytes);
            cartService.clearCart();
            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=order-summary.pdf")
                    .body(pdfBytes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/download-summary")
    public ResponseEntity<byte[]> downloadOrderSummaryPdf() {
        byte[] pdfBytes = cartService.getSummaryPdf();
        if (pdfBytes != null) {
            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=order-summary.pdf")
                    .body(pdfBytes);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
