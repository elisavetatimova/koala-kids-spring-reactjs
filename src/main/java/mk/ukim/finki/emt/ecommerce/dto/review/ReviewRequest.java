package mk.ukim.finki.emt.ecommerce.dto.review;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class ReviewRequest {

    private Long productId;

    @NotBlank(message = "Fill in the input field")
    private String author;

    @NotBlank(message = "Fill in the input field")
    private String message;

    @Min(value = 1, message = "Choose product rating")
    private Integer rating;
}
