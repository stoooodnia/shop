package karol.shop.controllers;

import karol.shop.models.ModelsFilterForm;
import karol.shop.models.Product;
import karol.shop.models.Review;
import karol.shop.services.CartService;
import karol.shop.services.GeneralService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;

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

        model.addAttribute("availableModels", generalService.getAvailableModels());
        model.addAttribute("modelsFilterForm", generalService.getModelsForm());
        model.addAttribute("allProducts", generalService.getAll());
        model.addAttribute("cart", cartService.getCart());

        return "pages/general";
    }

    @GetMapping("/products/{id}")
    public String productDetail(Model model, @PathVariable("id") String id){
        long productId = Long.parseLong(id);
        model.addAttribute("product", generalService.getProductById(productId));
        return "pages/product-details";
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
    public String addReview(Model model, @PathVariable("id") Long productId) {
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

    @PostMapping("/products/model-filter")
    public String filterByModel(@ModelAttribute("modelsFilterForm") ModelsFilterForm modelsFilterForm){
        String model = modelsFilterForm.getSelectedModel();
        String sortOption = modelsFilterForm.getSelectedSortOption();
        String sortDirection = modelsFilterForm.getSelectedSortDirection();
        return "redirect:/products/model=" + model + "?sortOption=" + sortOption + "&sortDirection=" + sortDirection;
    }
    @GetMapping("/products/model={model}")
    public String productsByModel(Model model,
                                  @PathVariable("model") String modelString,
                                  @RequestParam String sortOption,
                                  @RequestParam String sortDirection){

//        if(modelString == null || modelString.equals("") || modelString.equals("all")){
//            return "redirect:/";
//        }

        switch (sortOption) {
            case "price" ->
                    model.addAttribute("allProducts", generalService.getProductsByModelSortedByPrice(modelString, sortDirection));
            case "rating" ->
                    model.addAttribute("allProducts", generalService.getProductsByModelSortedByRating(modelString, sortDirection));
            case "deliveryPrice" ->
                    model.addAttribute("allProducts", generalService.getProductsByModelSortedByDeliveryPrice(modelString, sortDirection));
            default ->
                    model.addAttribute("allProducts", generalService.getProductsByModel(modelString));
        }

        ModelsFilterForm modelsFilterForm = new ModelsFilterForm();
        modelsFilterForm.setSelectedModel(modelString);
        modelsFilterForm.setSelectedSortOption(sortOption);
        modelsFilterForm.setSelectedSortDirection(sortDirection);
        model.addAttribute("availableModels", generalService.getAvailableModels());
        model.addAttribute("modelsFilterForm", modelsFilterForm);
        model.addAttribute("cart", cartService.getCart());
        return "pages/general";
    }




}
