package karol.shop.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Review {

    @Id
    @GeneratedValue(generator = "uuid")
    private Long reviewId;
    private Long productId;
    private String author;
    private String date;
    private Integer rating; // 1 to 5 stars
    private String content;

    public Review() {}
    public Review(Long reviewId, Long productId, String author, String date, Integer rating, String content) {
        this.reviewId = reviewId;
        this.productId = productId;
        this.author = author;
        this.date = date;
        this.rating = rating;
        this.content = content;
    }
}
