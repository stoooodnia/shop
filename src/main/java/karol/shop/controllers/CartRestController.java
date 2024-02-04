package karol.shop.controllers;

import karol.shop.models.DeliveryForm;
import karol.shop.services.CartService;
import karol.shop.services.GeneralService;
import karol.shop.services.OrderSummaryPdfGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
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

    @PostMapping("/add/{id}")
    public ResponseEntity<String> addToCart(@PathVariable("id") Long id) {
        cartService.addToCart(id);
        return new ResponseEntity<>("Product added to cart successfully", HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateCart(@PathVariable("id") Long id, @RequestParam("quantity") int quantity) {
        try {
            cartService.updateCart(id, quantity);
            return new ResponseEntity<>("Cart updated successfully", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> removeFromCart(@PathVariable("id") Long id) {
        cartService.removeFromCart(id);
        return new ResponseEntity<>("Product removed from cart successfully", HttpStatus.OK);
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
