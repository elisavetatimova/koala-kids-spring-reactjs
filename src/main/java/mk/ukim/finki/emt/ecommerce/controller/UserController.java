package mk.ukim.finki.emt.ecommerce.controller;

import mk.ukim.finki.emt.ecommerce.dto.order.OrderRequest;
import mk.ukim.finki.emt.ecommerce.dto.order.OrderResponse;
import mk.ukim.finki.emt.ecommerce.dto.product.ProductResponse;
import mk.ukim.finki.emt.ecommerce.dto.review.ReviewRequest;
import mk.ukim.finki.emt.ecommerce.dto.user.UserRequest;
import mk.ukim.finki.emt.ecommerce.dto.user.UserResponse;
import mk.ukim.finki.emt.ecommerce.exception.InputFieldException;
import mk.ukim.finki.emt.ecommerce.mapper.OrderMapper;
import mk.ukim.finki.emt.ecommerce.mapper.UserMapper;
import mk.ukim.finki.emt.ecommerce.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserMapper userMapper;
    private final OrderMapper orderMapper;

    @GetMapping("/info")
    public ResponseEntity<UserResponse> getUserInfo(@AuthenticationPrincipal UserPrincipal user) {
        return ResponseEntity.ok(userMapper.findUserByEmail(user.getEmail()));
    }

    @PutMapping("/edit")
    public ResponseEntity<UserResponse> updateUserInfo(@AuthenticationPrincipal UserPrincipal user,
                                                       @Valid @RequestBody UserRequest request,
                                                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InputFieldException(bindingResult);
        } else {
            return ResponseEntity.ok(userMapper.updateProfile(user.getEmail(), request));
        }
    }

    @PostMapping("/cart")
    public ResponseEntity<List<ProductResponse>> getCart(@RequestBody List<Long> productsIds) {
        return ResponseEntity.ok(userMapper.getCart(productsIds));
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrderResponse>> getUserOrders(@AuthenticationPrincipal UserPrincipal user) {
        return ResponseEntity.ok(orderMapper.findOrderByEmail(user.getEmail()));
    }

    @PostMapping("/order")
    public ResponseEntity<OrderResponse> postOrder(@Valid @RequestBody OrderRequest order, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InputFieldException(bindingResult);
        } else {
            return ResponseEntity.ok(orderMapper.postOrder(order));
        }
    }

    @PostMapping("/review")
    public ResponseEntity<ProductResponse> addReviewToProduct(@Valid @RequestBody ReviewRequest review,
                                                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InputFieldException(bindingResult);
        } else {
            ProductResponse product = userMapper.addReviewToProduct(review, review.getProductId());
            return ResponseEntity.ok(product);
        }
    }
}
