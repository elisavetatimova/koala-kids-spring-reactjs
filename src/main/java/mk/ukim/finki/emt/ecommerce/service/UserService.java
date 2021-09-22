package mk.ukim.finki.emt.ecommerce.service;

import mk.ukim.finki.emt.ecommerce.domain.Product;
import mk.ukim.finki.emt.ecommerce.domain.Review;
import mk.ukim.finki.emt.ecommerce.domain.User;

import java.util.List;

/**
 * Manages business logic around users.
 */
public interface UserService {

    /**
     * Provides user by id.
     * @param userId
     * @return
     */
    User findUserById(Long userId);

    /**
     * Provides user by email
     * @param email email
     * @return
     */
    User findUserByEmail(String email);

    /**
     * Provides all users
     * @return
     */
    List<User> findAllUsers();

    /**
     * Provides cart for a user.
     * @param productIds
     * @return
     */
    List<Product> getCart(List<Long> productIds);

    /**
     * Updates profile for a user
     * @param email email
     * @param user user profile
     * @return
     */
    User updateProfile(String email, User user);

    /**
     * Adds review to a product.
     * @param review review
     * @param productId product identifier
     * @return
     */
    Product addReviewToProduct(Review review, Long productId);
}
