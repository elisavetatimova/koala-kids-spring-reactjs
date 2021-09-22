package mk.ukim.finki.emt.ecommerce.dto.product;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ProductRequest {

    private Long id;
    private String filename;

    @NotBlank(message = "Fill in the input field")
    @Length(max = 255)
    private String title;

    @NotBlank(message = "Fill in the input field")
    @Length(max = 255)
    private String producer;

    @NotNull(message = "Fill in the input field")
    private Integer year;

    @NotBlank(message = "Fill in the input field")
    @Length(max = 255)
    private String country;

    @NotBlank(message = "Fill in the input field")
    @Length(max = 255)
    private String productGender;

    @NotBlank(message = "Fill in the input field")
    @Length(max = 255)
    private String productTopNotes;

    @NotBlank(message = "Fill in the input field")
    @Length(max = 255)
    private String productMiddleNotes;

    @NotBlank(message = "Fill in the input field")
    @Length(max = 255)
    private String productBaseNotes;

    @NotNull(message = "Fill in the input field")
    private Integer price;

    @NotBlank(message = "Fill in the input field")
    @Length(max = 255)
    private String type;
}
