package mk.ukim.finki.emt.ecommerce.mapper;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.emt.ecommerce.domain.Cart;
import mk.ukim.finki.emt.ecommerce.dto.cart.AddLineItemRequest;
import mk.ukim.finki.emt.ecommerce.dto.cart.CartResponse;
import mk.ukim.finki.emt.ecommerce.service.CartService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CartMapper {

    private final ModelMapper modelMapper;
    private final CartService cartService;

    private CartResponse convertToResponseDto(Cart cart) {
        return modelMapper.map(cart, CartResponse.class);
    }

    public CartResponse getCartById(Long id) {
        return convertToResponseDto(cartService.findCartById(id));
    }

    public CartResponse addLineItem(AddLineItemRequest addLineItemRequest) {
        return convertToResponseDto(cartService.addLineItem(addLineItemRequest.getCartId(), addLineItemRequest.getProductsId(), addLineItemRequest.getQuantity()));
    }

    public CartResponse emptyCart(Long cartId) {
        return convertToResponseDto(cartService.emptyCart(cartId));
    }
}
