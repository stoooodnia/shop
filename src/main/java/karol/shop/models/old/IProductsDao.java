package karol.shop.models.old;

import karol.shop.models.Product;

public interface IProductsDao {

        Product get(String productId);
        void add(Product product);
        void delete(String productId);
        void update(Product product);

}
