package karol.shop.models;

import lombok.Data;

import java.util.ArrayList;

@Data
public class ShoppingCart {
    private ArrayList<Product> products;
    private double totalPrice;

    public void addProduct(Product product) {
        products.add(product);
        totalPrice += product.getPrice();
    }

    public void removeProduct(Product product) {
        products.remove(product);
        totalPrice -= product.getPrice();
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }






}
