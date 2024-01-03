package karol.shop.repositories;

import karol.shop.models.Product;

public interface IProductDao {

        Product get(String productId);
        void add(Product product);
        void delete(String productId);
        void update(Product product);

}
