package ddd.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Класс для представления данных комментария при его создании или обновлении.
 * <p>
 * Этот класс используется для передачи текста комментария при создании
 * нового комментария или обновлении существующего. Он служит контейнером
 * для данных, необходимых для этих операций.
 */
public class CreateOrUpdateCommentsDto {

    private static final Logger logger = LoggerFactory.getLogger(CreateOrUpdateCommentsDto.class);

    private String text;

    /**
     * Получает текст комментария.
     *
     * @return текст комментария
     */
    public String getText() {
        logger.debug("Получение текста комментария: {}", text);
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