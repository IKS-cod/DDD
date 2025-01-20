package DDD.dto;

import DDD.enums.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//public class UpdateTaskStatusDto {
//    private String title;
//    private Status status;
//
//    public String getTitle() {
//        return title;
//    }
//
//    public Status getStatus() {
//        return status;
//    }
//}
/**
 * Класс для представления данных при обновлении статуса задачи.
 *
 * Этот класс используется для передачи информации о заголовке задачи и ее статусе.
 * Он служит контейнером для данных, необходимых для обновления статуса существующей задачи
 * в системе.
 */
public class UpdateTaskStatusDto {

    private static final Logger logger = LoggerFactory.getLogger(UpdateTaskStatusDto.class);

    private String title;
    private Status status;

    /**
     * Получает заголовок задачи.
     *
     * @return заголовок задачи
     */
    public String getTitle() {
        logger.debug("Получение заголовка задачи: {}", title);
        return title;
    }

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
     * Устанавливает заголовок задачи.
     *
     * @param title заголовок задачи
     */
    public void setTitle(String title) {
        logger.debug("Установка заголовка задачи: {}", title);
        this.title = title;
    }

    /**
     * Устанавливает статус задачи.
     *
     * @param status статус задачи
     */
    public void setStatus(Status status) {
        logger.debug("Установка статуса задачи: {}", status);
        this.status = status;
    }
}