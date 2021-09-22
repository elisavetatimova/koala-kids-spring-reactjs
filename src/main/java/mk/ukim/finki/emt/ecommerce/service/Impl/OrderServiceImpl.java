package mk.ukim.finki.emt.ecommerce.service.Impl;

import mk.ukim.finki.emt.ecommerce.domain.Order;
import mk.ukim.finki.emt.ecommerce.domain.OrderItem;
import mk.ukim.finki.emt.ecommerce.domain.Product;
import mk.ukim.finki.emt.ecommerce.repository.OrderItemRepository;
import mk.ukim.finki.emt.ecommerce.repository.OrderRepository;
import mk.ukim.finki.emt.ecommerce.repository.ProductRepository;
import mk.ukim.finki.emt.ecommerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;

    @Override
    public List<Order> findAll() {
        return orderRepository.findAllByOrderByIdAsc();
    }

    @Override
    public List<Order> findOrderByEmail(String email) {
        return orderRepository.findOrderByEmail(email);
    }

    @Override
    @Transactional
    public Order postOrder(Order validOrder, Map<Long, Long> productsId) {
        Order order = new Order();
        List<OrderItem> orderItemList = new ArrayList<>();

        for (Map.Entry<Long, Long> entry : productsId.entrySet()) {
            Product product = productRepository.findById(entry.getKey()).get();
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setAmount((product.getPrice() * entry.getValue()));
            orderItem.setQuantity(entry.getValue());
            orderItemList.add(orderItem);
            orderItemRepository.save(orderItem);
        }
        order.getOrderItems().addAll(orderItemList);
        order.setTotalPrice(validOrder.getTotalPrice());
        order.setFirstName(validOrder.getFirstName());
        order.setLastName(validOrder.getLastName());
        order.setCity(validOrder.getCity());
        order.setAddress(validOrder.getAddress());
        order.setPostIndex(validOrder.getPostIndex());
        order.setEmail(validOrder.getEmail());
        order.setPhoneNumber(validOrder.getPhoneNumber());
        orderRepository.save(order);

        return order;
    }

    @Override
    @Transactional
    public List<Order> deleteOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).get();
        order.getOrderItems().forEach(orderItem -> orderItemRepository.deleteById(orderItem.getId()));
        orderRepository.delete(order);
        return orderRepository.findAllByOrderByIdAsc();
    }
}
