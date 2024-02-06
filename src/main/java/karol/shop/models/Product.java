package karol.shop.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
    @NotBlank(message = "ModelId cannot be empty!")
    private String modelId;
    @NotBlank(message = "Title cannot be empty!")
    private String title;
    @NotBlank(message = "PhotoUrl cannot be empty!")
    private String photoUrl;
    @Positive(message = "Price must be positive!")
    private double price;
    @PositiveOrZero(message = "DeliveryPrice cannot be negative!")
    private double deliveryPrice;
    @NotBlank(message = "Description cannot be empty!")
    private String description;
    @PositiveOrZero(message = "Quantity cannot be negative!")
    private long quantity;
    @NotBlank(message = "Details cannot be empty!")
    private String details;

    @Min(value = 0, message = "Average rating must be between 0 and 5!")
    @Max(value = 5, message = "Average rating must be between 0 and 5!")
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



