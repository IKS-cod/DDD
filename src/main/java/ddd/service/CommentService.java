package ddd.service;

import ddd.dto.CommentFromDbDto;
import ddd.dto.CreateOrUpdateCommentsDto;
import ddd.exception.TaskNotFoundException;
import ddd.model.Comments;
import ddd.model.Task;
import ddd.repository.CommentRepository;
import ddd.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


/**
 * Сервис для управления комментариями.
 * <p>
 * Этот класс отвечает за создание и управление комментариями, связанными с задачами.
 * Он использует репозитории для доступа к данным и обработки бизнес-логики.
 */
@Service
public class CommentService {

    private static final Logger logger = LoggerFactory.getLogger(CommentService.class);

    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;

    /**
     * Конструктор для создания экземпляра CommentService.
     *
     * @param commentRepository репозиторий для работы с комментариями
     * @param taskRepository    репозиторий для работы с задачами
     */
    public CommentService(CommentRepository commentRepository, TaskRepository taskRepository) {
        this.commentRepository = commentRepository;
        this.taskRepository = taskRepository;
    }

    /**
     * Создает новый комментарий для указанной задачи.
     * <p>
     * Проверяет наличие задачи по ее идентификатору. Если задача найдена,
     * создаёт новый комментарий, связывает его с задачей и сохраняет в базе данных.
     *
     * @param id                        идентификатор задачи, к которой добавляется комментарий
     * @param createOrUpdateCommentsDto объект, содержащий информацию о новом комментарии
     * @return объект CommentFromDbDto, содержащий информацию о созданном комментарии
     */
    public CommentFromDbDto createComment(Long id, CreateOrUpdateCommentsDto createOrUpdateCommentsDto) {
        logger.debug("Создание комментария для задачи с идентификатором: {}", id);

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Задача с идентификатором {} не найдена.", id);
                    return new TaskNotFoundException();
                });

        Comments commentsForDb = new Comments();
        commentsForDb.setTask(task);
        commentsForDb.setText(createOrUpdateCommentsDto.getText());

        Comments commentsFromDb = commentRepository.save(commentsForDb);

        CommentFromDbDto commentFromDbDto = new CommentFromDbDto();
        commentFromDbDto.setId(commentsFromDb.getId());
        commentFromDbDto.setText(commentsFromDb.getText());
        commentFromDbDto.setTitleTask(commentsFromDb.getTask().getTitle());

        logger.info("Комментарий успешно создан: {}", commentFromDbDto);

        return commentFromDbDto;
    }
}