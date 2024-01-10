package karol.shop.services;

import karol.shop.models.Product;
import karol.shop.models.ShoppingCart;
import karol.shop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CartService {

    private final ProductRepository productRepository;
    private final ShoppingCart shoppingCart;

    @Autowired
    public CartService(ProductRepository productRepository, ShoppingCart shoppingCart) {
        this.productRepository = productRepository;
        this.shoppingCart = shoppingCart;
    }


    public ShoppingCart getCart() {
        return shoppingCart;
    }

    public void addToCart(long productId) {
        Product product = productRepository.getProductById(productId);
        // check if product is already in cart to prevent duplicates
        if(shoppingCart.contains(productId)) {
            shoppingCart.findProduct(productId).setQuantity(product.getQuantity() + 1);
        } else {
            product.setQuantity(1);
            shoppingCart.addProduct(product);
        }
    }


}
