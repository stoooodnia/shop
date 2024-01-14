package karol.shop.models;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class ShoppingCart {
    private ArrayList<Product> cart;

    private double totalPrice;
    private double totalPriceWithDelivery;

    public ShoppingCart() {
        cart = new ArrayList<>();
        totalPrice = 0;
    }

    public void addProduct(Product product) {
        cart.add(product);
    }

    public void removeProduct(Product product) {
        cart.remove(product);
        totalPrice -= product.getPrice();
    }
    public void calculateTotalPrice() {
        double neww = cart.stream()
                .mapToDouble(product -> product.getPrice() * product.getQuantity())
                .sum();
        setTotalPrice(Math.round(neww * 100.0) / 100.0);
    }
    public void calculateTotalPriceWithDelivery() {
        double neww = cart.stream()
                .mapToDouble(product -> (product.getPrice() + product.getDeliveryPrice()) * product.getQuantity())
                .sum();

        System.out.println("neww: " + neww);
        setTotalPriceWithDelivery(Math.round(neww * 100.0) / 100.0);

    }

    public boolean isEmpty() {
        return cart.isEmpty();
    }

    public boolean contains(Long productid) {
        return cart.stream().filter(p -> p.getProductId() == productid).findFirst().isPresent();
    }

    public Product findProduct(long productId) {
        return cart.stream()
                .filter(p -> p.getProductId() == productId)
                .findFirst()
                .orElse(null);
    }
}
