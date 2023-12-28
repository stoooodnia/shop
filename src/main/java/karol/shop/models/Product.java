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


}



