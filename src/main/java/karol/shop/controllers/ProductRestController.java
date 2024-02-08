package karol.shop.controllers;

import jakarta.transaction.Transactional;
import karol.shop.models.Product;
import karol.shop.models.Review;
import karol.shop.services.CartService;
import karol.shop.services.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

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

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable("id") Long id) {
        Product product = generalService.getProductById(id);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("product not found", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<String> editProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        product.setProductId(id);
        generalService.editProduct(product);
        return new ResponseEntity<>("Product updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long id) {
        generalService.deleteProduct(id);
        return new ResponseEntity<>("Product deleted successfully", HttpStatus.OK);
    }


    @PostMapping("/add")
    public ResponseEntity<String> addProduct(@RequestBody Product product) {
        generalService.addProduct(product);
        return new ResponseEntity<>("Product added successfully", HttpStatus.CREATED);
    }

    @GetMapping("/{id}/reviews")
    public ResponseEntity<ArrayList<Review>> getProductReviews(@PathVariable("id") Long id) {
        ArrayList<Review> reviews = generalService.getReviewsOf(id);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @PostMapping("/{id}/reviews/add")
    public ResponseEntity<String> addReview(@PathVariable("id") Long productId, @RequestBody Review review) {
        Review newReview = generalService.addReview(productId, review);
        if(newReview != null) {
            return new ResponseEntity<>("Review added successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/rating")
    public ResponseEntity<Double> getAverageRating(@PathVariable("id") Long id) {
        double rating = generalService.getAverageRatingOf(id);
        return new ResponseEntity<>(rating, HttpStatus.OK);
    }

    @DeleteMapping("/{id}/reviews/delete/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable("id") Long productId, @PathVariable("reviewId") Long reviewId) {
        generalService.deleteReview(reviewId);
        generalService.updateAverageRating(productId);
        return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
    }
    @PutMapping("/{id}/reviews/edit/{reviewId}")
    public ResponseEntity<String> editReview(@PathVariable("id") Long productId, @PathVariable("reviewId") Long reviewId, @RequestBody Review review) {
        Review newReview = generalService.editReview(productId, reviewId, review);
        if (newReview != null) {
            return new ResponseEntity<>("Review edited successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }
    }
    @PatchMapping("/{id}/reviews/edit/{reviewId}")
    public ResponseEntity<String> editReview(@PathVariable("id") Long productId, @PathVariable("reviewId") Long reviewId, @RequestBody Map<String, String> reviewMap) {
        Review review = generalService.getReviewById(reviewId);
        if(review == null) {
            return new ResponseEntity<>("Review not found", HttpStatus.NOT_FOUND);
        }
        if(reviewMap.get("rating") != null) {
            review.setRating(Integer.parseInt(reviewMap.get("rating")));
        }
        if(reviewMap.get("content") != null) {
            review.setContent(reviewMap.get("content"));
        }
        if(reviewMap.get("author") != null) {
            review.setAuthor(reviewMap.get("author"));
        }
        Review newReview = generalService.editReview(productId, reviewId, review);
        if (newReview != null) {
            return new ResponseEntity<>("Review edited successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/model/{model}")
    public ResponseEntity<Object> getProductsByModel(@PathVariable("model") String modelString,
                                                            @RequestParam String sortOption,
                                                            @RequestParam String sortDirection) {

        if (!generalService.getAvailableModels().contains(modelString) && !modelString.equals("all")) {
            return new ResponseEntity<>("Model doesn't exist", HttpStatus.NOT_FOUND);
        }

        ArrayList<Product> products = switch (sortOption) {
            case "price" -> generalService.getProductsByModelSortedByPrice(modelString, sortDirection);
            case "rating" -> generalService.getProductsByModelSortedByRating(modelString, sortDirection);
            case "deliveryPrice" -> generalService.getProductsByModelSortedByDeliveryPrice(modelString, sortDirection);
            default -> generalService.getProductsByModel(modelString);
        };
        return new ResponseEntity<>(products, HttpStatus.OK);
    }




}
