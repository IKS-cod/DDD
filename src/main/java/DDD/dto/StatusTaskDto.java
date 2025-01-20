package DDD.dto;

import DDD.enums.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//public class StatusTaskDto {
//    private Status status;
//
//    public Status getStatus() {
//        return status;
//    }
//}
/**
 * Класс для представления статуса задачи.
 *
 * Этот класс используется для передачи информации о статусе задачи,
 * что позволяет обновлять статус задачи в системе. Он служит контейнером
 * для данных, необходимых для изменения статуса задачи.
 */
public class StatusTaskDto {

    private static final Logger logger = LoggerFactory.getLogger(StatusTaskDto.class);

    private Status status;

    /**
     * Получает статус задачи.
     *
     * @return статус задачи
     */
    public Status getStatus() {
        logger.debug("Получение статуса задачи: {}", status);
        return status;
    }

    /**
     * Устанавливает статус задачи.
     *
     * @param status новый статус задачи
     */
    public void setStatus(Status status) {
        logger.debug("Установка нового статуса задачи: {}", status);
        this.status = status;
    }
}