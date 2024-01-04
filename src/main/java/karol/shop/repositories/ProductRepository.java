package karol.shop.repositories;

import karol.shop.models.Product;
import karol.shop.models.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;

@Repository
public class ProductRepository implements IProductRepository{
    private IProductDao ProductsDao;
    private IReviewDao ReviewsDao;

    @Autowired
    public ProductRepository(IProductDao ProductDao, IReviewDao ReviewDao) {
        this.ProductsDao = ProductDao;
        this.ReviewsDao = ReviewDao;
    }

    @Override
    public ArrayList<Product> getAll() {
        return null;
    }

    @Override
    public void addReview(Review review) {
        ReviewsDao.save(review);
    }

    @Override
    public Integer getAverageRatingOf(Long productId) {
        // Stream API
        return ReviewsDao.findByProductId(productId).stream()
                .reduce(0, (sum, review) -> sum + review.getRating(), Integer::sum) / ReviewsDao.findByProductId(productId).size();

    }

    @Override
    public void changeQuantity() {

    }

    @Override
    public void addProduct() {

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
