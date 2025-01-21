package ddd.service;

import ddd.model.CustomUserDetails;
import ddd.model.UserInfo;
import ddd.repository.UserInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/**
 * Сервис для управления пользователями.
 * <p>
 * Этот класс реализует интерфейс UserDetailsService и отвечает за загрузку
 * информации о пользователе по его имени пользователя (в данном случае — по электронной почте).
 * Он использует репозиторий UserInfoRepository для доступа к данным о пользователях.
 */
@Service
public class UserService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserInfoRepository userRepository;

    /**
     * Конструктор для создания экземпляра UserService.
     *
     * @param userRepository репозиторий для работы с пользователями
     */
    @Autowired
    public UserService(UserInfoRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Загружает пользователя по его имени пользователя (электронной почте).
     *
     * @param email адрес электронной почты пользователя
     * @return объект UserDetails, содержащий информацию о пользователе
     * @throws UsernameNotFoundException если пользователь не найден
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        logger.debug("Загрузка пользователя с email: {}", email);

        UserInfo userInfo = userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    logger.error("Пользователь с email {} не найден.", email);
                    return new UsernameNotFoundException("User not found");
                });

        logger.info("Пользователь с email {} успешно загружен.", email);

        return new CustomUserDetails(userInfo); // Оборачиваем UserInfo в CustomUserDetails
    }
}