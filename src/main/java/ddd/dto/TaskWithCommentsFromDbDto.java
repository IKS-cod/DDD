package ddd.dto;

import ddd.enums.Priority;
import ddd.enums.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Класс для представления задачи и связанных с ней комментариев.
 * <p>
 * Этот класс используется для передачи информации о задаче, включая ее идентификатор,
 * заголовок, описание, статус, приоритет, адреса электронной почты автора и исполнителя,
 * а также список комментариев к задаче. Он служит контейнером для данных, необходимых
 * для отображения задачи и ее комментариев в пользовательском интерфейсе.
 */
public class TaskWithCommentsFromDbDto {

    private static final Logger logger = LoggerFactory.getLogger(TaskWithCommentsFromDbDto.class);

    private Long id;
    private String title;
    private String description;
    private Status status;
    private Priority priority;
    private String emailAuthor;
    private String emailAssignee;
    private List<CommentTextFromDbDto> comments;

    /**
     * Пустой конструктор.
     */
    public TaskWithCommentsFromDbDto() {
        logger.debug("Создан пустой TaskWithCommentsFromDbDto");
    }

    /**
     * Конструктор для создания объекта TaskWithCommentsFromDbDto.
     *
     * @param id            идентификатор задачи
     * @param title         заголовок задачи
     * @param description   описание задачи
     * @param status        статус задачи
     * @param priority      приоритет задачи
     * @param emailAuthor   адрес электронной почты автора
     * @param emailAssignee адрес электронной почты исполнителя
     * @param comments      список комментариев к задаче
     */
    public TaskWithCommentsFromDbDto(Long id, String title, String description, Status status, Priority priority,
                                     String emailAuthor, String emailAssignee, List<CommentTextFromDbDto> comments) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.emailAuthor = emailAuthor;
        this.emailAssignee = emailAssignee;
        this.comments = comments;

        logger.debug("Создан TaskWithCommentsFromDbDto: {}", this);
    }

    public Long getId() {
        logger.debug("Получение идентификатора задачи: {}", id);
        return id;
    }

    public void setId(Long id) {
        logger.debug("Установка идентификатора задачи: {}", id);
        this.id = id;
    }

    public String getTitle() {
        logger.debug("Получение заголовка задачи: {}", title);
        return title;
    }

    public void setTitle(String title) {
        logger.debug("Установка заголовка задачи: {}", title);
        this.title = title;
    }

    public String getDescription() {
        logger.debug("Получение описания задачи: {}", description);
        return description;
    }

    public void setDescription(String description) {
        logger.debug("Установка описания задачи: {}", description);
        this.description = description;
    }

    public Status getStatus() {
        logger.debug("Получение статуса задачи: {}", status);
        return status;
    }

    public void setStatus(Status status) {
        logger.debug("Установка статуса задачи: {}", status);
        this.status = status;
    }

    public Priority getPriority() {
        logger.debug("Получение приоритета задачи: {}", priority);
        return priority;
    }

    public void setPriority(Priority priority) {
        logger.debug("Установка приоритета задачи: {}", priority);
        this.priority = priority;
    }

    public String getEmailAuthor() {
        logger.debug("Получение адреса электронной почты автора: {}", emailAuthor);
        return emailAuthor;
    }

    public void setEmailAuthor(String emailAuthor) {
        logger.debug("Установка адреса электронной почты автора: {}", emailAuthor);
        this.emailAuthor = emailAuthor;
    }

    public String getEmailAssignee() {
        logger.debug("Получение адреса электронной почты исполнителя: {}", emailAssignee);
        return emailAssignee;
    }

    public void setEmailAssignee(String emailAssignee) {
        logger.debug("Установка адреса электронной почты исполнителя: {}", emailAssignee);
        this.emailAssignee = emailAssignee;
    }

    public List<CommentTextFromDbDto> getComments() {
        logger.debug("Получение списка комментариев: {}", comments);
        return comments;
    }

    public void setComments(List<CommentTextFromDbDto> comments) {
        logger.debug("Установка списка комментариев: {}", comments);
        this.comments = comments;
    }
}