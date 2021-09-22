package mk.ukim.finki.emt.ecommerce.service.Impl;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.emt.ecommerce.domain.Product;
import mk.ukim.finki.emt.ecommerce.repository.ProductRepository;
import mk.ukim.finki.emt.ecommerce.repository.ReviewRepository;
import mk.ukim.finki.emt.ecommerce.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public Product findProductById(Long productId) {
        return productRepository.findById(productId).get();
    }

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAllByOrderByIdAsc();
    }

    @Override
    public List<Product> findProductsByIds(List<Long> productsId) {
        return productRepository.findByIdIn(productsId);
    }

    @Override
    public List<Product> filter(List<String> producers, List<String> genders, List<Integer> prices, boolean sortByPrice) {
        List<Product> productList = new ArrayList<>();

        if (!producers.isEmpty() || !genders.isEmpty() || !prices.isEmpty()) {
            if (!producers.isEmpty()) {
                if (!productList.isEmpty()) {
                    List<Product> productsList = new ArrayList<>();
                    for (String producer : producers) {
                        productsList.addAll(productList.stream()
                                .filter(product -> product.getProducer().equals(producer))
                                .collect(Collectors.toList()));
                    }
                    productList = productsList;
                } else {
                    productList.addAll(productRepository.findByProducerIn(producers));
                }
            }
            if (!genders.isEmpty()) {
                if (!productList.isEmpty()) {
                    List<Product> gendersList = new ArrayList<>();
                    for (String gender : genders) {
                        gendersList.addAll(productList.stream()
                                .filter(product -> product.getProductGender().equals(gender))
                                .collect(Collectors.toList()));
                    }
                    productList = gendersList;
                } else {
                    productList.addAll(productRepository.findByProductGenderIn(genders));
                }
            }
            if (!prices.isEmpty()) {
                productList = productRepository.findByPriceBetween(prices.get(0), prices.get(1));
            }
        } else {
            productList = productRepository.findAllByOrderByIdAsc();
        }
        if (sortByPrice) {
            productList.sort(Comparator.comparing(Product::getPrice));
        } else {
            productList.sort((product1, product2) -> product2.getPrice().compareTo(product1.getPrice()));
        }
        return productList;
    }

    @Override
    public List<Product> findByProducerOrderByPriceDesc(String producer) {
        return productRepository.findByProducerOrderByPriceDesc(producer);
    }

    @Override
    public List<Product> findByProductGenderOrderByPriceDesc(String productGender) {
        return productRepository.findByProductGenderOrderByPriceDesc(productGender);
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public List<Product> deleteProduct(Long productId) {
        Product product = productRepository.findById(productId).get();
        product.getReviews().forEach(review -> reviewRepository.deleteById(review.getId()));
        productRepository.delete(product);
        return productRepository.findAllByOrderByIdAsc();
    }
}
