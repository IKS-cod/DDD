package ddd.model;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.Objects;

/**
 * Сущность для представления комментариев в системе.
 * <p>
 * Этот класс представляет комментарии, связанные с задачами. Каждый комментарий
 * имеет уникальный идентификатор, текст и связь с задачей, к которой он относится.
 */
@Entity
public class Comments {

    private static final Logger logger = LoggerFactory.getLogger(Comments.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    private Task task;

    /**
     * Пустой конструктор.
     */
    public Comments() {
        logger.debug("Создан пустой комментарий.");
    }

    /**
     * Конструктор для создания комментария.
     *
     * @param id   идентификатор комментария
     * @param text текст комментария
     * @param task задача, к которой относится комментарий
     */
    public Comments(Long id, String text, Task task) {
        this.id = id;
        this.text = text;
        this.task = task;
        logger.debug("Создан комментарий: {}", this);
    }

    public Long getId() {
        logger.debug("Получение идентификатора комментария: {}", id);
        return id;
    }

    public void setId(Long id) {
        logger.debug("Установка идентификатора комментария: {}", id);
        this.id = id;
    }

    public String getText() {
        logger.debug("Получение текста комментария: {}", text);
        return text;
    }

    public void setText(String text) {
        logger.debug("Установка текста комментария: {}", text);
        this.text = text;
    }

    public Task getTask() {
        logger.debug("Получение задачи для комментария: {}", task);
        return task;
    }

    public void setTask(Task task) {
        logger.debug("Установка задачи для комментария: {}", task);
        this.task = task;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comments comments = (Comments) o;
        return Objects.equals(id, comments.id) && Objects.equals(text, comments.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }
}