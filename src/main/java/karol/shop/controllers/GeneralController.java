package karol.shop.controllers;

import karol.shop.models.Product;
import karol.shop.models.Review;
import karol.shop.services.CartService;
import karol.shop.services.GeneralService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;

@Controller
public class GeneralController {
    private final GeneralService generalService;
    private final CartService cartService;
    GeneralController(GeneralService generalService, CartService cartService) {
        this.generalService = generalService;
        this.cartService = cartService;
    }

    @GetMapping("/")
    public String shop(Model model) {

        String error = (String) model.getAttribute("errorMessage");
        if (error != null) {
            model.addAttribute("errorMessage", error);
        }

        model.addAttribute("allProducts", generalService.getAll());
        model.addAttribute("cart", cartService.getCart());
        System.out.println("cart price: " + cartService.getCart().getTotalPrice());

        return "pages/general";
    }

    @GetMapping("/products/{id}")
    public String productDetail(Model model, @PathVariable("id") String id){
        long productId = Long.parseLong(id);
        model.addAttribute("product", generalService.getProductById(productId));
        return "pages/product-details";
    }

    @GetMapping("/products/add")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "pages/product-add-form";
    }

    @PostMapping("/products/add")
    public String addProduct(@ModelAttribute Product product) {
        generalService.addProduct(product);
        return "redirect:/";
    }

    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable("id") String id) {
        long productId = Long.parseLong(id);
        generalService.deleteProduct(productId);
        return "redirect:/";
    }

    @GetMapping("/products/edit/{id}")
    public String editProduct(Model model, @PathVariable("id") String id) {
        long productId = Long.parseLong(id);
        model.addAttribute("product", generalService.getProductById(productId));
        return "pages/product-edit-form";
    }

    @PostMapping("/products/edit/{id}")
    public String editProduct(@ModelAttribute Product product, @PathVariable("id") String id) {
        long productId = Long.parseLong(id);
        product.setProductId(productId);
        generalService.editProduct(product);
        return "redirect:/";
    }
    @GetMapping("/products/{id}/reviews")
    public String productReviews(Model model, @PathVariable("id") String id){
        long productId = Long.parseLong(id);
//        System.out.println("product id: " + productId);
        model.addAttribute("product", generalService.getProductById(productId));
        model.addAttribute("AllReviews", generalService.getReviewsOf(productId));
        return "pages/product-reviews";
    }

    @GetMapping("/products/{id}/reviews/add")
    public String showAddReviewForm(Model model, @PathVariable("id") Long productId) {
        Product product = generalService.getProductById(productId);
        if (product != null) {
            model.addAttribute("product", product);
            model.addAttribute("review", new Review());
            return "pages/add-review-form";
        } else {
            return "redirect:/";
        }
    }
    @PostMapping("/products/{id}/reviews/add")
    public String addReview(@PathVariable("id") Long productId, @ModelAttribute Review review) {
        Product product = generalService.getProductById(productId);
        if (product != null) {
            // Dodanie opinii do produktu
            review.setProductId(product.getProductId());
            review.setDate(LocalDate.now().toString());
            generalService.addReview(review);
            generalService.updateAverageRating(productId);
            return "redirect:/";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/products/{id}/reviews/edit/{reviewId}")
    public String editReview(Model model, @PathVariable("id") String id, @PathVariable("reviewId") String reviewId) {
        long productId = Long.parseLong(id);
        long reviewIdParsed = Long.parseLong(reviewId);
        model.addAttribute("product", generalService.getProductById(productId));
        model.addAttribute("review", generalService.getReviewById(reviewIdParsed));
        return "pages/edit-review-form";
    }

    @PostMapping("/products/{id}/reviews/edit/{reviewId}")
    public String editReview(@ModelAttribute Review review, @PathVariable("id") String id, @PathVariable("reviewId") String reviewId) {
        long productId = Long.parseLong(id);
        long reviewIdParsed = Long.parseLong(reviewId);
        review.setReviewId(reviewIdParsed);
        review.setProductId(productId);
        review.setDate(LocalDate.now().toString());
        generalService.editReview(review);
        generalService.updateAverageRating(productId);
        return "redirect:/";
    }

    @GetMapping("/products/{id}/reviews/delete/{reviewId}")
    public String deleteReview(@PathVariable("id") String id, @PathVariable("reviewId") String reviewId) {
        long productId = Long.parseLong(id);
        long reviewIdParsed = Long.parseLong(reviewId);
        generalService.deleteReview(reviewIdParsed);
        generalService.updateAverageRating(productId);
        return "redirect:/";
    }

}
