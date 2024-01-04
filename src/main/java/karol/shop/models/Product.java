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
    private Long productid;
    private String modelid;
    private String title;
    private String photourl;
    private Double price;
    private Double deliveryprice;
    private String description;
    private Integer quantity;
    private String details;

    public Product() {}
    public Product(Long productId, String modelId, String title, String photoUrl, Double price, Double deliveryPrice, String description, Integer quantity, String details) {
        this.productid = productId;
        this.modelid = modelId;
        this.title = title;
        this.photourl = photoUrl;
        this.price = price;
        this.deliveryprice = deliveryPrice;
        this.description = description;
        this.quantity = quantity;
        this.details = details;
    }
}



