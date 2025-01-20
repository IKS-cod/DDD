package DDD.enums;

//public enum Status {
//    PENDING,
//    IN_PROGRESS,
//    COMPLETED
//}

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Перечисление для представления статуса задачи.
 *
 * Это перечисление определяет три статуса задачи: PENDING (ожидание),
 * IN_PROGRESS (в процессе) и COMPLETED (завершено).
 * Использование перечислений позволяет ограничить возможные значения
 * для статуса задачи и улучшить читаемость кода.
 */
public enum Status {
    PENDING,
    IN_PROGRESS,
    COMPLETED;

    private static final Logger logger = LoggerFactory.getLogger(Status.class);

    /**
     * Метод, возвращающий строковое представление статуса.
     *
     * @return строковое значение статуса
     */
    @Override
    public String toString() {
        String name = name();
        logger.debug("Получение строкового представления статуса: {}", name);
        return name;
    }
}