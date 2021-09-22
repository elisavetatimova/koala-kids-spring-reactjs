package mk.ukim.finki.emt.ecommerce.service;

import mk.ukim.finki.emt.ecommerce.domain.Order;

import java.util.List;
import java.util.Map;

/**
 * Manages business logic regarding orders in the webshop.
 */
public interface OrderService {

    /**
     * Provides all orders
     * @return
     */
    List<Order> findAll();

    /**
     * Provides orders by email
     * @param email
     * @return
     */
    List<Order> findOrderByEmail(String email);

    /**
     * Submits a new order
     * @param validOrder
     * @param productsId
     * @return
     */
    Order postOrder(Order validOrder, Map<Long, Long> productsId);

    /**
     * Deletes an order
     * @param orderId
     * @return
     */
    List<Order> deleteOrder(Long orderId);
}
