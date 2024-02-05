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
    @GeneratedValue(generator = "increment")
    private long productId;
    private String modelId;
    private String title;
    private String photoUrl;
    private double price;
    private double deliveryPrice;
    private String description;
    private long quantity;
    private String details;

    private int averageRating; // 1 to 5 stars



    public Product() {
        this.averageRating = 0;
    }


    public Product(long productId, String modelId, String title, String photoUrl, double price, double deliveryPrice, String description, long quantity, String details) {
        this.productId = productId;
        this.modelId = modelId;
        this.title = title;
        this.photoUrl = photoUrl;
        this.price = price;
        this.deliveryPrice = deliveryPrice;
        this.description = description;
        this.quantity = quantity;
        this.details = details;
        this.averageRating = 0;
    }
    public Product(long productId, String modelId, String title, String photoUrl, double price, double deliveryPrice, String description, long quantity, String details, int averageRating) {
        this.productId = productId;
        this.modelId = modelId;
        this.title = title;
        this.photoUrl = photoUrl;
        this.price = price;
        this.deliveryPrice = deliveryPrice;
        this.description = description;
        this.quantity = quantity;
        this.details = details;
        this.averageRating = averageRating;
    }


    public Product cloneProduct() {
        return new Product(this.getProductId(), this.getModelId(), this.getTitle(), this.getPhotoUrl(), this.getPrice(), this.getDeliveryPrice(), this.getDescription(), this.getQuantity(), this.getDetails(), this.getAverageRating());
    }
}



