package ddd.service;

import ddd.dto.CreateUserInfoDto;
import ddd.model.UserInfo;
import ddd.repository.UserInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


/**
 * Реализация сервиса аутентификации пользователей.
 * <p>
 * Этот класс отвечает за регистрацию пользователей, включая проверку
 * существования пользователя с таким же адресом электронной почты,
 * хеширование пароля и сохранение информации о пользователе в базе данных.
 */
@Service
public class AuthServiceImpl {

    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    private final UserInfoRepository userInfoRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Конструктор для создания экземпляра AuthServiceImpl.
     *
     * @param userInfoRepository репозиторий для работы с пользователями
     * @param passwordEncoder    кодировщик паролей
     */
    public AuthServiceImpl(UserInfoRepository userInfoRepository, PasswordEncoder passwordEncoder) {
        this.userInfoRepository = userInfoRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Регистрация нового пользователя.
     * <p>
     * Проверяет, существует ли пользователь с указанным адресом электронной почты.
     * Если пользователь не существует, создаёт нового пользователя, хеширует его пароль
     * и сохраняет информацию в базе данных.
     *
     * @param createUserInfoDto объект, содержащий информацию о новом пользователе
     * @return true, если регистрация успешна; false, если пользователь с таким email уже существует
     */
    public boolean register(CreateUserInfoDto createUserInfoDto) {
        if (userInfoRepository.findByEmail(createUserInfoDto.getEmail()).isPresent()) {
            logger.warn("Регистрация не удалась: пользователь с email {} уже существует.", createUserInfoDto.getEmail());
            return false; // Пользователь с таким email уже существует
        }

        UserInfo userInfo = new UserInfo();
        userInfo.setEmail(createUserInfoDto.getEmail());
        userInfo.setPassword(passwordEncoder.encode(createUserInfoDto.getPassword()));
        userInfo.setRole(createUserInfoDto.getRole());

        userInfoRepository.save(userInfo);
        logger.info("Регистрация успешна: пользователь с email {} зарегистрирован.", createUserInfoDto.getEmail());

        return true; // Регистрация успешна
    }
}