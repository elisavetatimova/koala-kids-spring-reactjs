package mk.ukim.finki.emt.ecommerce.service;

import mk.ukim.finki.emt.ecommerce.domain.Cart;
import mk.ukim.finki.emt.ecommerce.domain.Order;

import java.util.List;
import java.util.Map;

/**
 * Manages business logic regarding orders in the webshop.
 */
public interface CartService {


    /**
     * Provides cart by id.
     * @param id cart Id
     * @return Cart
     */
    Cart findCartById(Long id);

    /**
     * Creates a new cart
     * @param validCart
     * @return Cart
     */
    Cart createCart(Cart validCart);

    /**
     * Adds line item to the cart
     * @param cartId
     * @param productId
     * @param quantity
     * @return
     */
    Cart addLineItem(Long cartId, Long productId, Long quantity);

    /**
     * Empties a cart
     * @param cartId
     * @return
     */
    Cart emptyCart(Long cartId);
}
