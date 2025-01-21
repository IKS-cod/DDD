package ddd.dto;

import ddd.enums.Priority;
import ddd.enums.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Класс для представления данных задачи при ее создании.
 * <p>
 * Этот класс используется для передачи информации о задаче, включая
 * заголовок, описание, статус, приоритет, а также адреса электронной почты
 * автора и исполнителя задачи. Он служит контейнером для данных, необходимых
 * для создания новой задачи в системе.
 */
public class CreateTaskDto {

    private static final Logger logger = LoggerFactory.getLogger(CreateTaskDto.class);

    private String title;
    private String description;
    private Status status;
    private Priority priority;
    private String emailAuthor;
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
     * Получает адрес электронной почты автора задачи.
     *
     * @return адрес электронной почты автора
     */
    public String getEmailAuthor() {
        logger.debug("Получение адреса электронной почты автора: {}", emailAuthor);
        return emailAuthor;
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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public void setEmailAuthor(String emailAuthor) {
        this.emailAuthor = emailAuthor;
    }

    public void setEmailAssignee(String emailAssignee) {
        this.emailAssignee = emailAssignee;
    }
}