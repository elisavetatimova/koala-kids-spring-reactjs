package mk.ukim.finki.emt.ecommerce.repository;

import mk.ukim.finki.emt.ecommerce.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByOrderByIdAsc();

    List<Order> findOrderByEmail(String email);
}
