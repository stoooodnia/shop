package karol.shop.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product
{
    @Id
    @GeneratedValue(generator = "uuid")
    private Long productId;
    private String modelId;
    private String title;
    private String photo;
    private Double price;
    private Double deliveryPrice;
    private String description;
    private Integer quantity;
    private String details;

    public Product() {}
    public Product(Long productId, String modelId, String title, String photoUrl, Double price, Double deliveryPrice, String description, Integer quantity, String details) {
        this.productId = productId;
        this.modelId = modelId;
        this.title = title;
        this.photo = photoUrl;
        this.price = price;
        this.deliveryPrice = deliveryPrice;
        this.description = description;
        this.quantity = quantity;
        this.details = details;
    }
}



