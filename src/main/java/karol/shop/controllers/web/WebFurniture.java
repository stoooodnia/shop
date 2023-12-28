package karol.shop.controllers.web;

import karol.shop.domain.FurniturePiece;
import karol.shop.service.FurnitureService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WebFurniture {
    private final FurnitureService furnitureService;

    public WebFurniture(FurnitureService furnitureService) {
        this.furnitureService = furnitureService;
    }

    @GetMapping("/shop")
    public String furniture(Model model) {
        model.addAttribute("allProducts", furnitureService.listAll());
        return "products-all";
    }

    @GetMapping("/furniture/{id}")
    public String furnitureDetail(Model model, @PathVariable("id") String id){
        System.out.println("id  " + id);
        model.addAttribute("furnitureDetails", furnitureService.getFurniturePieceByID(id));
//        System.out.println(furnitureService.getFurniturePieceByID(id).name);
        return "furniture-details";
    }

    @GetMapping("/furniture/form")
    public String furnitureForm(Model model) {
        model.addAttribute("furnitureToAdd", new FurniturePiece("","", 0,""));
        return "furniture-add";
    }


    @PostMapping("/furniture")
    public String personEdit(@Valid @ModelAttribute("furnitureToAdd") FurniturePiece furnitureToAdd, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("furnitureToAdd", furnitureToAdd);
            System.out.println(bindingResult.getAllErrors());
            return "furniture-add";
        }
        furnitureService.addNewFurniturePiece(furnitureToAdd);
        model.addAttribute("allFurniture", furnitureService.listAll());
        return "products-all";
    }

    @GetMapping("/furniture/delete/{id}")
    public String personDelete(@PathVariable("id") String id, Model model) {
        if (furnitureService.deleteFurniturePiece(id)) {
            model.addAttribute("successMessage", "Operacja powiodła się");
        }else{
            model.addAttribute("errorMessage", "Operacja nie powiodła się");
        }
        model.addAttribute("allFurniture", furnitureService.listAll());
        return "redirect:/furniture";
    }

    @GetMapping("/furniture/edit/{id}")
    public String personEditForm(@PathVariable("id") String id, Model model) {
        FurniturePiece furniturePiece = furnitureService.getFurniturePieceByID(id);
        if (furniturePiece != null) {
            model.addAttribute("furnitureToEdit", furniturePiece);
            return "furniture-edit";
        } else {
            model.addAttribute("errorMessage", "Nie znaleziono mebla o id: " + id);
            model.addAttribute("allFurniture", furnitureService.listAll());
            return "products-all";
        }
    }

    @PostMapping("/furniture/edit/{id}")
    public String personEdit(@PathVariable("id") String id, @Valid @ModelAttribute("furnitureToEdit") FurniturePiece furnitureToEdit, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("furnitureToEdit", furnitureToEdit);
            System.out.println(bindingResult.getAllErrors());
            return "furniture-edit";
        }
        System.out.println("furnitureToEdit: " + id );
        furnitureService.changeFurniture(id, furnitureToEdit);
        model.addAttribute("allFurniture", furnitureService.listAll());
        return "redirect:/furniture";
    }


}