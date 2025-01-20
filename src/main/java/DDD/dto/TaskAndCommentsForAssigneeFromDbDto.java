package DDD.dto;

import DDD.enums.Priority;
import DDD.enums.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

//public class TaskAndCommentsForAssigneeFromDbDto {
//    private Long id;
//    private String title;
//    private String description;
//    private Status status;
//    private Priority priority;
//    private String emailAssignee;
//    private List<String> comments;
//
//    public TaskAndCommentsForAssigneeFromDbDto(Long id,
//                                               String title,
//                                               String description,
//                                               Status status,
//                                               Priority priority,
//                                               String emailAssignee,
//                                               List<String> comments) {
//        this.id = id;
//        this.title = title;
//        this.description = description;
//        this.status = status;
//        this.priority = priority;
//        this.emailAssignee = emailAssignee;
//        this.comments = comments;
//    }
//
//    public TaskAndCommentsForAssigneeFromDbDto() {
//    }
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
//    public String getEmailAssignee() {
//        return emailAssignee;
//    }
//
//    public void setEmailAssignee(String emailAssignee) {
//        this.emailAssignee = emailAssignee;
//    }
//
//    public List<String> getComments() {
//        return comments;
//    }
//
//    public void setComments(List<String> comments) {
//        this.comments = comments;
//    }
//}
/**
 * Класс для представления задачи и связанных с ней комментариев для исполнителя.
 *
 * Этот класс используется для передачи информации о задаче, включая ее идентификатор,
 * заголовок, описание, статус, приоритет, адрес электронной почты исполнителя
 * и список комментариев. Он служит контейнером для данных, необходимых для
 * отображения задачи и ее комментариев в пользовательском интерфейсе.
 */
public class TaskAndCommentsForAssigneeFromDbDto {

    private static final Logger logger = LoggerFactory.getLogger(TaskAndCommentsForAssigneeFromDbDto.class);

    private Long id;
    private String title;
    private String description;
    private Status status;
    private Priority priority;
    private String emailAssignee;
    private List<String> comments;

    /**
     * Конструктор для создания объекта TaskAndCommentsForAssigneeFromDbDto.
     *
     * @param id идентификатор задачи
     * @param title заголовок задачи
     * @param description описание задачи
     * @param status статус задачи
     * @param priority приоритет задачи
     * @param emailAssignee адрес электронной почты исполнителя
     * @param comments список комментариев к задаче
     */
    public TaskAndCommentsForAssigneeFromDbDto(Long id,
                                               String title,
                                               String description,
                                               Status status,
                                               Priority priority,
                                               String emailAssignee,
                                               List<String> comments) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.emailAssignee = emailAssignee;
        this.comments = comments;

        logger.debug("Создан TaskAndCommentsForAssigneeFromDbDto: {}", this);
    }

    /**
     * Пустой конструктор.
     */
    public TaskAndCommentsForAssigneeFromDbDto() {
        logger.debug("Создан пустой TaskAndCommentsForAssigneeFromDbDto");
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

    public String getEmailAssignee() {
        logger.debug("Получение адреса электронной почты исполнителя: {}", emailAssignee);
        return emailAssignee;
    }

    public void setEmailAssignee(String emailAssignee) {
        logger.debug("Установка адреса электронной почты исполнителя: {}", emailAssignee);
        this.emailAssignee = emailAssignee;
    }

    public List<String> getComments() {
        logger.debug("Получение списка комментариев: {}", comments);
        return comments;
    }

    public void setComments(List<String> comments) {
        logger.debug("Установка списка комментариев: {}", comments);
        this.comments = comments;
    }
}