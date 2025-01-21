package ddd.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * Класс для представления пользовательских данных в системе.
 * <p>
 * Этот класс реализует интерфейс UserDetails и используется для
 * аутентификации и авторизации пользователей в Spring Security.
 * Он оборачивает объект UserInfo, предоставляя необходимые методы
 * для работы с данными пользователя.
 */
public class CustomUserDetails implements UserDetails {

    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetails.class);

    private final UserInfo userInfo;

    /**
     * Конструктор для создания объекта CustomUserDetails.
     *
     * @param userInfo объект, содержащий информацию о пользователе
     */
    public CustomUserDetails(UserInfo userInfo) {
        this.userInfo = userInfo;
        logger.debug("Создан CustomUserDetails для пользователя: {}", userInfo.getEmail());
    }

    @Override
    public String getUsername() {
        logger.debug("Получение имени пользователя: {}", userInfo.getEmail());
        return userInfo.getEmail(); // Предполагается, что email является именем пользователя
    }

    @Override
    public String getPassword() {
        logger.debug("Получение пароля пользователя.");
        return userInfo.getPassword(); // Предполагается, что password хранится в UserInfo
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String role = "ROLE_" + userInfo.getRole().name();
        logger.debug("Получение ролей пользователя: {}", role);
        return Collections.singletonList(new SimpleGrantedAuthority(role));
        // Например, если роль ADMIN, то будет "ROLE_ADMIN"
    }

    @Override
    public boolean isAccountNonExpired() {
        logger.debug("Проверка срока действия аккаунта: true");
        return true; // Логика проверки срока действия аккаунта
    }

    @Override
    public boolean isAccountNonLocked() {
        logger.debug("Проверка блокировки аккаунта: true");
        return true; // Логика проверки блокировки аккаунта
    }

    @Override
    public boolean isCredentialsNonExpired() {
        logger.debug("Проверка срока действия учетных данных: true");
        return true; // Логика проверки срока действия учетных данных
    }

    @Override
    public boolean isEnabled() {
        logger.debug("Проверка активности аккаунта: true");
        return true; // Логика проверки активности аккаунта
    }
}