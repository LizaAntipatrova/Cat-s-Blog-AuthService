//package ru.meow.meowauth.services;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import ru.meow.meowauth.data.entity.user.User;
//import ru.meow.meowauth.data.repositories.RoleRepository;
//import ru.meow.meowauth.data.entity.roles.RoleType;
//import ru.meow.meowauth.dto.JwtAuthenticationResponse;
//import ru.meow.meowauth.dto.SignInRequest;
//import ru.meow.meowauth.dto.SignUpRequest;
//
//
//@Service
//@RequiredArgsConstructor
//public class AuthenticationService {
//    private final AuthUserService authUserService;
//    private final RoleRepository roleRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final AuthenticationManager authenticationManager;
//
//    /**
//     * Регистрация пользователя
//     *
//     * @param request данные пользователя
//     * @return токен
//     */
//    public JwtAuthenticationResponse signUp(SignUpRequest request) {
//
//        var user = new User();
//        user.setUsername(request.getUsername());
//        user.setPassword(passwordEncoder.encode(request.getPassword()));
//        user.setUserRole(roleRepository.findByName(RoleType.ROLE_USER));
//
//        authUserService.create(user);
//
//        var jwt = jwtService.generateToken(user);
//        return new JwtAuthenticationResponse(jwt);
//    }
//
//    /**
//     * Аутентификация пользователя
//     *
//     * @param request данные пользователя
//     * @return токен
//     */
//    public JwtAuthenticationResponse signIn(SignInRequest request) {
//        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
//                request.getUsername(),
//                request.getPassword()
//        ));
//
//        var user = authUserService
//                .userDetailsService()
//                .loadUserByUsername(request.getUsername());
//
//        var jwt = jwtService.generateToken(user);
//        return new JwtAuthenticationResponse(jwt);
//
//    }
//
//}