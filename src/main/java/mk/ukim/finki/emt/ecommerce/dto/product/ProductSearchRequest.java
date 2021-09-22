package mk.ukim.finki.emt.ecommerce.dto.product;

import lombok.Data;

import java.util.List;

@Data
public class ProductSearchRequest {
    private List<String> producers;
    private List<String> genders;
    private List<Integer> prices;
    private boolean sortByPrice;
    private String producer;
    private String productGender;
}
