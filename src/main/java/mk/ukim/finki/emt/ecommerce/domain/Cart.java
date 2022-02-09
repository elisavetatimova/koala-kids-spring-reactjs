package mk.ukim.finki.emt.ecommerce.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Cart entity.
 * It keeps carts coming from the customers in the basket.
 */
@Data
@Entity
@Table(name = "carts")
public class Cart extends Orderable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "carts_seq")
    @SequenceGenerator(name = "carts_seq", sequenceName = "carts_seq", initialValue = 6, allocationSize = 1)
    private Long id;


}
