package karol.shop.repositories;

import karol.shop.models.Product;
import karol.shop.models.Review;

import java.util.ArrayList;

public interface IProductRepository {
    ArrayList<Product> getAll(); // zwraca wszystkie produkty
    void addReview(Review review); // dodaje recenzje do produktu
    int getAverageRatingOf(long productId); // zwraca srednia ocen produktu
    void changeQuantity(int quantity, long productId); // zmienia ilosc produktu
    // admin
    void addProduct(Product product); // dodaje produkt
    void deleteReview(long reviewId); // usuwa recenzje
    void editReview(Review review); // edytuje recenzje czyli te komentarz
    Product getProductById(long productId); // zwraca produkt o podanym id







    // po rozwinięciu szczegółów mamy dwie kategorie, szczegóły produktu - np. dostawy i opinie.
    // aplikacja powinna umozliwiac pokazanie modeli produktów jakie mamy na stanie
}
