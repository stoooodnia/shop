package karol.shop;

import karol.shop.domain.FurniturePiece;
import karol.shop.service.FurnitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopApplication.class, args);
	}
	@Bean
	public CommandLineRunner appSetup(@Autowired FurnitureService furnitureService){
		return args -> {
			System.out.println("CommandLineRunner started...");

			furnitureService.addNewFurniturePiece(new FurniturePiece("chair", 5, "wood"));
			furnitureService.addNewFurniturePiece(new FurniturePiece("table", 10, "wood"));
			furnitureService.addNewFurniturePiece(new FurniturePiece("bed", 15, "wood"));
		};
	}

}
