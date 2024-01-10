package karol.shop.services;

import karol.shop.models.ShoppingCart;
import karol.shop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    private final ProductRepository productRepository;
    private final ShoppingCart shoppingCart;

    @Autowired
    public CartService(ProductRepository productRepository, ShoppingCart shoppingCart) {
        this.productRepository = productRepository;
        this.shoppingCart = shoppingCart;
    }


}
