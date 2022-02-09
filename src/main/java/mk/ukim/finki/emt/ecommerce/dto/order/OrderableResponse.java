package mk.ukim.finki.emt.ecommerce.dto.order;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public abstract class OrderableResponse {
    private Long id;
    private Double totalPrice;
    private LocalDate date;
    private String firstName;
    private String lastName;
    private String city;
    private String address;
    private String email;
    private String phoneNumber;
    private Integer postIndex;
    private List<OrderItemResponse> orderItems;
}
