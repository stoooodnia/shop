package karol.shop.repositories;

import karol.shop.models.Product;
import karol.shop.models.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;

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
        return null;
    }

    @Override
    public void addReview(Review review) {
        ReviewDao.save(review);
    }

    @Override
    public Integer getAverageRatingOf(Long productId) {
        // Stream API
        return ReviewDao.findByProductId(productId).stream()
                .reduce(0, (sum, review) -> sum + review.getRating(), Integer::sum) / ReviewDao.findByProductId(productId).size();

    }

    @Override
    public void changeQuantity(Integer quantity, Long productId) {
        ProductDao.updateQuantity(quantity, productId);
    }

    @Override
    public void addProduct(Product product ) {
        ProductDao.add(product);
    }

    @Override
    public void deleteReview() {

    }

    @Override
    public void editReview() {

    }

    @Override
    public void editComment() {

    }


}
