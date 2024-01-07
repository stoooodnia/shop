package karol.shop.repositories;

import karol.shop.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface IProductDao extends JpaRepository<Product, Long> {
        @Modifying
        @Query("UPDATE Product p SET p.quantity = :quantity WHERE p.productId = :product_id")
        void updateQuantity(int quantity, long product_id);
}
