package karol.shop.repositories;

import karol.shop.models.Product;
import karol.shop.models.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository implements IProductRepository{
    private IProductDao ProductDao;
    private IReviewDao ReviewDao;

    @Autowired
    public ProductRepository(IProductDao ProductDao, IReviewDao ReviewDao) {
        this.ProductDao = ProductDao;
        this.ReviewDao = ReviewDao;
    }

    @Override
    public ArrayList<Product> findAllProducts() {
        ArrayList<Product> products = (ArrayList<Product>) ProductDao.findAll();
        return products;
        // Przy dodawaniu opinii średnia opinia się
        // zaktualizuje więc nie trzeba jej będzie ciągle dodawać do modelu
    }

    @Override
    public void addReview(Review review) {
        ReviewDao.save(review);
    }

    @Override
    public ArrayList<Review> findReviewsByProductId(long product_id) {
        return ReviewDao.findByProductId(product_id);
    }

    @Override
    public void changeQuantityOfProduct(int quantity, long product_id) {
        ProductDao.updateQuantity(quantity, product_id);
    }

    @Override
    public void addProduct(Product product ) {
        ProductDao.save(product);
    }

    @Override
    public void deleteProduct(long productId) {
        ProductDao.deleteById(productId);
    }
    @Override
    public void updateProduct(Product product) {
        ProductDao.save(product);
    }

    @Override
    public Product getProductById(long product_id) {
        return ProductDao.findById(product_id).orElse(null);
    }

    @Override
    public void deleteReview(long reviewId) {
        ReviewDao.deleteById(reviewId);
    }

    @Override
    public void editReview(Review review) {
        ReviewDao.save(review); // TODO: to dziala przekornie, sprawdzic czy dziala
    }

    @Override
    public ArrayList<Review> getReviewsOf(long product_id) {
        return ReviewDao.findByProductId(product_id);
    }

    @Override
    public Object getReviewById(long reviewId) { return ReviewDao.findById(reviewId); }

}
