package karol.shop.repositories;

import karol.shop.models.Product;
import karol.shop.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IProductDao extends JpaRepository<Product, Long> {
        @Modifying
        @Query("UPDATE Product p SET p.quantity = :quantity WHERE p.productId = :product_id")
        void updateQuantity(int quantity, long product_id);

        @Query("SELECT r FROM Review r JOIN r.product p WHERE p.productId = :productId")
        List<Review> findReviewsByProductId(@Param("productId") Long productId);
}
