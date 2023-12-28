package karol.shop.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product
{
    private String productId;
    private String title;
    private String photoUrl;
    private Double price;
    private Double deliveryPrice;
    private String description;
    private Integer quantity;
    private String details;

    public Product(String productId, String title, String photoUrl, Double price, Double deliveryPrice, String description, Integer quantity, String details) {
        this.productId = productId;
        this.title = title;
        this.photoUrl = photoUrl;
        this.price = price;
        this.deliveryPrice = deliveryPrice;
        this.description = description;
        this.quantity = quantity;
        this.details = details;
    }
}



