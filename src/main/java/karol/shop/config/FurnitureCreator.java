package karol.shop.config;

import karol.shop.domain.FurniturePiece;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// ten config mi nie działą do thymeleafa nies sugerij się
@Configuration
public class FurnitureCreator {
    @Bean
    FurniturePiece createBean() {
        return new FurniturePiece("Chair", 101, "wood");
    }



}
