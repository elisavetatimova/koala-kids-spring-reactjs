package mk.ukim.finki.emt.ecommerce.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Product entity represents a product in the webshop. It holds basic informaiton for a product.
 */
@Data
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_seq")
    @SequenceGenerator(name = "product_id_seq", sequenceName = "product_id_seq", initialValue = 109, allocationSize = 1)
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

    @OneToMany
    private List<Review> reviews;
}
