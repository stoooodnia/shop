package karol.shop.controllers;

import karol.shop.models.Product;
import karol.shop.models.Review;
import karol.shop.services.CartService;
import karol.shop.services.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;

@RestController
@RequestMapping("/products")
public class ProductRestController {
    private final GeneralService generalService;
    private final CartService cartService;
    @Autowired
    ProductRestController(GeneralService generalService, CartService cartService) {
        this.generalService = generalService;
        this.cartService = cartService;
    }

    @GetMapping
    public ResponseEntity<ArrayList<Product>> getAllProducts() {
        ArrayList<Product> products = generalService.getAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }


    @GetMapping("/{id}/reviews")
    public ResponseEntity<ArrayList<Review>> getProductReviews(@PathVariable("id") Long id) {
        ArrayList<Review> reviews = generalService.getReviewsOf(id);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @PostMapping("/{id}/reviews")
    public ResponseEntity<String> addReview(@PathVariable("id") Long productId, @RequestBody Review review) {
        Product product = generalService.getProductById(productId);
        if (product != null) {
            review.setProductId(product.getProductId());
            review.setDate(LocalDate.now().toString());
            generalService.addReview(review);
            generalService.updateAverageRating(productId);
            return new ResponseEntity<>("Review added successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/model/{model}")
    public ResponseEntity<ArrayList<Product>> getProductsByModel(@PathVariable("model") String modelString,
                                                            @RequestParam String sortOption,
                                                            @RequestParam String sortDirection) {
        ArrayList<Product> products;
        switch (sortOption) {
            case "price":
                products = generalService.getProductsByModelSortedByPrice(modelString, sortDirection);
                break;
            case "rating":
                products = generalService.getProductsByModelSortedByRating(modelString, sortDirection);
                break;
            default:
                products = generalService.getProductsByModel(modelString);
                break;
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }




}
