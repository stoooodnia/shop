package karol.shop.controllers;

import karol.shop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GeneralController {
    private final ProductRepository ProductRepository;
    GeneralController(ProductRepository ProductRepository) {
        this.ProductRepository = ProductRepository;
    }

    @GetMapping("/general")
    public String shop(Model model) {
        model.addAttribute("allProducts", ProductRepository.getAll());
        return "pages/general";
    }
}
