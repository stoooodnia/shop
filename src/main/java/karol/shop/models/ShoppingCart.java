package karol.shop.models;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class ShoppingCart {
    private ArrayList<Product> cart;
    private double totalPrice;

    public ShoppingCart() {
        cart = new ArrayList<>();
        totalPrice = 0;
    }

    public void addProduct(Product product) {
        cart.add(product);
        totalPrice += product.getPrice();
    }

    public void removeProduct(Product product) {
        cart.remove(product);
        totalPrice -= product.getPrice();
    }

    public boolean isEmpty() {
        return cart.isEmpty();
    }

    public double getTotal() {
        return totalPrice;
    }

}
