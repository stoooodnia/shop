package karol.shop.services;

import karol.shop.models.Product;
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


    public ShoppingCart getCart() {
        return shoppingCart;
    }

    public void addToCart(long productId) {
        Product product = productRepository.getProductById(productId);
        // prototype pattern
        Product newProduct = product.cloneProduct();

        // check if product is already in cart to prevent duplicates
        if(shoppingCart.contains(productId)) {
            Product temp = shoppingCart.findProduct(productId);
                    temp.setQuantity(temp.getQuantity() + 1);
        } else {
            newProduct.setQuantity(1);
            shoppingCart.addProduct(newProduct);
        }
    }

    public void updateCart(long productId, int quantity) {
        // check if there is enough quantity of product in stock
        if(quantity > productRepository.getProductById(productId).getQuantity()) {
            throw new IllegalArgumentException("Not enough quantity of product in stock");
        }
        shoppingCart.findProduct(productId).setQuantity(quantity);
        Product productInStock = productRepository.getProductById(productId);
        productInStock.setQuantity(productInStock.getQuantity() - quantity);
        productRepository.updateProduct(productInStock);
    }
}
