package karol.shop.service;

import karol.shop.domain.FurniturePiece;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class FurnitureService {
    private final List<FurniturePiece>  furniture = Collections.synchronizedList(new ArrayList<>()); // synchronized ze jedno za jedyn razem
    //
    public List<FurniturePiece> getFurniturePieces() {
        return furniture;
    }
    public List<FurniturePiece> listAll() {
        return furniture;
    }

    public List<FurniturePiece> addNewFurniturePiece(FurniturePiece piece) {
        System.out.println("st" + piece);

        FurniturePiece newFurniturePiece = new FurniturePiece(
                UUID.randomUUID().toString(),
             piece.getName(),
             piece.getPrice(),
             piece.getCategory());


        System.out.println(newFurniturePiece);
        System.out.println(piece);
        System.out.println(furniture);
        furniture.add(newFurniturePiece);
        return furniture;
    }

    public FurniturePiece getFurniturePiece(String id) {
        System.out.println(id);
        for (FurniturePiece furniture : furniture) {
            if (furniture.getId().equals(id)) {
                System.out.println(furniture.getName());
                return furniture;
            }
        }

        return null;
    }


    public boolean deleteFurniturePiece(String id) {

        FurniturePiece furnitureToDelete = null;

        for (FurniturePiece furniture : furniture) {
            if (furniture.getId().equals(id)) {
                furnitureToDelete = furniture;
                break;
            }
        }
        if (furnitureToDelete != null) {
            furniture.remove(furnitureToDelete);
            return true;
        }
        return false;
    }
    public boolean changeFurniture(String id,FurniturePiece piece) {
        System.out.println("st" + piece.getCategory() +" " + piece.getName()  +" " +piece.getPrice());
        for (FurniturePiece furniture : furniture) {
            System.out.println("id" + furniture.getId());
            System.out.println("id" + id);
            if (furniture.getId().equals(id)) {
                   furniture.setName(piece.getName());
                     furniture.setPrice(piece.getPrice());
                        furniture.setCategory(piece.getCategory());

               System.out.println("price" + furniture.getPrice() + " " + furniture.getName() + " " + furniture.getCategory());

                return true;
            }
        }
        return false;
    }


    public FurniturePiece getFurniturePieceByID(String id){
//        System.out.println(id);
        for (FurniturePiece furniture : furniture) {
//            System.out.println(furniture.getId() + " " + id);
//            System.out.println(furniture.getId().equals(id));
            if (furniture.getId().equals(id)) {
                return furniture;
            }
        }
        return null;
    }

}
