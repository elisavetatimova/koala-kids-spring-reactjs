package mk.ukim.finki.emt.ecommerce.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract Orderable entity, shared between thec cart and the order
 * It keeps orders coming from the customers in the basket.
 */
@Data
public abstract class Orderable {

    private Double totalPrice;
    private LocalDate date;
    private String firstName;
    private String lastName;
    private String city;
    private String address;
    private String email;
    private String phoneNumber;
    private Integer postIndex;

    @OneToMany(fetch = FetchType.EAGER)
    private List<OrderItem> orderItems;

    public Orderable() {
        this.date = LocalDate.now();
        this.orderItems = new ArrayList<>();
    }
}
