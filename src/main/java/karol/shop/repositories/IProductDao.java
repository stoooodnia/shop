package karol.shop.repositories;

import karol.shop.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductDao extends JpaRepository<Product, Long> {
        void updateQuantity(Integer quantity, Long productId);

}
