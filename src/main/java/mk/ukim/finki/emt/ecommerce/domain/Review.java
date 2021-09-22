package mk.ukim.finki.emt.ecommerce.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Reviews for a product.
 */
@Data
@Entity
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String author;
    private String message;
    private Integer rating;
    private LocalDate date;

    public Review() {
        this.date = LocalDate.now();
    }
}
