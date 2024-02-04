package karol.shop.services;

import karol.shop.models.ModelsFilterForm;
import karol.shop.models.Product;
import karol.shop.models.Review;
import karol.shop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;

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

    public ArrayList<Product> getProductsByModel(String modelString) {
        if (modelString.equals("all")) {
            return getAll();
        }
        ArrayList<Product> products = getAll();
        ArrayList<Product> productsByModel = new ArrayList<>();
        for (Product product : products) {
            if (product.getModelId().equals(modelString)) {
                productsByModel.add(product);
            }
        }
        return productsByModel;
    }

    public ArrayList<String> getAvailableModels() {
        ArrayList<Product> products = getAll();
        ArrayList<String> models = new ArrayList<>();
        for (Product product : products) {
            if (!models.contains(product.getModelId())) {
                models.add(product.getModelId());
            }
        }
        return models;
    }

    public Object getModelsForm() {
        return new ModelsFilterForm();
    }

    public ArrayList<Product> getProductsByModelSortedByPrice(String modelString, String sortDirection) {
        ArrayList<Product> products = getProductsByModel(modelString);
        if (sortDirection.equals("asc")) {
            products.sort((p1, p2) -> (int) (p1.getPrice() - p2.getPrice()));
        } else {
            products.sort((p1, p2) -> (int) (p2.getPrice() - p1.getPrice()));
        }
        return products;
    }

    public ArrayList<Product> getProductsByModelSortedByRating(String modelString, String sortDirection) {
        ArrayList<Product> products = getProductsByModel(modelString);
        if (sortDirection.equals("asc")) {
            products.sort(Comparator.comparingInt(Product::getAverageRating)); // inaczej bo krzyczało
        } else {
            products.sort((p1, p2) -> (p2.getAverageRating() - p1.getAverageRating()));
        }
        return products;
    }
}
