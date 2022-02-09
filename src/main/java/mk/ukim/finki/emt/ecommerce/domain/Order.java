package mk.ukim.finki.emt.ecommerce.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Order entity.
 * It keeps orders coming from the customers basket.
 */
@Data
@Entity
@Table(name = "orders")
public class Order extends Orderable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_seq")
    @SequenceGenerator(name = "orders_seq", sequenceName = "orders_seq", initialValue = 6, allocationSize = 1)
    private Long id;

    @OneToOne
    private Cart cartId;

}
