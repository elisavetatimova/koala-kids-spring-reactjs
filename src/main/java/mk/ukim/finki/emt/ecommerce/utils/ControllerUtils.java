package mk.ukim.finki.emt.ecommerce.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ControllerUtils {

    public boolean isPasswordDifferent(String password, String password2) {
        return password != null && !password.equals(password2);
    }
}
