package karol.shop.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import karol.shop.models.DeliveryForm;
import karol.shop.services.CartService;
import karol.shop.services.GeneralService;
import karol.shop.services.OrderSummaryPdfGenerator;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CartController {
    private final GeneralService generalService;
    private final CartService cartService;
    private final OrderSummaryPdfGenerator orderSummaryPdfGenerator;
    CartController(GeneralService generalService, CartService cartService, OrderSummaryPdfGenerator orderSummaryPdfGenerator) {
        this.generalService = generalService;
        this.cartService = cartService;
        this.orderSummaryPdfGenerator = new OrderSummaryPdfGenerator();
    }

    @GetMapping("/cart/add/{id}")
    public String addToCart(@PathVariable("id") String id){
        long productId = Long.parseLong(id);
        cartService.addToCart(productId);
        return "redirect:/";
    }

    @PostMapping("/cart/update/{id}")
    public String updateCart(@PathVariable("id") String id, HttpServletRequest request, RedirectAttributes redirectAttributes){
        long productId = Long.parseLong(id);
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        try {
            cartService.updateCart(productId, quantity);
            return "redirect:/";
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/";
        }
    }
    @GetMapping("/cart/remove/{id}")
    public String removeFromCart(@PathVariable("id") String id){
        long productId = Long.parseLong(id);
        cartService.removeFromCart(productId);
        return "redirect:/";
    }

    @GetMapping("/cart/checkout")
    public String checkout(Model model){

        String error = (String) model.getAttribute("errorMessage");
        if (error != null) {
            model.addAttribute("errorMessage", error);
        }

        model.addAttribute("cart", cartService.getCart());
        model.addAttribute("deliveryForm", cartService.getDeliveryForm());
        return "pages/checkout";
    }

    @PostMapping("/cart/checkout")
    public String processCheckout(RedirectAttributes redirectAttributes, Model model, @Valid @ModelAttribute("deliveryForm") DeliveryForm deliveryForm, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("cart", cartService.getCart());
            return "pages/checkout";
        }
        try {
            byte[] pdfBytes = orderSummaryPdfGenerator.generateOrderSummary(
                    cartService.getCart().getCart(),
                    cartService.getCart().getTotalPrice(),
                    cartService.getCart().getTotalPriceWithDelivery(),
                    deliveryForm
            );

            redirectAttributes.addFlashAttribute("orderSummaryPdf", pdfBytes);
            return "redirect:/cart/order-success";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error during PDF generation: " + e.getMessage());
            return "redirect:/cart/checkout";
        }
    }

    @GetMapping("/cart/order-success")
    public String orderSuccess(Model model) {
        byte[] pdfBytes = (byte[]) model.getAttribute("orderSummaryPdf");
        if (pdfBytes != null) {
         System.out.println("PDF istnieje");
        }

        cartService.setSummaryPdf(pdfBytes);
        cartService.clearCart();

        return "pages/order-success";
    }

    @GetMapping("/cart/download-summary")
    public ResponseEntity<byte[]> downloadOrderSummaryPdf(Model model) {
        byte[] pdfBytes = cartService.getSummaryPdf();

        if (pdfBytes != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(ContentDisposition.builder("inline").filename("order-summary.pdf").build());

            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
        } else {
            System.out.println("PDF nie istnieje");

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}





