package mk.ukim.finki.emt.ecommerce.controller;

import mk.ukim.finki.emt.ecommerce.dto.product.ProductResponse;
import mk.ukim.finki.emt.ecommerce.dto.product.ProductSearchRequest;
import mk.ukim.finki.emt.ecommerce.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductMapper productMapper;

    /**
     * Provides all products.
     * @return
     */
    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return ResponseEntity.ok(productMapper.findAllProducts());
    }

    /**
     * Provides product by id
     * @param productId
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable("id") Long productId) {
        return ResponseEntity.ok(productMapper.findProductById(productId));
    }

    /**
     * Provides all products for given list of ids.
     * @param productsIds
     * @return
     */
    @PostMapping("/ids")
    public ResponseEntity<List<ProductResponse>> getProductsByIds(@RequestBody List<Long> productsIds) {
        return ResponseEntity.ok(productMapper.findProductsByIds(productsIds));
    }

    /**
     * Searches for product
     * @param filter
     * @return
     */
    @PostMapping("/search")
    public ResponseEntity<List<ProductResponse>> findProductsByFilterParams(@RequestBody ProductSearchRequest filter) {
        return ResponseEntity.ok(productMapper.filter(filter.getProducers(), filter.getGenders(), filter.getPrices(), filter.isSortByPrice()));
    }

    /**
     * Searches for products applicable for gender i.e. male/female
     * @param filter
     * @return
     */
    @PostMapping("/search/gender")
    public ResponseEntity<List<ProductResponse>> findByProductGender(@RequestBody ProductSearchRequest filter) {
        return ResponseEntity.ok(productMapper.findByProductGenderOrderByPriceDesc(filter.getProductGender()));
    }

    /**
     * Searches and filters products for given producer.
     * @param filter producer.
     * @return
     */
    @PostMapping("/search/producer")
    public ResponseEntity<List<ProductResponse>> findByProducer(@RequestBody ProductSearchRequest filter) {
        return ResponseEntity.ok(productMapper.findByProducerOrderByPriceDesc(filter.getProducer()));
    }
}
