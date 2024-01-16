package karol.shop.services;

import karol.shop.models.Product;
import karol.shop.models.Review;
import karol.shop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class GeneralService {
    private final ProductRepository productRepository;

    @Autowired
    public GeneralService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ArrayList<Product> getAll() {
        return productRepository.findAllProducts();
    }

    public void addReview(Review review) {
        productRepository.addReview(review);
    }

    public int getAverageRatingOf(long productId) {
        // Stream API
        ArrayList<Review> reviews = productRepository.findReviewsByProductId(productId);
        // exception for empty list
        if (reviews.size() == 0) {
            return 0;
        }
        return reviews.stream()
                .reduce(0, (sum, review) -> sum + review.getRating(), Integer::sum) / (reviews.size());
    }

    public void changeQuantity(int quantity, long productId) {
        productRepository.changeQuantityOfProduct(quantity, productId);
    }

    public void addProduct(Product product) {
        productRepository.addProduct(product);
    }
    public void deleteProduct(long productId) { productRepository.deleteProduct(productId); }

    public void editProduct(Product product) { productRepository.updateProduct(product); }
    public void deleteReview(long reviewId) {
        productRepository.deleteReview(reviewId);
    }

    public void editReview(Review review) {
        productRepository.editReview(review);
    }

    public Product getProductById(long productId) {
        return productRepository.getProductById(productId);
    }

    public ArrayList<Review> getReviewsOf(long productId) {
        return productRepository.getReviewsOf(productId);
    }

    public void updateAverageRating(long product_id) {
        Product product = getProductById(product_id);
        product.setAverageRating(getAverageRatingOf(product_id));
        productRepository.updateProduct(product);
    }


    public Object getReviewById(long reviewId) {
        return productRepository.getReviewById(reviewId);
    }
}
