package mk.ukim.finki.emt.ecommerce.service.Impl;

import mk.ukim.finki.emt.ecommerce.domain.Role;
import mk.ukim.finki.emt.ecommerce.domain.User;
import mk.ukim.finki.emt.ecommerce.repository.UserRepository;
import mk.ukim.finki.emt.ecommerce.security.JwtProvider;
import mk.ukim.finki.emt.ecommerce.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Value("${hostname}")
    private String hostname;

    /**
     * Performs a login
     * @param email
     * @return
     */
    @Override
    public Map<String, String> login(String email) {
        User user = userRepository.findByEmail(email);
        String userRole = user.getRoles().iterator().next().name();
        String token = jwtProvider.createToken(email, userRole);

        Map<String, String> response = new HashMap<>();
        response.put("email", email);
        response.put("token", token);
        response.put("userRole", userRole);
        return response;
    }

    @Override
    public boolean registerUser(User user) {
        User userFromDb = userRepository.findByEmail(user.getEmail());
        if (userFromDb != null) return false;
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    @Override
    public String passwordReset(String email, String password) {
        User user = userRepository.findByEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        return "Password successfully changed!";
    }

}
