package mk.ukim.finki.emt.ecommerce.controller;

import mk.ukim.finki.emt.ecommerce.dto.RegistrationRequest;
import mk.ukim.finki.emt.ecommerce.exception.EmailException;
import mk.ukim.finki.emt.ecommerce.exception.InputFieldException;
import mk.ukim.finki.emt.ecommerce.exception.PasswordException;
import mk.ukim.finki.emt.ecommerce.mapper.AuthenticationMapper;
import mk.ukim.finki.emt.ecommerce.utils.ControllerUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/registration")
public class RegistrationController {

    private final AuthenticationMapper authenticationMapper;
    private final ControllerUtils controllerUtils;

    /**
     * Performs registration for users.
     * @param user
     * @param bindingResult
     * @return
     */
    @PostMapping
    public ResponseEntity<String> registration(@Valid @RequestBody RegistrationRequest user, BindingResult bindingResult) {
        if (controllerUtils.isPasswordDifferent(user.getPassword(), user.getPassword2())) {
            throw new PasswordException("Passwords do not match.");
        }
        if (bindingResult.hasErrors()) {
            throw new InputFieldException(bindingResult);
        }
        if (!authenticationMapper.registerUser(user)) {
            throw new EmailException("Email is already used.");
        }
        return ResponseEntity.ok("User successfully registered.");
    }
}
