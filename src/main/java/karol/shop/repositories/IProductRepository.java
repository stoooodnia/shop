package karol.shop.repositories;

import karol.shop.models.Product;
import karol.shop.models.Review;

import java.util.ArrayList;

public interface IProductRepository {
    ArrayList<Product> getAll(); // zwraca wszystkie produkty
    void addReview(Review review); // dodaje recenzje do produktu
    Integer getAverageRatingOf(Long productId); // zwraca srednia ocen produktu
    void changeQuantity(); // zmienia ilosc produktu
    // admin
    void addProduct(); // dodaje produkt
    void deleteReview(); // usuwa recenzje
    void editReview(); // edytuje recenzje
    void editComment(); // edytuje komentarz








    // po rozwinięciu szczegółów mamy dwie kategorie, szczegóły produktu - np. dostawy i opinie.
    // aplikacja powinna umozliwiac pokazanie modeli produktów jakie mamy na stanie
}
