package ddd.controller;

import ddd.dto.AuthRequest;
import ddd.dto.CreateUserInfoDto;
import ddd.model.JwtUtil;
import ddd.model.UserInfo;
import ddd.service.AuthServiceImpl;
import ddd.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Контроллер для обработки запросов, связанных с авторизацией пользователей.
 * <p>
 * Этот класс предоставляет конечные точки для регистрации новых пользователей
 * и авторизации существующих пользователей. Он использует AuthenticationManager
 * для аутентификации и JwtUtil для генерации JWT токенов.
 * <p>
 * Основные функции:
 * - Регистрация нового пользователя через /register
 * - Авторизация пользователя и получение JWT токена через /login
 */
@RestController
@Tag(name = "Авторизация")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;
    private final AuthServiceImpl authServiceImpl;
    private final UserService userService;

    public AuthController(JwtUtil jwtUtil, AuthServiceImpl authServiceImpl, UserService userService) {
        this.jwtUtil = jwtUtil;
        this.authServiceImpl = authServiceImpl;
        this.userService = userService;
    }

    /**
     * Регистрация нового пользователя.
     *
     * @param createUserInfoDto данные нового пользователя
     * @return статус ответа в зависимости от успешности регистрации
     * @throws IOException в случае ошибки ввода-вывода
     */
    @PostMapping("/register")
    @Tag(name = "Регистрация")
    @Operation(summary = "Регистрация пользователя")
    public ResponseEntity<UserInfo> register(@RequestBody CreateUserInfoDto createUserInfoDto) throws IOException {
        logger.info("Запрос на регистрацию пользователя: {}", createUserInfoDto.getEmail());

        if (authServiceImpl.register(createUserInfoDto)) {
            logger.info("Пользователь успешно зарегистрирован: {}", createUserInfoDto.getEmail());
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            logger.warn("Ошибка регистрации пользователя: {}", createUserInfoDto.getEmail());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     * Авторизация пользователя и получение JWT токена.
     *
     * @param authRequest запрос на авторизацию с данными пользователя
     * @return JWT токен для авторизованного пользователя
     */
    @PostMapping("/login")
    @Tag(name = "Авторизация пользователя")
    @Operation(summary = "Авторизация пользователя")
    public ResponseEntity<String> login(@RequestBody AuthRequest authRequest) {
        logger.info("Запрос на авторизацию для пользователя: {}", authRequest.getEmail());

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
            );
            final UserDetails userDetails = userService.loadUserByUsername(authRequest.getEmail());
            final String jwt = jwtUtil.generateToken(userDetails);
            logger.info("Пользователь успешно авторизован: {}", authRequest.getEmail());
            return ResponseEntity.ok(jwt);
        } catch (Exception e) {
            logger.error("Ошибка авторизации для пользователя: {}", authRequest.getEmail(), e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
