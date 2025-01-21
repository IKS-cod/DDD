package ddd.model;

import ddd.enums.Priority;
import ddd.enums.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Сущность для представления задачи в системе.
 * <p>
 * Этот класс представляет задачу, содержащую информацию о заголовке,
 * описании, статусе, приоритете, авторе и исполнителе задачи. Также
 * включает список комментариев, связанных с задачей.
 */
@Entity
public class Task {

    private static final Logger logger = LoggerFactory.getLogger(Task.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private UserInfo author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignee_id")
    private UserInfo assignee;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private List<Comments> comments = new ArrayList<>();

    /**
     * Пустой конструктор.
     */
    public Task() {
        logger.debug("Создан пустой объект Task.");
    }

    /**
     * Конструктор для создания задачи.
     *
     * @param id          идентификатор задачи
     * @param title       заголовок задачи
     * @param description описание задачи
     * @param status      статус задачи
     * @param priority    приоритет задачи
     * @param author      автор задачи
     * @param assignee    исполнитель задачи
     * @param comments    список комментариев к задаче
     */
    public Task(Long id, String title, String description, Status status, Priority priority,
                UserInfo author, UserInfo assignee, List<Comments> comments) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.author = author;
        this.assignee = assignee;
        this.comments = comments;
        logger.debug("Создан объект Task: {}", this);
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

    public UserInfo getAuthor() {
        logger.debug("Получение автора задачи: {}", author);
        return author;
    }

    public void setAuthor(UserInfo author) {
        logger.debug("Установка автора задачи: {}", author);
        this.author = author;
    }

    public UserInfo getAssignee() {
        logger.debug("Получение исполнителя задачи: {}", assignee);
        return assignee;
    }

    public void setAssignee(UserInfo assignee) {
        logger.debug("Установка исполнителя задачи: {}", assignee);
        this.assignee = assignee;
    }

    public List<Comments> getComments() {
        logger.debug("Получение списка комментариев к задаче.");
        return comments;
    }

    public void setComments(List<Comments> comments) {
        logger.debug("Установка списка комментариев к задаче.");
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", priority=" + priority +
                ", author=" + author +
                ", assignee=" + assignee +
                '}';
    }
}