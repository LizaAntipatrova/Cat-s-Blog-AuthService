package ru.meow.meowauth.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.meow.meowauth.services.AuthService;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {


    private final AuthService authService;

    @Operation(summary = "Регистрация пользователя")
    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestHeader("Authorization") String authorizationHeader) {
        return authService.signUp(authorizationHeader);
    }

    @Operation(summary = "Авторизация пользователя")
    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestHeader("Authorization") String authorizationHeader) {
        return authService.signIn(authorizationHeader);
    }

    @Operation(summary = "Выход из системы")
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Cookie") String cookieHeader) {
        return authService.logout(cookieHeader);
    }
}
