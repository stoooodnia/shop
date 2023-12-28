package karol.shop.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Review {
    private String productId;
    private String reviewId;
    private String author;
    private String date;
    private Integer grade; // 1 to 5 stars
    private String content;
}
