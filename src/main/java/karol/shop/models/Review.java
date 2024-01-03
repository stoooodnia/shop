package karol.shop.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

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
    private Integer grade; // 1 to 5 stars
    private String content;

    public Review() {}
    public Review(Long productId, Long reviewId, String author, String date, Integer grade, String content) {
        this.productId = productId;
        this.reviewId = reviewId;
        this.author = author;
        this.date = date;
        this.grade = grade;
        this.content = content;
    }
}
