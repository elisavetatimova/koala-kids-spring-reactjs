package mk.ukim.finki.emt.ecommerce.repository;

import mk.ukim.finki.emt.ecommerce.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByOrderByIdAsc();

    List<Product> findByProducerIn(List<String> producers);

    List<Product> findByProductGenderIn(List<String> genders);

    List<Product> findByPriceBetween(Integer startingPrice, Integer endingPrice);

    List<Product> findByProducerOrderByPriceDesc(String producers);

    List<Product> findByProductGenderOrderByPriceDesc(String productGender);

    List<Product> findByIdIn(List<Long> productsIds);
}
