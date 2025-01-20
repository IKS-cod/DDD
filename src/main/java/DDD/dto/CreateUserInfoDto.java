package DDD.dto;

import DDD.enums.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


//public class CreateUserInfoDto {
//    private String email;
//    private String password;
//    private Role role;
//
//    public String getEmail() {
//        return email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public Role getRole() {
//        return role;
//    }
//}
/**
 * Класс для представления данных пользователя при его создании.
 *
 * Этот класс используется для передачи информации о новом пользователе,
 * включая адрес электронной почты, пароль и роль. Он служит контейнером
 * для данных, необходимых для создания нового пользователя в системе.
 */
public class CreateUserInfoDto {

    private static final Logger logger = LoggerFactory.getLogger(CreateUserInfoDto.class);

    private String email;
    private String password;
    private Role role;

    /**
     * Получает адрес электронной почты пользователя.
     *
     * @return адрес электронной почты
     */
    public String getEmail() {
        logger.debug("Получение адреса электронной почты: {}", email);
        return email;
    }

    /**
     * Получает пароль пользователя.
     *
     * @return пароль
     */
    public String getPassword() {
        logger.debug("Получение пароля пользователя"); // Не рекомендуется логировать пароли в реальных приложениях
        return password;
    }

    /**
     * Получает роль пользователя.
     *
     * @return роль пользователя
     */
    public Role getRole() {
        logger.debug("Получение роли пользователя: {}", role);
        return role;
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
     * Устанавливает пароль пользователя.
     *
     * @param password пароль
     */
    public void setPassword(String password) {
        logger.debug("Установка пароля пользователя"); // Не рекомендуется логировать пароли в реальных приложениях
        this.password = password;
    }

    /**
     * Устанавливает роль пользователя.
     *
     * @param role роль пользователя
     */
    public void setRole(Role role) {
        logger.debug("Установка роли пользователя: {}", role);
        this.role = role;
    }
}