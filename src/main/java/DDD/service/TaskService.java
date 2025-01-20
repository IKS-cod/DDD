package DDD.service;

import DDD.dto.*;
import DDD.exception.TaskNotFoundException;
import DDD.exception.UserNotFoundException;
import DDD.model.Comments;
import DDD.model.Task;
import DDD.model.UserInfo;
import DDD.repository.CommentRepository;
import DDD.repository.TaskRepository;
import DDD.repository.UserInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//@Service
//public class TaskService {
//
//
//    private final TaskRepository taskRepository;
//    private final UserInfoRepository userInfoRepository;
//    private final CommentRepository commentRepository;
//
//    public TaskService(TaskRepository taskRepository, UserInfoRepository userInfoRepository, CommentRepository commentRepository) {
//        this.taskRepository = taskRepository;
//        this.userInfoRepository = userInfoRepository;
//        this.commentRepository = commentRepository;
//    }
//
//    public TaskFromDbDto createTask(CreateTaskDto createTaskDto) {
//        Task taskForDb = new Task();
//        taskForDb.setTitle(createTaskDto.getTitle());
//        taskForDb.setDescription(createTaskDto.getDescription());
//        taskForDb.setPriority(createTaskDto.getPriority());
//        UserInfo userInfoFromDbAssignee = userInfoRepository.findByEmail(createTaskDto.getEmailAssignee())
//                .orElseThrow(UserNotFoundException::new);
//        taskForDb.setAssignee(userInfoFromDbAssignee);
//        UserInfo userInfoFromDbAuthor = userInfoRepository.findByEmail(createTaskDto.getEmailAuthor())
//                .orElseThrow(UserNotFoundException::new);
//        taskForDb.setAuthor(userInfoFromDbAuthor);
//        taskForDb.setStatus(createTaskDto.getStatus());
//        Task taskFromDb = taskRepository.save(taskForDb);
//
//        TaskFromDbDto taskFromDbDto = new TaskFromDbDto();
//        taskFromDbDto.setTitle(taskFromDb.getTitle());
//        taskFromDbDto.setDescription(taskFromDb.getDescription());
//        taskFromDbDto.setStatus(taskFromDb.getStatus());
//        taskFromDbDto.setPriority(taskFromDb.getPriority());
//        taskFromDbDto.setEmailAssignee(taskFromDb.getAssignee().getEmail());
//        taskFromDbDto.setEmailAuthor(taskFromDb.getAuthor().getEmail());
//        taskFromDbDto.setId(taskFromDb.getId());
//        return taskFromDbDto;
//    }
//
//    public TaskWithCommentsFromDbDto getTaskById(Long id) {
//        if (taskRepository.findById(id).isPresent()) {
//            Task taskFromDb = taskRepository.findById(id).get();
//            TaskWithCommentsFromDbDto taskWithCommentsFromDbDto = new TaskWithCommentsFromDbDto();
//            taskWithCommentsFromDbDto.setId(taskFromDb.getId());
//            taskWithCommentsFromDbDto.setDescription(taskFromDb.getDescription());
//            taskWithCommentsFromDbDto.setPriority(taskFromDb.getPriority());
//            taskWithCommentsFromDbDto.setStatus(taskFromDb.getStatus());
//            taskWithCommentsFromDbDto.setTitle(taskFromDb.getTitle());
//            taskWithCommentsFromDbDto.setEmailAssignee(taskFromDb.getAssignee().getEmail());
//            taskWithCommentsFromDbDto.setEmailAuthor(taskFromDb.getAuthor().getEmail());
//            List<String> stringList = commentRepository.getCommentTexts(id);
//            List<CommentTextFromDbDto> commentsList = new ArrayList<>();
//            for(String e: stringList){
//                commentsList.add(new CommentTextFromDbDto(e));
//            }
//            taskWithCommentsFromDbDto.setComments(commentsList);
//            return taskWithCommentsFromDbDto;
//        } else {
//            throw new TaskNotFoundException();
//        }
//    }
//
//    public TaskFromDbDto updateTask(Long id, UpdateTaskDto updateTaskDto) {
//        if (taskRepository.findById(id).isPresent()) {
//            Task taskFromDb = taskRepository.findById(id).get();
//            UserInfo userInfoFromDbAssignee = userInfoRepository.findByEmail(updateTaskDto.getEmailAssignee())
//                    .orElseThrow(UserNotFoundException::new);
//            taskFromDb.setAssignee(userInfoFromDbAssignee);
//            taskFromDb.setDescription(updateTaskDto.getDescription());
//            taskFromDb.setPriority(updateTaskDto.getPriority());
//            taskFromDb.setStatus(updateTaskDto.getStatus());
//            taskFromDb.setTitle(updateTaskDto.getTitle());
//            taskRepository.save(taskFromDb);
//            Task taskFromDbNew = taskRepository.findById(id).get();
//            TaskFromDbDto taskFromDbDto = new TaskFromDbDto();
//            taskFromDbDto.setId(taskFromDbNew.getId());
//            taskFromDbDto.setDescription(taskFromDbNew.getDescription());
//            taskFromDbDto.setPriority(taskFromDbNew.getPriority());
//            taskFromDbDto.setStatus(taskFromDbNew.getStatus());
//            taskFromDbDto.setTitle(taskFromDbNew.getTitle());
//            taskFromDbDto.setEmailAssignee(taskFromDbNew.getAssignee().getEmail());
//            taskFromDbDto.setEmailAuthor(taskFromDbNew.getAuthor().getEmail());
//            return taskFromDbDto;
//        } else {
//            throw new TaskNotFoundException();
//        }
//    }
//
//    public TaskFromDbDto updateTaskStatus(Long id, StatusTaskDto statusTaskDto) {
//        if (taskRepository.findById(id).isPresent()) {
//            Task taskFromDb = taskRepository.findById(id).get();
//            taskFromDb.setStatus(statusTaskDto.getStatus());
//            taskRepository.save(taskFromDb);
//            Task taskFromDbNew = taskRepository.findById(id).get();
//            TaskFromDbDto taskFromDbDto = new TaskFromDbDto();
//            taskFromDbDto.setId(taskFromDbNew.getId());
//            taskFromDbDto.setDescription(taskFromDbNew.getDescription());
//            taskFromDbDto.setPriority(taskFromDbNew.getPriority());
//            taskFromDbDto.setStatus(taskFromDbNew.getStatus());
//            taskFromDbDto.setTitle(taskFromDbNew.getTitle());
//            taskFromDbDto.setEmailAssignee(taskFromDbNew.getAssignee().getEmail());
//            taskFromDbDto.setEmailAuthor(taskFromDbNew.getAuthor().getEmail());
//            return taskFromDbDto;
//        } else {
//            throw new TaskNotFoundException();
//        }
//    }
//
//    public void deleteTask(Long id) {
//        if (!taskRepository.existsById(id)) {
//            throw new TaskNotFoundException();
//        }
//        commentRepository.deleteByTaskId(id);
//        taskRepository.deleteById(id);
//    }
//
//    public Page<TaskAndCommentsForAuthorFromDbDto> getTasksAndCommentsByAuthor(Long authorId, int page, int size) {
//        Pageable pageable = PageRequest.of(page, size);
//
//        // Fetch tasks by author ID
//        Page<Task> tasksPage = taskRepository.findByAuthorId(authorId, pageable);
//
//        // Map tasks to DTOs
//        return tasksPage.map(task -> {
//            List<String> comments = task.getComments().stream()
//                    .map(Comments::getText) // Assuming Comments has a getText method
//                    .collect(Collectors.toList());
//            return new TaskAndCommentsForAuthorFromDbDto(task.getId(),
//                    task.getTitle(),
//                    task.getDescription(),
//                    task.getStatus(),
//                    task.getPriority(),
//                    task.getAuthor().getEmail(),
//                    comments);
//        });
//    }
//
//    public Page<TaskAndCommentsForAssigneeFromDbDto> getTasksAndCommentsByAssignee(Long authorId, int page, int size) {
//        Pageable pageable = PageRequest.of(page, size);
//        Page<Task> tasksPage = taskRepository.findByAssigneeId(authorId, pageable);
//        return tasksPage.map(task -> {
//            List<String> comments = task.getComments().stream()
//                    .map(Comments::getText) // Assuming Comments has a getText method
//                    .collect(Collectors.toList());
//            return new TaskAndCommentsForAssigneeFromDbDto(task.getId(),
//                    task.getTitle(),
//                    task.getDescription(),
//                    task.getStatus(),
//                    task.getPriority(),
//                    task.getAssignee().getEmail(),
//                    comments);
//        });
//    }
//}

/**
 * Сервис для управления задачами и комментариями.
 *
 * Этот класс отвечает за создание, обновление, получение и удаление задач,
 * а также за управление комментариями, связанными с задачами. Он использует
 * репозитории для доступа к данным и обработки бизнес-логики.
 */

@Service
public class TaskService {

    private static final Logger logger = LoggerFactory.getLogger(TaskService.class);

    private final TaskRepository taskRepository;
    private final UserInfoRepository userInfoRepository;
    private final CommentRepository commentRepository;


/**
     * Конструктор для создания экземпляра TaskService.
     *
     * @param taskRepository репозиторий для работы с задачами
     * @param userInfoRepository репозиторий для работы с пользователями
     * @param commentRepository репозиторий для работы с комментариями
     */

    public TaskService(TaskRepository taskRepository, UserInfoRepository userInfoRepository, CommentRepository commentRepository) {
        this.taskRepository = taskRepository;
        this.userInfoRepository = userInfoRepository;
        this.commentRepository = commentRepository;
    }


/**
     * Создает новую задачу.
     *
     * @param createTaskDto объект, содержащий информацию о новой задаче
     * @return объект TaskFromDbDto, содержащий информацию о созданной задаче
     */

    public TaskFromDbDto createTask(CreateTaskDto createTaskDto) {
        logger.debug("Создание новой задачи: {}", createTaskDto);

        Task taskForDb = new Task();
        taskForDb.setTitle(createTaskDto.getTitle());
        taskForDb.setDescription(createTaskDto.getDescription());
        taskForDb.setPriority(createTaskDto.getPriority());

        UserInfo userInfoFromDbAssignee = userInfoRepository.findByEmail(createTaskDto.getEmailAssignee())
                .orElseThrow(() -> {
                    logger.error("Пользователь с email {} не найден.", createTaskDto.getEmailAssignee());
                    return new UserNotFoundException();
                });
        taskForDb.setAssignee(userInfoFromDbAssignee);

        UserInfo userInfoFromDbAuthor = userInfoRepository.findByEmail(createTaskDto.getEmailAuthor())
                .orElseThrow(() -> {
                    logger.error("Пользователь с email {} не найден.", createTaskDto.getEmailAuthor());
                    return new UserNotFoundException();
                });
        taskForDb.setAuthor(userInfoFromDbAuthor);

        taskForDb.setStatus(createTaskDto.getStatus());

        Task taskFromDb = taskRepository.save(taskForDb);

        TaskFromDbDto taskFromDbDto = new TaskFromDbDto();
        taskFromDbDto.setId(taskFromDb.getId());
        taskFromDbDto.setTitle(taskFromDb.getTitle());
        taskFromDbDto.setDescription(taskFromDb.getDescription());
        taskFromDbDto.setStatus(taskFromDb.getStatus());
        taskFromDbDto.setPriority(taskFromDb.getPriority());
        taskFromDbDto.setEmailAssignee(taskFromDb.getAssignee().getEmail());
        taskFromDbDto.setEmailAuthor(taskFromDb.getAuthor().getEmail());

        logger.info("Задача успешно создана: {}", taskFromDbDto);

        return taskFromDbDto;
    }


/**
     * Получает задачу по ее идентификатору.
     *
     * @param id идентификатор задачи
     * @return объект TaskWithCommentsFromDbDto, содержащий информацию о задаче и комментариях
     */

    public TaskWithCommentsFromDbDto getTaskById(Long id) {
        logger.debug("Получение задачи с идентификатором: {}", id);

        Task taskFromDb = taskRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Задача с идентификатором {} не найдена.", id);
                    return new TaskNotFoundException();
                });

        TaskWithCommentsFromDbDto taskWithCommentsFromDbDto = new TaskWithCommentsFromDbDto();
        taskWithCommentsFromDbDto.setId(taskFromDb.getId());
        taskWithCommentsFromDbDto.setDescription(taskFromDb.getDescription());
        taskWithCommentsFromDbDto.setPriority(taskFromDb.getPriority());
        taskWithCommentsFromDbDto.setStatus(taskFromDb.getStatus());
        taskWithCommentsFromDbDto.setTitle(taskFromDb.getTitle());
        taskWithCommentsFromDbDto.setEmailAssignee(taskFromDb.getAssignee().getEmail());
        taskWithCommentsFromDbDto.setEmailAuthor(taskFromDb.getAuthor().getEmail());

        List<String> stringList = commentRepository.getCommentTexts(id);
        List<CommentTextFromDbDto> commentsList = stringList.stream()
                .map(CommentTextFromDbDto::new)
                .collect(Collectors.toList());

        taskWithCommentsFromDbDto.setComments(commentsList);

        logger.info("Задача успешно получена: {}", taskWithCommentsFromDbDto);

        return taskWithCommentsFromDbDto;
    }


/**
     * Обновляет существующую задачу.
     *
     * @param id идентификатор задачи для обновления
     * @param updateTaskDto объект, содержащий новую информацию о задаче
     * @return объект TaskFromDbDto, содержащий обновленную информацию о задаче
     */

    public TaskFromDbDto updateTask(Long id, UpdateTaskDto updateTaskDto) {
        logger.debug("Обновление задачи с идентификатором: {}", id);

        Task taskFromDb = taskRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Задача с идентификатором {} не найдена.", id);
                    return new TaskNotFoundException();
                });

        UserInfo userInfoFromDbAssignee = userInfoRepository.findByEmail(updateTaskDto.getEmailAssignee())
                .orElseThrow(() -> {
                    logger.error("Пользователь с email {} не найден.", updateTaskDto.getEmailAssignee());
                    return new UserNotFoundException();
                });

        // Обновление полей задачи
        taskFromDb.setAssignee(userInfoFromDbAssignee);
        taskFromDb.setDescription(updateTaskDto.getDescription());
        taskFromDb.setPriority(updateTaskDto.getPriority());
        taskFromDb.setStatus(updateTaskDto.getStatus());
        taskFromDb.setTitle(updateTaskDto.getTitle());

        // Сохранение обновленной задачи в базе данных
        Task updatedTask = taskRepository.save(taskFromDb);

        // Создание DTO для возвращаемой информации
        TaskFromDbDto updatedTaskDTO = new TaskFromDbDto();
        updatedTaskDTO.setId(updatedTask.getId());
        updatedTaskDTO.setDescription(updatedTask.getDescription());
        updatedTaskDTO.setPriority(updatedTask.getPriority());
        updatedTaskDTO.setStatus(updatedTask.getStatus());
        updatedTaskDTO.setTitle(updatedTask.getTitle());
        updatedTaskDTO.setEmailAssignee(updatedTask.getAssignee().getEmail());
        updatedTaskDTO.setEmailAuthor(updatedTask.getAuthor().getEmail());

        logger.info("Задача успешно обновлена: {}", updatedTaskDTO);

        return updatedTaskDTO;
    }


/**
     * Обновляет статус существующей задачи.
     *
     * @param id идентификатор задачи для обновления статуса
     * @param statusTaskDto объект, содержащий новый статус задачи
     * @return объект TaskFromDbDto, содержащий обновленную информацию о задаче
     */

    public TaskFromDbDto updateTaskStatus(Long id, StatusTaskDto statusTaskDto) {
        logger.debug("Обновление статуса задачи с идентификатором: {}", id);

        Task taskFromDb = taskRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Задача с идентификатором {} не найдена.", id);
                    return new TaskNotFoundException();
                });

        // Обновление статуса задачи
        taskFromDb.setStatus(statusTaskDto.getStatus());

        // Сохранение обновленной задачи в базе данных
        Task updatedStatusTask = taskRepository.save(taskFromDb);

        // Создание DTO для возвращаемой информации
        TaskFromDbDto updatedStatusDTO = new TaskFromDbDto();
        updatedStatusDTO.setId(updatedStatusTask.getId());
        updatedStatusDTO.setDescription(updatedStatusTask.getDescription());
        updatedStatusDTO.setPriority(updatedStatusTask.getPriority());
        updatedStatusDTO.setStatus(updatedStatusTask.getStatus());
        updatedStatusDTO.setTitle(updatedStatusTask.getTitle());
        updatedStatusDTO.setEmailAssignee(updatedStatusTask.getAssignee().getEmail());
        updatedStatusDTO.setEmailAuthor(updatedStatusTask.getAuthor().getEmail());

        logger.info("Статус задачи успешно обновлен: {}", updatedStatusDTO);

        return updatedStatusDTO;
    }


/**
     * Удаляет задачу по ее идентификатору.
     *
     * @param id идентификатор задачи для удаления
     */

    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            logger.error("Попытка удалить несуществующую задачу с идентификатором: {}", id);
            throw new TaskNotFoundException();
        }

        // Удаление всех комментариев, связанных с задачей
        commentRepository.deleteByTaskId(id);

        // Удаление самой задачи
        taskRepository.deleteById(id);

        logger.info("Задача с идентификатором {} успешно удалена.", id);
    }


/**
     * Получает страницы задач и комментариев по автору.
     *
     * @param authorId идентификатор автора задач
     * @param page номер страницы для пагинации
     * @param size размер страницы (количество задач на странице)
     * @return страница DTO объектов задач и комментариев для данного автора
     */

    public Page<TaskAndCommentsForAuthorFromDbDto> getTasksAndCommentsByAuthor(Long authorId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        // Получение страниц задач по автору
        Page<Task> tasksPage = taskRepository.findByAuthorId(authorId, pageable);

        // Преобразование задач в DTO объекты
        return tasksPage.map(task -> {
            List<String> comments = task.getComments().stream()
                    .map(Comments::getText) // Предполагается наличие метода getText в Comments
                    .collect(Collectors.toList());

            return new TaskAndCommentsForAuthorFromDbDto(
                    task.getId(),
                    task.getTitle(),
                    task.getDescription(),
                    task.getStatus(),
                    task.getPriority(),
                    task.getAuthor().getEmail(),
                    comments);
        });
    }


/**
     * Получает страницы задач и комментариев по исполнителю.
     *
     * @param assigneeId идентификатор исполнителя задач
     * @param page номер страницы для пагинации
     * @param size размер страницы (количество задач на странице)
     * @return страница DTO объектов задач и комментариев для данного исполнителя
     */

    public Page<TaskAndCommentsForAssigneeFromDbDto> getTasksAndCommentsByAssignee(Long assigneeId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        // Получение страниц задач по исполнителю
        Page<Task> tasksPage = taskRepository.findByAssigneeId(assigneeId, pageable);

        // Преобразование задач в DTO объекты
        return tasksPage.map(task -> {
            List<String> comments = task.getComments().stream()
                    .map(Comments::getText) // Предполагается наличие метода getText в Comments
                    .collect(Collectors.toList());

            return new TaskAndCommentsForAssigneeFromDbDto(
                    task.getId(),
                    task.getTitle(),
                    task.getDescription(),
                    task.getStatus(),
                    task.getPriority(),
                    task.getAssignee().getEmail(),
                    comments);
        });
    }
}
