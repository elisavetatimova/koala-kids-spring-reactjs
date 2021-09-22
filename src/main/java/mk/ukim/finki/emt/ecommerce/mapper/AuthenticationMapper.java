package mk.ukim.finki.emt.ecommerce.mapper;

import mk.ukim.finki.emt.ecommerce.dto.RegistrationRequest;
import mk.ukim.finki.emt.ecommerce.dto.auth.AuthenticationResponse;
import mk.ukim.finki.emt.ecommerce.dto.user.UserResponse;
import mk.ukim.finki.emt.ecommerce.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Mapper class that transforms domain model into data transfer objects.
 */
@Component
@RequiredArgsConstructor
public class AuthenticationMapper {

    private final AuthenticationService authenticationService;
    private final UserMapper userMapper;

    public AuthenticationResponse login(String email) {
        Map<String, String> resultMap = authenticationService.login(email);
        AuthenticationResponse response = new AuthenticationResponse();
        response.setEmail(resultMap.get("email"));
        response.setToken(resultMap.get("token"));
        response.setUserRole(resultMap.get("userRole"));
        return response;
    }

    public boolean registerUser(RegistrationRequest registrationRequest) {
        return authenticationService.registerUser(userMapper.convertToEntity(registrationRequest));
    }

    public String passwordReset(String email, String password) {
        return authenticationService.passwordReset(email, password);
    }
}
