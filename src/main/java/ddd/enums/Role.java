package ddd.enums;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Перечисление для представления ролей пользователя в системе.
 * <p>
 * Это перечисление определяет две роли: USER и ADMIN.
 * Использование перечислений позволяет ограничить возможные значения
 * для ролей пользователей и улучшить читаемость кода.
 */
public enum Role {
    USER,
    ADMIN;

    private static final Logger logger = LoggerFactory.getLogger(Role.class);

    /**
     * Метод, возвращающий строковое представление роли.
     *
     * @return строковое значение роли
     */
    @Override
    public String toString() {
        String name = name();
        logger.debug("Получение строкового представления роли: {}", name);
        return name;
    }
}