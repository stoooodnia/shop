package karol.shop.models;

public class ProductRepository {
    private IProductsDao ProductsDao;
    private IReviewsDao ReviewsDao;

    public ProductRepository(IProductsDao ProductsDao, IReviewsDao ReviewsDao) {
        this.ProductsDao = ProductsDao;
        this.ReviewsDao = ReviewsDao;
    }
}
