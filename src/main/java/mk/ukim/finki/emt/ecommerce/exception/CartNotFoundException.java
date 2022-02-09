package mk.ukim.finki.emt.ecommerce.exception;

import lombok.Getter;

@Getter
public class CartNotFoundException extends RuntimeException {

    public CartNotFoundException() {
        super("Cart cannot be found for user");
    }
    public CartNotFoundException(String message) {
        super(message);
    }
}
