package karol.shop.models;

import java.util.ArrayList;

public interface IReviewsDao {

    ArrayList<Review> getAllByProductId(String productId);
    Review get(String reviewId);
    void add(Review review, String productId);
    void delete(String reviewId);



}
