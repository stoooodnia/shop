package karol.shop.repositories;

import karol.shop.models.Product;

public interface IProductDao {

        Product get(Long productId);
        void add(Product product);
        void delete(String productId);
        void update(Product product);

        void updateQuantity(Integer quantity, Long productId);

}
