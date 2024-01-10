package karol.shop.controllers;

import jakarta.servlet.http.HttpServletRequest;
import karol.shop.services.CartService;
import karol.shop.services.GeneralService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

}
