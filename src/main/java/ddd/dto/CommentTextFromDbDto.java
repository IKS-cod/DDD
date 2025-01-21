package ddd.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Класс для представления текста комментария, полученного из базы данных.
 * <p>
 * Этот класс используется для передачи текста комментария. Он может быть
 * использован в различных сценариях, таких как отображение комментариев
 * в пользовательском интерфейсе или передача данных между слоями приложения.
 */
public class CommentTextFromDbDto {

    private static final Logger logger = LoggerFactory.getLogger(CommentTextFromDbDto.class);

    private String text;

    /**
     * Конструктор для создания объекта CommentTextFromDbDto.
     *
     * @param text текст комментария
     */
    public CommentTextFromDbDto(String text) {
        this.text = text;
        logger.debug("Создан CommentTextFromDbDto с текстом: {}", text);
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
}