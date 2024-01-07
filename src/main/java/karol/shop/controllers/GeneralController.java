package karol.shop.controllers;

import karol.shop.models.Product;
import karol.shop.models.Review;
import karol.shop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;

@Controller
public class GeneralController {
    private final ProductRepository ProductRepository;
    GeneralController(ProductRepository ProductRepository) {
        this.ProductRepository = ProductRepository;
    }

    @GetMapping("/")
    public String shop(Model model) {
        model.addAttribute("allProducts", ProductRepository.getAll());

        return "pages/general";
    }

    @GetMapping("/products/{id}")
    public String productDetail(Model model, @PathVariable("id") String id){
        long productId = Long.parseLong(id);
        System.out.println("gwiazdki: " + ProductRepository.getProductById(productId).getAverageRating());
        model.addAttribute("product", ProductRepository.getProductById(productId));
        return "pages/product-details";
    }

    @GetMapping("/products/{id}/reviews")
    public String productReviews(Model model, @PathVariable("id") String id){
        long productId = Long.parseLong(id);
//        System.out.println("product id: " + productId);
        model.addAttribute("product", ProductRepository.getProductById(productId));
        model.addAttribute("AllReviews", ProductRepository.getReviewsOf(productId));
        return "pages/product-reviews";
    }

    @GetMapping("/products/{id}/reviews/add")
    public String showAddReviewForm(Model model, @PathVariable("id") Long productId) {
        Product product = ProductRepository.getProductById(productId);
        if (product != null) {
            model.addAttribute("product", product);
            model.addAttribute("review", new Review());
            return "pages/add-review-form";
        } else {
            // Obsługa przypadku, gdy produkt o danym ID nie istnieje
            return "redirect:/";
        }
    }
    @PostMapping("/products/{productId}/add-review")
    public String addReview(@PathVariable Long productId, @ModelAttribute Review review) {
        Product product = ProductRepository.getProductById(productId);
        if (product != null) {
            // Dodanie opinii do produktu
            review.setProductId(product.getProductId());
            review.setDate(LocalDate.now().toString());
            ProductRepository.addReview(review);
            ProductRepository.updateAverageRating(productId);
            return "redirect:/";
        } else {
            // Obsługa przypadku, gdy produkt o danym ID nie istnieje
            return "redirect:/";
        }
    }
}
