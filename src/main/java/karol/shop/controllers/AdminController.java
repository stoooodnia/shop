package karol.shop.controllers;

import org.springframework.web.bind.annotation.GetMapping;

public class AdminController {

    @GetMapping("/admin")
    public String admin() {
        return "pages/admin";
    }

    @GetMapping("/admin/add-product")
    public String addProduct() {
        return "pages/add-product";
    }

    @GetMapping("/admin/remove-product/{id}")
    public String removeProduct() {
        return "pages/remove-product";
    }



}
