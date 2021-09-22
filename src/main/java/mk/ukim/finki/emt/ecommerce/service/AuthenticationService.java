package mk.ukim.finki.emt.ecommerce.service;

import mk.ukim.finki.emt.ecommerce.domain.User;

import java.util.Map;

public interface AuthenticationService {

    Map<String, String> login(String email);

    boolean registerUser(User user);

    String passwordReset(String email, String password);
}
