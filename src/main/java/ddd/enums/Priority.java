package ddd.enums;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Перечисление для представления приоритета задачи.
 * <p>
 * Это перечисление определяет три уровня приоритета: HIGH, MEDIUM и LOW.
 * Использование перечислений позволяет ограничить возможные значения
 * для приоритета задачи и улучшить читаемость кода.
 */
public enum Priority {
    HIGH,
    MEDIUM,
    LOW;

    private static final Logger logger = LoggerFactory.getLogger(Priority.class);

    /**
     * Метод, возвращающий строковое представление приоритета.
     *
     * @return строковое значение приоритета
     */
    @Override
    public String toString() {
        String name = name();
        logger.debug("Получение строкового представления приоритета: {}", name);
        return name;
    }
}