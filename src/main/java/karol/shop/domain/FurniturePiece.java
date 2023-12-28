package karol.shop.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
//time

public class FurniturePiece {
    private String id;
//     UUID.randomUUID(); - servis powinien nadawac, a normalnie przez baze danych


    @Size(min = 2, max = 30)
    // json property do rest api
    @JsonProperty("name")
    private String name;

    private boolean assembled;

      // custom min message
    @Min(value = 1, message = "Price should not be less than 0")
    @JsonProperty("price")
    private int price;
    @JsonProperty("kategory")
    private String kategory;

    public FurniturePiece(String name, int price, String kategory) {
        this.name = name;
        this.price = price;
        this.kategory = kategory;
    }
    public FurniturePiece(String id, String name, int price, String kategory) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.kategory = kategory;
    }
    public FurniturePiece() {}
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getPrice() {
          return price;
     }
    public String getCategory() {
          return kategory;
     }
    public void setCategory(String kategory) {
           this.kategory = kategory;
      }
    public void setName(String name) {
            this.name = name;
       }
    public void setPrice(int price) {
             this.price = price;
        }
    public void setId(String id) {
              this.id = id;
         }

}



