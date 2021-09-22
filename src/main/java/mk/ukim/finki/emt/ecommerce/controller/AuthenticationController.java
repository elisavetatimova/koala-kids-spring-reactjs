package mk.ukim.finki.emt.ecommerce.controller;

import mk.ukim.finki.emt.ecommerce.dto.auth.AuthenticationRequest;
import mk.ukim.finki.emt.ecommerce.dto.PasswordResetRequest;
import mk.ukim.finki.emt.ecommerce.dto.auth.AuthenticationResponse;
import mk.ukim.finki.emt.ecommerce.exception.ApiRequestException;
import mk.ukim.finki.emt.ecommerce.exception.InputFieldException;
import mk.ukim.finki.emt.ecommerce.exception.PasswordException;
import mk.ukim.finki.emt.ecommerce.mapper.AuthenticationMapper;
import mk.ukim.finki.emt.ecommerce.security.UserPrincipal;
import mk.ukim.finki.emt.ecommerce.utils.ControllerUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final AuthenticationMapper authenticationMapper;
    private final ControllerUtils controllerUtils;

    /**
     * Login handler. Authenticates user using their email and password
     * @param request
     * @return authorization response including the json web token
     */
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            return ResponseEntity.ok(authenticationMapper.login(request.getEmail()));
        } catch (AuthenticationException e) {
            throw new ApiRequestException("Incorrect password or email", HttpStatus.FORBIDDEN);
        }
    }

    /**
     * Modifies password for a user
     * @param user
     * @param passwordReset
     * @param bindingResult
     * @return
     */
    @PutMapping("/edit/password")
    public ResponseEntity<String> updateUserPassword(@AuthenticationPrincipal UserPrincipal user,
                                                     @Valid @RequestBody PasswordResetRequest passwordReset,
                                                     BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InputFieldException(bindingResult);
        } else if (controllerUtils.isPasswordDifferent(passwordReset.getPassword(), passwordReset.getPassword2())) {
            throw new PasswordException("Passwords do not match.");
        } else {
            return ResponseEntity.ok(authenticationMapper.passwordReset(user.getEmail(), passwordReset.getPassword()));
        }
    }
}
