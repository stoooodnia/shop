package karol.shop.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import karol.shop.models.DeliveryForm;
import karol.shop.services.CartService;
import karol.shop.services.GeneralService;
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
    CartController(GeneralService generalService, CartService cartService) {
        this.generalService = generalService;
        this.cartService = cartService;
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
    public String processCheckout(Model model, @Valid @ModelAttribute("deliveryForm") DeliveryForm deliveryForm, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("cart", cartService.getCart());
            return "pages/checkout";
        }

        // Przetwarzanie zamówienia, np. zapis do bazy danych, itp.

        return "redirect:/cart/order-success"; // Przekierowanie po złożeniu zamówienia
    }

    @GetMapping("/cart/order-success")
    public String orderSuccess() {
        return "pages/order-success";
    }



}
