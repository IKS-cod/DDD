package DDD.dto;

import DDD.enums.Priority;
import DDD.enums.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//public class TaskFromDbDto {
//    private Long id;
//    private String title;
//    private String description;
//    private Status status;
//    private Priority priority;
//    private String emailAuthor;
//    private String emailAssignee;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public Status getStatus() {
//        return status;
//    }
//
//    public void setStatus(Status status) {
//        this.status = status;
//    }
//
//    public Priority getPriority() {
//        return priority;
//    }
//
//    public void setPriority(Priority priority) {
//        this.priority = priority;
//    }
//
//    public String getEmailAuthor() {
//        return emailAuthor;
//    }
//
//    public void setEmailAuthor(String emailAuthor) {
//        this.emailAuthor = emailAuthor;
//    }
//
//    public String getEmailAssignee() {
//        return emailAssignee;
//    }
//
//    public void setEmailAssignee(String emailAssignee) {
//        this.emailAssignee = emailAssignee;
//    }
//}
/**
 * Класс для представления задачи, полученной из базы данных.
 *
 * Этот класс используется для передачи информации о задаче, включая ее идентификатор,
 * заголовок, описание, статус, приоритет, а также адреса электронной почты автора и исполнителя.
 * Он служит контейнером для данных, необходимых для отображения задачи в пользовательском интерфейсе
 * или передачи между слоями приложения.
 */
public class TaskFromDbDto {

    private static final Logger logger = LoggerFactory.getLogger(TaskFromDbDto.class);

    private Long id;
    private String title;
    private String description;
    private Status status;
    private Priority priority;
    private String emailAuthor;
    private String emailAssignee;

    /**
     * Получает идентификатор задачи.
     *
     * @return идентификатор задачи
     */
    public Long getId() {
        logger.debug("Получение идентификатора задачи: {}", id);
        return id;
    }

    /**
     * Устанавливает идентификатор задачи.
     *
     * @param id идентификатор задачи
     */
    public void setId(Long id) {
        logger.debug("Установка идентификатора задачи: {}", id);
        this.id = id;
    }

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
     * Устанавливает заголовок задачи.
     *
     * @param title заголовок задачи
     */
    public void setTitle(String title) {
        logger.debug("Установка заголовка задачи: {}", title);
        this.title = title;
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
     * Устанавливает описание задачи.
     *
     * @param description описание задачи
     */
    public void setDescription(String description) {
        logger.debug("Установка описания задачи: {}", description);
        this.description = description;
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
     * Устанавливает статус задачи.
     *
     * @param status статус задачи
     */
    public void setStatus(Status status) {
        logger.debug("Установка статуса задачи: {}", status);
        this.status = status;
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
     * Устанавливает приоритет задачи.
     *
     * @param priority приоритет задачи
     */
    public void setPriority(Priority priority) {
        logger.debug("Установка приоритета задачи: {}", priority);
        this.priority = priority;
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
     * Устанавливает адрес электронной почты автора задачи.
     *
     * @param emailAuthor адрес электронной почты автора
     */
    public void setEmailAuthor(String emailAuthor) {
        logger.debug("Установка адреса электронной почты автора: {}", emailAuthor);
        this.emailAuthor = emailAuthor;
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
     * Устанавливает адрес электронной почты исполнителя задачи.
     *
     * @param emailAssignee адрес электронной почты исполнителя
     */
    public void setEmailAssignee(String emailAssignee) {
        logger.debug("Установка адреса электронной почты исполнителя: {}", emailAssignee);
        this.emailAssignee = emailAssignee;
    }
}