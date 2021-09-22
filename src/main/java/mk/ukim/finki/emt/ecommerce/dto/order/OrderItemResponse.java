package mk.ukim.finki.emt.ecommerce.dto.order;

import mk.ukim.finki.emt.ecommerce.dto.product.ProductResponse;
import lombok.Data;

@Data
public class OrderItemResponse {
    private Long id;
    private Long amount;
    private Long quantity;
    private ProductResponse product;
}
