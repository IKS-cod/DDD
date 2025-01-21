package ddd.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Класс для представления комментария, полученного из базы данных.
 * <p>
 * Этот класс используется для передачи данных о комментариях, включая
 * идентификатор комментария, текст комментария и заголовок связанного задания.
 */
public class CommentFromDbDto {

    private static final Logger logger = LoggerFactory.getLogger(CommentFromDbDto.class);

    private Long id;
    private String text;
    private String titleTask;

    /**
     * Получает идентификатор комментария.
     *
     * @return идентификатор комментария
     */
    public Long getId() {
        return id;
    }

    /**
     * Устанавливает идентификатор комментария.
     *
     * @param id идентификатор комментария
     */
    public void setId(Long id) {
        logger.debug("Установка идентификатора комментария: {}", id);
        this.id = id;
    }

    /**
     * Получает текст комментария.
     *
     * @return текст комментария
     */
    public String getText() {
        return text;
    }

    /**
     * Устанавливает текст комментария.
     *
     * @param text текст комментария
     */
    public void setText(String text) {
        logger.debug("Установка текста комментария: {}", text);
        this.text = text;
    }

    /**
     * Получает заголовок связанного задания.
     *
     * @return заголовок задания
     */
    public String getTitleTask() {
        return titleTask;
    }

    /**
     * Устанавливает заголовок связанного задания.
     *
     * @param titleTask заголовок задания
     */
    public void setTitleTask(String titleTask) {
        logger.debug("Установка заголовка задания: {}", titleTask);
        this.titleTask = titleTask;
    }
}