package karol.shop.repositories;

import karol.shop.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface IReviewDao extends JpaRepository<Review, Long> {
    ArrayList<Review> findByProductId(Long productId);
}
