package karol.shop.controllers;

import karol.shop.models.Product;
import karol.shop.models.Review;
import karol.shop.services.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/admin/products")
public class AdminProductRestController {

    private final GeneralService generalService;

    @Autowired
    public AdminProductRestController(GeneralService generalService) {
        this.generalService = generalService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = generalService.getAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) {
        Product product = generalService.getProductById(id);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<String> addProduct(@RequestBody Product product) {
        generalService.addProduct(product);
        return new ResponseEntity<>("Product added successfully", HttpStatus.CREATED);
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

    @GetMapping("/{id}/reviews")
    public ResponseEntity<List<Review>> getProductReviews(@PathVariable("id") Long id) {
        List<Review> reviews = generalService.getReviewsOf(id);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @PostMapping("/{id}/reviews/add")
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

    @PutMapping("/{id}/reviews/edit/{reviewId}")
    public ResponseEntity<String> editReview(@PathVariable("id") Long productId, @PathVariable("reviewId") Long reviewId, @RequestBody Review review) {
        Product product = generalService.getProductById(productId);
        if (product != null) {
            review.setReviewId(reviewId);
            review.setProductId(productId);
            review.setDate(LocalDate.now().toString());
            generalService.editReview(review);
            generalService.updateAverageRating(productId);
            return new ResponseEntity<>("Review edited successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}/reviews/delete/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable("id") Long productId, @PathVariable("reviewId") Long reviewId) {
        generalService.deleteReview(reviewId);
        generalService.updateAverageRating(productId);
        return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
    }
}
