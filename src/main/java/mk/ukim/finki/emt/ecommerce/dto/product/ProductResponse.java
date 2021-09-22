package mk.ukim.finki.emt.ecommerce.dto.product;

import mk.ukim.finki.emt.ecommerce.dto.review.ReviewResponse;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ProductResponse {
    private Long id;
    private String title;
    private String producer;
    private Integer year;
    private String country;
    private String productGender;
    private String productTopNotes;
    private String productMiddleNotes;
    private String productBaseNotes;
    private String description;
    private String filename;
    private Integer price;
    private String volume;
    private String type;
    private Double productRating;
    private List<ReviewResponse> reviews;
    private MultipartFile file;
}
