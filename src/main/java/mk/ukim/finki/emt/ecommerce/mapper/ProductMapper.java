package mk.ukim.finki.emt.ecommerce.mapper;

import mk.ukim.finki.emt.ecommerce.domain.Product;
import mk.ukim.finki.emt.ecommerce.dto.product.ProductRequest;
import mk.ukim.finki.emt.ecommerce.dto.product.ProductResponse;
import mk.ukim.finki.emt.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductMapper {

    private final ModelMapper modelMapper;
    private final ProductService productService;

    private Product convertToEntity(ProductRequest productRequest) {
        return modelMapper.map(productRequest, Product.class);
    }

    ProductResponse convertToResponseDto(Product product) {
        return modelMapper.map(product, ProductResponse.class);
    }

    List<ProductResponse> convertListToResponseDto(List<Product> products) {
        return products.stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    public ProductResponse findProductById(Long product) {
        return convertToResponseDto(productService.findProductById(product));
    }

    public List<ProductResponse> findProductsByIds(List<Long> productsId) {
        return convertListToResponseDto(productService.findProductsByIds(productsId));
    }

    public List<ProductResponse> findAllProducts() {
        return convertListToResponseDto(productService.findAllProducts());
    }

    public List<ProductResponse> filter(List<String> producers, List<String> genders, List<Integer> prices, boolean sortByPrice) {
        return convertListToResponseDto(productService.filter(producers, genders, prices, sortByPrice));
    }

    public List<ProductResponse> findByProducerOrderByPriceDesc(String producer) {
        return convertListToResponseDto(productService.findByProducerOrderByPriceDesc(producer));
    }

    public List<ProductResponse> findByProductGenderOrderByPriceDesc(String productGender) {
        return convertListToResponseDto(productService.findByProductGenderOrderByPriceDesc(productGender));
    }

    public ProductResponse saveProduct(ProductRequest productRequest) {
        return convertToResponseDto(productService.saveProduct(convertToEntity(productRequest)));
    }

    public List<ProductResponse> deleteProduct(Long productId) {
        return convertListToResponseDto(productService.deleteProduct(productId));
    }
}
