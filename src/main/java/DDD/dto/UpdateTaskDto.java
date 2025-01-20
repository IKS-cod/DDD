package DDD.dto;

import DDD.enums.Priority;
import DDD.enums.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//public class UpdateTaskDto {
//    private String title;
//    private String description;
//    private Status status;
//    private Priority priority;
//    private String emailAssignee;
//
//    public String getTitle() {
//        return title;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public Status getStatus() {
//        return status;
//    }
//
//    public Priority getPriority() {
//        return priority;
//    }
//
//    public String getEmailAssignee() {
//        return emailAssignee;
//    }
//}
/**
 * Класс для представления данных задачи при ее обновлении.
 *
 * Этот класс используется для передачи информации о задаче, включая
 * заголовок, описание, статус, приоритет и адрес электронной почты исполнителя.
 * Он служит контейнером для данных, необходимых для обновления существующей задачи
 * в системе.
 */
public class UpdateTaskDto {

    private static final Logger logger = LoggerFactory.getLogger(UpdateTaskDto.class);

    private String title;
    private String description;
    private Status status;
    private Priority priority;
    private String emailAssignee;

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
     * Получает описание задачи.
     *
     * @return описание задачи
     */
    public String getDescription() {
        logger.debug("Получение описания задачи: {}", description);
        return description;
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
     * Получает приоритет задачи.
     *
     * @return приоритет задачи
     */
    public Priority getPriority() {
        logger.debug("Получение приоритета задачи: {}", priority);
        return priority;
    }

    /**
     * Получает адрес электронной почты исполнителя задачи.
     *
     * @return адрес электронной почты исполнителя
     */
    public String getEmailAssignee() {
        logger.debug("Получение адреса электронной почты исполнителя: {}", emailAssignee);
        return emailAssignee;
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
     * Устанавливает описание задачи.
     *
     * @param description описание задачи
     */
    public void setDescription(String description) {
        logger.debug("Установка описания задачи: {}", description);
        this.description = description;
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

    /**
     * Устанавливает приоритет задачи.
     *
     * @param priority приоритет задачи
     */
    public void setPriority(Priority priority) {
        logger.debug("Установка приоритета задачи: {}", priority);
        this.priority = priority;
    }

    /**
     * Устанавливает адрес электронной почты исполнителя задачи.
     *
     * @param emailAssignee адрес электронной почты исполнителя
     */
    public void setEmailAssignee(String emailAssignee) {
        logger.debug("Установка адреса электронной почты исполнителя: {}", emailAssignee);
        this.emailAssignee = emailAssignee;
    }
}