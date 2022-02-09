package mk.ukim.finki.emt.ecommerce.dto.cart;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Map;

@Data
public class CartRequest {

    private Double totalPrice;

    private String firstName;

    private String lastName;

    private String city;

    private String address;

    @Email(message = "Incorrect email")
    private String email;

    private String phoneNumber;

    @NotNull(message = "Post index cannot be empty")
    @Min(value = 5, message = "Post index must contain 5 digits")
    private Integer postIndex;
}
