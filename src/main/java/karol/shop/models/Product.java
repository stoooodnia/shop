package karol.shop.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product
{
    @Id
    @GeneratedValue(generator = "uuid")
    private long productId;
    private String modelId;
    private String title;

    private String photoUrl;
    private double price;
    private double deliveryPrice;
    private String description;
    private int quantity;
    private String details;

    public Product() {}
    public Product(long productId, String modelId, String title, String photoUrl, double price, double deliveryPrice, String description, int quantity, String details) {
        this.productId = productId;
        this.modelId = modelId;
        this.title = title;
        this.photoUrl = photoUrl;
        this.price = price;
        this.deliveryPrice = deliveryPrice;
        this.description = description;
        this.quantity = quantity;
        this.details = details;
    }
}



