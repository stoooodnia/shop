package karol.shop.controllers;

import karol.shop.models.Product;
import karol.shop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

    @GetMapping("/products/{id}")
    public String furnitureDetail(Model model, @PathVariable("id") String id){
        System.out.println("iddd  " + id);
        long productId = Long.parseLong(id);
        model.addAttribute("product", ProductRepository.getProductById(productId));
        return "pages/product-details";
    }
}
