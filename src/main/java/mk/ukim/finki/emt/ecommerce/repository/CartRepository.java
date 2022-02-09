package mk.ukim.finki.emt.ecommerce.repository;

import mk.ukim.finki.emt.ecommerce.domain.Cart;
import mk.ukim.finki.emt.ecommerce.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
}
