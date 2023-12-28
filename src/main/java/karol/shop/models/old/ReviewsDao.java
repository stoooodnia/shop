package karol.shop.models.old;

import karol.shop.models.Review;

import java.util.ArrayList;

public class ReviewsDao implements IReviewsDao {
    @Override
    public ArrayList<Review> getAllByProductId(String productId) {
        return null;
    }

    @Override
    public Review get(String reviewId) {
        return null;
    }

    @Override
    public void add(Review review, String productId) {

    }

    @Override
    public void delete(String reviewId) {

    }
}
