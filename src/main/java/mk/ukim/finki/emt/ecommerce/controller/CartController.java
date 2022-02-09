package mk.ukim.finki.emt.ecommerce.controller;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.emt.ecommerce.dto.cart.AddLineItemRequest;
import mk.ukim.finki.emt.ecommerce.dto.cart.CartResponse;
import mk.ukim.finki.emt.ecommerce.exception.InputFieldException;
import mk.ukim.finki.emt.ecommerce.mapper.CartMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/carts")
public class CartController {

    private final CartMapper cartMapper;
    @PostMapping("/add-line-item")
    public ResponseEntity<CartResponse> addLineItem(@RequestBody @Valid AddLineItemRequest addLineItemRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InputFieldException(bindingResult);
        } else {
            return ResponseEntity.ok(cartMapper.addLineItem(addLineItemRequest));
        }
    }

    @PostMapping("/empty")
    public ResponseEntity<CartResponse> emptyCart(@RequestBody Long cartId) {
        return ResponseEntity.ok(cartMapper.emptyCart(cartId));
    }
}
