package mk.ukim.finki.emt.ecommerce.service.Impl;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.emt.ecommerce.domain.Cart;
import mk.ukim.finki.emt.ecommerce.domain.Order;
import mk.ukim.finki.emt.ecommerce.domain.OrderItem;
import mk.ukim.finki.emt.ecommerce.domain.Product;
import mk.ukim.finki.emt.ecommerce.exception.CartNotFoundException;
import mk.ukim.finki.emt.ecommerce.repository.CartRepository;
import mk.ukim.finki.emt.ecommerce.repository.OrderItemRepository;
import mk.ukim.finki.emt.ecommerce.repository.OrderRepository;
import mk.ukim.finki.emt.ecommerce.repository.ProductRepository;
import mk.ukim.finki.emt.ecommerce.service.CartService;
import mk.ukim.finki.emt.ecommerce.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;


    @Override
    public Cart findCartById(Long id) {
        return cartRepository.findById(id).orElseThrow(CartNotFoundException::new);
    }

    @Override
    @Transactional
    public Cart createCart(Cart validCart) {
        return cartRepository.save(validCart);

    }

    @Override
    @Transactional
    public Cart addLineItem(Long cartId, Long productId, Long quantity) {

        final Cart cart = cartRepository.getOne(cartId);


        final Product product = productRepository.findById(productId).orElseThrow(IllegalArgumentException::new);
        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(product);
        orderItem.setAmount((product.getPrice() * quantity));
        orderItem.setQuantity(quantity);
        orderItemRepository.save(orderItem);

        cart.getOrderItems().add(orderItem);
        cart.setTotalPrice(cart.getTotalPrice() + orderItem.getAmount());
        cartRepository.save(cart);
        return cart;
    }



    @Override
    @Transactional
    public Cart emptyCart(Long cartId) {
        Cart cart = cartRepository.getOne(cartId);
        cart.getOrderItems().forEach(orderItem -> orderItemRepository.deleteById(orderItem.getId()));
        return cartRepository.save(cart);
    }
}
