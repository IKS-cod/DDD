package DDD.model;

import DDD.enums.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.Objects;

//@Entity
//public class UserInfo {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String email;
//
//    private String password;
//
//    @Enumerated(EnumType.STRING)
//    private Role role;
//
//    public UserInfo() {
//    }
//
//    public UserInfo(Long id, String email, String password, Role role) {
//        this.id = id;
//        this.email = email;
//        this.password = password;
//        this.role = role;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
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
//
//    public Role getRole() {
//        return role;
//    }
//
//    public void setRole(Role role) {
//        this.role = role;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        UserInfo userInfo = (UserInfo) o;
//        return Objects.equals(id, userInfo.id) && Objects.equals(email, userInfo.email) && Objects.equals(password, userInfo.password) && role == userInfo.role;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, email, password, role);
//    }
//
//    @Override
//    public String toString() {
//        return "User{" +
//                "id=" + id +
//                ", email='" + email + '\'' +
//                ", password='" + password + '\'' +
//                ", role=" + role +
//                '}';
//    }
//}
/**
 * Сущность для представления информации о пользователе в системе.
 *
 * Этот класс содержит данные о пользователе, включая его идентификатор,
 * адрес электронной почты, пароль и роль. Он используется для управления
 * пользователями в приложении.
 */
@Entity
public class UserInfo {

    private static final Logger logger = LoggerFactory.getLogger(UserInfo.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    /**
     * Пустой конструктор.
     */
    public UserInfo() {
        logger.debug("Создан пустой объект UserInfo.");
    }

    /**
     * Конструктор для создания пользователя.
     *
     * @param id идентификатор пользователя
     * @param email адрес электронной почты пользователя
     * @param password пароль пользователя
     * @param role роль пользователя
     */
    public UserInfo(Long id, String email, String password, Role role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
        logger.debug("Создан объект UserInfo: {}", this);
    }

    public Long getId() {
        logger.debug("Получение идентификатора пользователя: {}", id);
        return id;
    }

    public void setId(Long id) {
        logger.debug("Установка идентификатора пользователя: {}", id);
        this.id = id;
    }

    public String getEmail() {
        logger.debug("Получение адреса электронной почты пользователя: {}", email);
        return email;
    }

    public void setEmail(String email) {
        logger.debug("Установка адреса электронной почты пользователя: {}", email);
        this.email = email;
    }

    public String getPassword() {
        logger.debug("Получение пароля пользователя.");
        return password; // Будьте осторожны с логированием паролей в реальных приложениях
    }

    public void setPassword(String password) {
        logger.debug("Установка пароля пользователя.");
        this.password = password; // Будьте осторожны с логированием паролей в реальных приложениях
    }

    public Role getRole() {
        logger.debug("Получение роли пользователя: {}", role);
        return role;
    }

    public void setRole(Role role) {
        logger.debug("Установка роли пользователя: {}", role);
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfo userInfo = (UserInfo) o;
        return Objects.equals(id, userInfo.id) &&
                Objects.equals(email, userInfo.email) &&
                Objects.equals(password, userInfo.password) &&
                role == userInfo.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, role);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}'; // Не логируем пароль для безопасности
    }
}