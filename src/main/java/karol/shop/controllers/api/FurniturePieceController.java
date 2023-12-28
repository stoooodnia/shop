package karol.shop.controllers.api;

import karol.shop.domain.FurniturePiece;
import karol.shop.service.FurnitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/furniture")
public class FurniturePieceController {
    private final FurnitureService furnitureService;
    // dajemy personalmanager a nie implementacja interfejsu czyli personalmanagerimpl

    //dajemy tutaj response entity
    @Autowired
    public FurniturePieceController(FurnitureService furnitureService){
        this.furnitureService = furnitureService;
    }

    @GetMapping
    public ResponseEntity<List<FurniturePiece>> getFurniturePieces(){
       return ResponseEntity.ok(furnitureService.listAll());

    }
    @GetMapping("{id}")
    public ResponseEntity<FurniturePiece> getFurniturePiece(@PathVariable String id){
        return ResponseEntity.ok(furnitureService.getFurniturePiece(id));
    }

    @PostMapping
    public ResponseEntity<List<FurniturePiece>> registerNewFurniturePiece(@RequestBody FurniturePiece piece){
       return ResponseEntity.ok(furnitureService.addNewFurniturePiece(piece));
    }

    @DeleteMapping("{piece}")
    public ResponseEntity.BodyBuilder deleteFurniturePiece(@PathVariable String id){
//        furnitureService.deleteFurniturePiece(id);
        try{
            furnitureService.deleteFurniturePiece(id);
        }
        catch (Exception e){
            System.out.println("Nie ma takiego mebla");
        }
        finally {
            return ResponseEntity.ok();
        }

    }
//
//    @PutMapping("{id}")
//    public void updateFurniturePiece(@PathVariable String id, @RequestBody FurniturePiece piece){
////        furnitureService.changeFurniture(id,
//    }
//
//











}
