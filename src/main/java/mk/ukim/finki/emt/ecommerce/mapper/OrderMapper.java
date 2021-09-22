package mk.ukim.finki.emt.ecommerce.mapper;

import mk.ukim.finki.emt.ecommerce.domain.Order;
import mk.ukim.finki.emt.ecommerce.dto.order.OrderRequest;
import mk.ukim.finki.emt.ecommerce.dto.order.OrderResponse;
import mk.ukim.finki.emt.ecommerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderMapper {

    private final ModelMapper modelMapper;
    private final OrderService orderService;

    private Order convertToEntity(OrderRequest orderRequest) {
        return modelMapper.map(orderRequest, Order.class);
    }

    private OrderResponse convertToResponseDto(Order order) {
        return modelMapper.map(order, OrderResponse.class);
    }

    private List<OrderResponse> convertListToResponseDto(List<Order> orders) {
        return orders.stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    public List<OrderResponse> findAllOrders() {
        return convertListToResponseDto(orderService.findAll());
    }

    public List<OrderResponse> findOrderByEmail(String email) {
        return convertListToResponseDto(orderService.findOrderByEmail(email));
    }

    public List<OrderResponse> deleteOrder(Long orderId) {
        return convertListToResponseDto(orderService.deleteOrder(orderId));
    }

    public OrderResponse postOrder(OrderRequest orderRequest) {
        return convertToResponseDto(orderService.postOrder(convertToEntity(orderRequest), orderRequest.getProductsId()));
    }
}
