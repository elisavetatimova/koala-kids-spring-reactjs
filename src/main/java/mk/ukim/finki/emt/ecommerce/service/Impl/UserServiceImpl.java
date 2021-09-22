package mk.ukim.finki.emt.ecommerce.service.Impl;

import mk.ukim.finki.emt.ecommerce.domain.Product;
import mk.ukim.finki.emt.ecommerce.domain.Review;
import mk.ukim.finki.emt.ecommerce.domain.User;
import mk.ukim.finki.emt.ecommerce.repository.ProductRepository;
import mk.ukim.finki.emt.ecommerce.repository.ReviewRepository;
import mk.ukim.finki.emt.ecommerce.repository.UserRepository;
import mk.ukim.finki.emt.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public User findUserById(Long userId) {
        return userRepository.findById(userId).get();
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAllByOrderByIdAsc();
    }

    @Override
    public List<Product> getCart(List<Long> productIds) {
        return productRepository.findByIdIn(productIds);
    }

    @Override
    public User updateProfile(String email, User user) {
        User userFromDb = userRepository.findByEmail(email);
        userFromDb.setFirstName(user.getFirstName());
        userFromDb.setLastName(user.getLastName());
        userFromDb.setCity(user.getCity());
        userFromDb.setAddress(user.getAddress());
        userFromDb.setPhoneNumber(user.getPhoneNumber());
        userFromDb.setPostIndex(user.getPostIndex());
        userRepository.save(userFromDb);
        return userFromDb;
    }

    @Override
    public Product addReviewToProduct(Review review, Long productId) {
        Product product = productRepository.getOne(productId);
        List<Review> reviews = product.getReviews();
        reviews.add(review);
        double totalReviews = reviews.size();
        double sumRating = reviews.stream().mapToInt(Review::getRating).sum();
        product.setProductRating(sumRating / totalReviews);
        reviewRepository.save(review);
        return product;
    }
}
