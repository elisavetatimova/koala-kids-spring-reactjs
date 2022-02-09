package mk.ukim.finki.emt.ecommerce.dto.cart;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Map;

@Data
public class AddLineItemRequest {

    @NotNull
    private Long cartId;

    @NotNull
    private Long productsId;

    @NotNull
    private Long quantity;
}
