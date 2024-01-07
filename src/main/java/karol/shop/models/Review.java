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
    @GeneratedValue(generator = "increment")
    private long reviewId;
    private long productId;
    private String author;
    private String date;
    private int rating; // 1 to 5 stars
    private String content;

    public Review() {}
    public Review(long productId, String author, String date, int rating, String content) {
        this.productId = productId;
        this.author = author;
        this.date = date;
        this.rating = rating;
        this.content = content;
    }
}
