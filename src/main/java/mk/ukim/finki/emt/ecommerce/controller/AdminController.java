package mk.ukim.finki.emt.ecommerce.controller;

import mk.ukim.finki.emt.ecommerce.dto.order.OrderResponse;
import mk.ukim.finki.emt.ecommerce.dto.product.ProductRequest;
import mk.ukim.finki.emt.ecommerce.dto.product.ProductResponse;
import mk.ukim.finki.emt.ecommerce.dto.user.UserRequest;
import mk.ukim.finki.emt.ecommerce.dto.user.UserResponse;
import mk.ukim.finki.emt.ecommerce.exception.InputFieldException;
import mk.ukim.finki.emt.ecommerce.mapper.OrderMapper;
import mk.ukim.finki.emt.ecommerce.mapper.ProductMapper;
import mk.ukim.finki.emt.ecommerce.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Admin controller, accessible for users with assigned admin roles.
 * Maintains admin tasks.
 */
@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/api/v1/admin")
public class AdminController {

    private final UserMapper userMapper;
    private final ProductMapper productMapper;
    private final OrderMapper orderMapper;

    /**
     * Adds new product to the product catalog.
     * @param product product
     * @param bindingResult binding result
     * @return product response.
     */
    @PostMapping("/add")
    public ResponseEntity<ProductResponse> addProduct(@RequestBody @Valid ProductRequest product,
                                                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InputFieldException(bindingResult);
        } else {
            return ResponseEntity.ok(productMapper.saveProduct(product));
        }
    }

    /**
     * Modifies existing product in the product catalog.
     * @param product product catalog
     * @param bindingResult binding result
     * @return product response.
     */
    @PostMapping("/edit")
    public ResponseEntity<ProductResponse> updateProduct(@RequestBody @Valid ProductRequest product,
                                                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InputFieldException(bindingResult);
        } else {
            return ResponseEntity.ok(productMapper.saveProduct(product));
        }
    }

    /**
     * Deletes product from the product catalog.
     * @param productId product identifier
     * @return
     */
    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<List<ProductResponse>> deleteProduct(@PathVariable(value = "productId") Long productId) {
        return ResponseEntity.ok(productMapper.deleteProduct(productId));
    }

    /**
     * Provides all orders.
     * @return
     */
    @GetMapping("/orders")
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        return ResponseEntity.ok(orderMapper.findAllOrders());
    }

    /**
     * Providers all orders for specific user
     * @param user
     * @return
     */
    @PostMapping("/order")
    public ResponseEntity<List<OrderResponse>> getUserOrdersByEmail(@RequestBody UserRequest user) {
        return ResponseEntity.ok(orderMapper.findOrderByEmail(user.getEmail()));
    }

    /**
     * Deletes an order by id
     * @param orderId
     * @return
     */
    @DeleteMapping("/order/delete/{orderId}")
    public ResponseEntity<List<OrderResponse>> deleteOrder(@PathVariable(value = "orderId") Long orderId) {
        return ResponseEntity.ok(orderMapper.deleteOrder(orderId));
    }

    /**
     * Provides user details by id
     * @param userId
     * @return
     */
    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable("id") Long userId) {
        return ResponseEntity.ok(userMapper.findUserById(userId));
    }

    /**
     * Provides all users.
     * @return
     */
    @GetMapping("/user/all")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userMapper.findAllUsers());
    }
}
