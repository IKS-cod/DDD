package DDD.dto;

//public class AuthRequest {
//
//    private String email;
//
//    private String password;
//
//    // Геттеры и сеттеры
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//}

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Класс для представления запроса на авторизацию пользователя.
 *
 * Этот класс используется для передачи данных аутентификации пользователя,
 * включая его электронную почту и пароль. Он служит контейнером для этих данных
 * при обработке запросов на вход в систему.
 */
public class AuthRequest {

    private static final Logger logger = LoggerFactory.getLogger(AuthRequest.class);

    private String email;
    private String password;

    /**
     * Получает адрес электронной почты пользователя.
     *
     * @return адрес электронной почты
     */
    public String getEmail() {
        return email;
    }

    /**
     * Устанавливает адрес электронной почты пользователя.
     *
     * @param email адрес электронной почты
     */
    public void setEmail(String email) {
        logger.debug("Установка адреса электронной почты: {}", email);
        this.email = email;
    }

    /**
     * Получает пароль пользователя.
     *
     * @return пароль
     */
    public String getPassword() {
        return password;
    }

    /**
     * Устанавливает пароль пользователя.
     *
     * @param password пароль
     */
    public void setPassword(String password) {
        logger.debug("Установка пароля");
        this.password = password; // В реальных приложениях не рекомендуется логировать пароли
    }
}