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
    public ArrayList<Product> getAll() {
        ArrayList<Product> products = (ArrayList<Product>) ProductDao.findAll();
//        products.forEach(product -> product.setAverageRating(this.getAverageRatingOf(product.getProductId())));
        return products;
        //TODO : Przy dodawaniu opinii średnia opinia się zaktualizuje więc nie trzeba jej będzie ciągle dodawać do modelu
    }

    @Override
    public void addReview(Review review) {
        ReviewDao.save(review);
    }

    @Override
    public int getAverageRatingOf(long product_id) {
        // Stream API
        return ReviewDao.findByProductId(product_id).stream()
                .reduce(0, (sum, review) -> sum + review.getRating(), Integer::sum) / (ReviewDao.findByProductId(product_id).size());

    }

    @Override
    public void changeQuantity(int quantity, long product_id) {
        ProductDao.updateQuantity(quantity, product_id);
    }

    @Override
    public void addProduct(Product product ) {
        ProductDao.save(product);
    }

    @Override
    public Product getProductById(long product_id) {
        return ProductDao.findById(product_id).orElse(null);
    }

    @Override
    public void deleteReview(long review_id) {
        ReviewDao.deleteById(review_id);
    }

    @Override
    public void editReview(Review review) {
        ReviewDao.save(review); // TODO: to dziala przekornie, sprawdzic czy dziala
    }

    @Override
    public ArrayList<Review> getReviewsOf(long product_id) {
        System.out.println("product id: " + product_id);
        return ReviewDao.findByProductId(product_id);
    }

    public void updateAverageRating(long product_id) {
        Product product = getProductById(product_id);
        product.setAverageRating(getAverageRatingOf(product_id));
        ProductDao.save(product);
    }


}
