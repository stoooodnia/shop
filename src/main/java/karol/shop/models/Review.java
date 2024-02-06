package karol.shop.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;

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
    @NotBlank(message = "Author cannot be empty!")
    private String author;
    private String date;
    @Min(value = 1, message = "Rating must be between 1 and 5!")
    @Max(value = 5, message = "Rating must be between 1 and 5!")
    private int rating; // 1 to 5 stars
    @NotBlank(message = "Content cannot be empty!")
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
