package karol.shop.services;

import karol.shop.models.DeliveryForm;
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

        Product productInStock = productRepository.getProductById(productId);
        productInStock.setQuantity(productInStock.getQuantity() - 1);
        productRepository.updateProduct(productInStock);

        shoppingCart.calculateTotalPrice();
        shoppingCart.calculateTotalPriceWithDelivery();
    }

    public void updateCart(long productId, int quantity) {

        Product productInCart = shoppingCart.findProduct(productId);
        Product productInStock = productRepository.getProductById(productId);
        // check if there is enough quantity of product in stock
        if(quantity > productInStock.getQuantity() + productInCart.getQuantity()) {
            throw new IllegalArgumentException("Not enough quantity of product in stock");
        }

        if (quantity == 0) {
            shoppingCart.removeProduct(productInCart);
            productInStock.setQuantity(productInStock.getQuantity() + productInCart.getQuantity());
            productRepository.updateProduct(productInStock);
            return;
        }
        else if(quantity > productInCart.getQuantity()) {
            productInStock.setQuantity(productInStock.getQuantity() + (productInCart.getQuantity() - quantity));
        } else if(quantity < productInCart.getQuantity()) {
            productInStock.setQuantity(productInStock.getQuantity() - (quantity - productInCart.getQuantity()));
        } else {
            return;
        }
        productInCart.setQuantity(quantity);
        productRepository.updateProduct(productInStock);

        System.out.println("add1  "+ shoppingCart.getTotalPrice());
        shoppingCart.calculateTotalPrice();
        shoppingCart.calculateTotalPriceWithDelivery();
        System.out.println("add2  "+ shoppingCart.getTotalPrice());
    }

    public void removeFromCart(long productId) {
        Product productInCart = shoppingCart.findProduct(productId);
        Product productInStock = productRepository.getProductById(productId);
        productInStock.setQuantity(productInStock.getQuantity() + productInCart.getQuantity());
        productRepository.updateProduct(productInStock);
        shoppingCart.removeProduct(productInCart);

        shoppingCart.calculateTotalPriceWithDelivery();
    }

    public Object getDeliveryForm() {
        return new DeliveryForm();
    }

    public void setSummaryPdf(byte[] summaryPdf) {
        shoppingCart.setSummaryPdf(summaryPdf);
    }

    public byte[] getSummaryPdf() {
        return shoppingCart.getSummaryPdf();
    }

    public void clearCart() {
        shoppingCart.getCart().clear();
        shoppingCart.setTotalPrice(0);
        shoppingCart.setTotalPriceWithDelivery(0);
    }
}
