package mk.ukim.finki.emt.ecommerce.service;

import mk.ukim.finki.emt.ecommerce.domain.Product;

import java.util.List;

/**
 * Manages business logic related to products in the webshop.
 */
public interface ProductService {

    /**
     * Provides product by id
     * @param productId
     * @return
     */
    Product findProductById(Long productId);

    /**
     * Provides all products
     * @return
     */
    List<Product> findAllProducts();

    /**
     * Provides products for given ids.
     * @param productsId
     * @return
     */
    List<Product> findProductsByIds(List<Long> productsId);

    /**
     * Filters products for given product criterias.
     * @param producers
     * @param genders
     * @param prices
     * @param sortByPrice
     * @return
     */
    List<Product> filter(List<String> producers, List<String> genders, List<Integer> prices, boolean sortByPrice);

    /**
     * Filters products per producers and sorts them by price descending
     * @param producer producer
     * @return
     */
    List<Product> findByProducerOrderByPriceDesc(String producer);

    /**
     * Filters products per gender and orders them per price descending
     * @param productGender gender
     * @return
     */
    List<Product> findByProductGenderOrderByPriceDesc(String productGender);

    /**
     * Saves product into database.
     * @param product
     * @return
     */
    Product saveProduct(Product product);

    /**
     * Deletes product from the database.
     * @param productId product id.
     * @return
     */
    List<Product> deleteProduct(Long productId);
}
