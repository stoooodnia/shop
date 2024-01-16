package karol.shop.repositories;

import karol.shop.models.Product;
import karol.shop.models.Review;

import java.util.ArrayList;

public interface IProductRepository {
    ArrayList<Product> findAllProducts(); // zwraca wszystkie produkty
    void addReview(Review review); // dodaje recenzje do produktu
    ArrayList<Review> findReviewsByProductId(long product_id); // zwraca srednia ocen produktu
    void changeQuantity(int quantity, long product_id); // zmienia ilosc produktu
    // admin
    void addProduct(Product product); // dodaje produkt
    void deleteReview(long review_id); // usuwa recenzje
    void editReview(Review review); // edytuje recenzje czyli tez komentarz
    Product getProductById(long product_id); // zwraca produkt o podanym id
    ArrayList<Review> getReviewsOf(long product_id); // zwraca wszystkie recenzje produktu
    void updateProduct(Product product); // aktualizuje produkt

    void deleteProduct(long productId);


    // po rozwinięciu szczegółów mamy dwie kategorie, szczegóły produktu - np. dostawy i opinie.
    // aplikacja powinna umozliwiac pokazanie modeli produktów jakie mamy na stanie
}
