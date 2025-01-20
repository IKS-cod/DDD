package DDD.controller;

import DDD.dto.*;
import DDD.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

//@RestController
//@RequestMapping("/tasks")
//@Tag(name = "Задания")
//public class TaskController {
//
//    private final TaskService taskService;
//
//    public TaskController(TaskService taskService) {
//        this.taskService = taskService;
//    }
//
//    // создание задания
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    @PostMapping
//    @Operation(summary = "Создание задания")
//    public TaskFromDbDto createTask(@RequestBody CreateTaskDto createTaskDto) {
//        return taskService.createTask(createTaskDto);
//    }
//
//    //получение задания по id
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    @GetMapping("/{id}")
//    @Operation(summary = "Получение информации о задании по id")
//    public TaskWithCommentsFromDbDto getTask(@PathVariable Long id) {
//        return taskService.getTaskById(id);
//    }
//
//    // ADMIN обновление задания по id
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    @PutMapping("/{id}")
//    @Operation(summary = "Обновление информации задания по id")
//    public TaskFromDbDto updateTask(@PathVariable Long id, @RequestBody UpdateTaskDto updateTaskDto) {
//        return taskService.updateTask(id, updateTaskDto);
//    }
//
//    // USER обновление статуса задания по id
//    @PreAuthorize("@userVerification.verificationUserForComment(#id) || hasAuthority('ROLE_ADMIN')")
//    @PutMapping("/status/{id}")
//    @Operation(summary = "Обновление статуса задания по id")
//    public TaskFromDbDto updateTaskStatus(@PathVariable Long id, @RequestBody StatusTaskDto statusTaskDto) {
//        return taskService.updateTaskStatus(id, statusTaskDto);
//    }
//
//    // удаление задания по id
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    @DeleteMapping("/{id}")
//    @Operation(summary = "Удаление задания по id")
//    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
//        taskService.deleteTask(id);
//        return ResponseEntity.noContent().build();
//    }
//
//    // Получение задач по автору
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    @GetMapping("/author/{authorId}")
//    @Operation(summary = "Получение заданий с комментариями по автору")
//    public ResponseEntity<Page<TaskAndCommentsForAuthorFromDbDto>> getTasksByAuthor(
//            @PathVariable Long authorId,
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size) {
//        Page<TaskAndCommentsForAuthorFromDbDto> tasks = taskService.getTasksAndCommentsByAuthor(authorId, page, size);
//        return ResponseEntity.ok(tasks);
//    }
//
//    //  Получение задач по исполнителю
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    @GetMapping("/assignee/{assigneeId}")
//    @Operation(summary = "Получение заданий с комментариями по исполнителю")
//    public ResponseEntity<Page<TaskAndCommentsForAssigneeFromDbDto>> getTasksByAssignee(
//            @PathVariable Long assigneeId,
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size) {
//        Page<TaskAndCommentsForAssigneeFromDbDto> tasks = taskService.getTasksAndCommentsByAssignee(assigneeId, page, size);
//        return ResponseEntity.ok(tasks);
//    }
//}
/**
 * Контроллер для обработки запросов, связанных с заданиями.
 *
 * Этот класс предоставляет конечные точки для управления заданиями в системе,
 * включая создание, получение, обновление и удаление заданий.
 * Он также позволяет получать задания по автору или исполнителю.
 *
 * Доступ к методам контроллера ограничен ролями пользователей:
 * - Только администраторы могут создавать, обновлять и удалять задания.
 * - Пользователи могут обновлять статус заданий, если они имеют соответствующие права.
 *
 * Основные функции:
 * - Создание нового задания
 * - Получение информации о задании по ID
 * - Обновление информации о задании
 * - Обновление статуса задания
 * - Удаление задания
 * - Получение заданий по автору
 * - Получение заданий по исполнителю
 */
@RestController
@RequestMapping("/tasks")
@Tag(name = "Задания")
/**
 * Контроллер для обработки запросов, связанных с заданиями.
 *
 * Этот класс предоставляет конечные точки для создания, получения, обновления и удаления заданий.
 * Он также позволяет получать задания по автору или исполнителю.
 * Доступ к методам контроллера ограничен ролями пользователей.
 *
 * Основные функции:
 * - Создание задания
 * - Получение информации о задании по ID
 * - Обновление информации о задании
 * - Обновление статуса задания
 * - Удаление задания
 * - Получение заданий по автору
 * - Получение заданий по исполнителю
 */
public class TaskController {

    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * Создание нового задания.
     *
     * @param createTaskDto данные для создания задания
     * @return созданное задание
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping
    @Operation(summary = "Создание задания")
    public TaskFromDbDto createTask(@RequestBody CreateTaskDto createTaskDto) {
        logger.info("Запрос на создание задания: {}", createTaskDto);
        TaskFromDbDto createdTask = taskService.createTask(createTaskDto);
        logger.info("Задание успешно создано: {}", createdTask);
        return createdTask;
    }

    /**
     * Получение информации о задании по ID.
     *
     * @param id идентификатор задания
     * @return информация о задании
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/{id}")
    @Operation(summary = "Получение информации о задании по id")
    public TaskWithCommentsFromDbDto getTask(@PathVariable Long id) {
        logger.info("Запрос на получение задания с ID: {}", id);
        TaskWithCommentsFromDbDto task = taskService.getTaskById(id);
        logger.info("Задание получено: {}", task);
        return task;
    }

    /**
     * Обновление информации о задании по ID.
     *
     * @param id идентификатор задания
     * @param updateTaskDto данные для обновления задания
     * @return обновленное задание
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/{id}")
    @Operation(summary = "Обновление информации задания по id")
    public TaskFromDbDto updateTask(@PathVariable Long id, @RequestBody UpdateTaskDto updateTaskDto) {
        logger.info("Запрос на обновление задания с ID: {}", id);
        TaskFromDbDto updatedTask = taskService.updateTask(id, updateTaskDto);
        logger.info("Задание успешно обновлено: {}", updatedTask);
        return updatedTask;
    }

    /**
     * Обновление статуса задания по ID.
     *
     * @param id идентификатор задания
     * @param statusTaskDto данные для обновления статуса задания
     * @return задание с обновленным статусом
     */
    @PreAuthorize("@userVerification.verificationUserForComment(#id) || hasAuthority('ROLE_ADMIN')")
    @PutMapping("/status/{id}")
    @Operation(summary = "Обновление статуса задания по id")
    public TaskFromDbDto updateTaskStatus(@PathVariable Long id, @RequestBody StatusTaskDto statusTaskDto) {
        logger.info("Запрос на обновление статуса задания с ID: {}", id);
        TaskFromDbDto updatedStatusTask = taskService.updateTaskStatus(id, statusTaskDto);
        logger.info("Статус задания успешно обновлен: {}", updatedStatusTask);
        return updatedStatusTask;
    }

    /**
     * Удаление задания по ID.
     *
     * @param id идентификатор задания
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление задания по id")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        logger.info("Запрос на удаление задания с ID: {}", id);
        taskService.deleteTask(id);
        logger.info("Задание с ID {} успешно удалено", id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Получение заданий по автору.
     *
     * @param authorId идентификатор автора
     * @param page номер страницы для пагинации
     * @param size размер страницы для пагинации
     * @return страница заданий и комментариев по автору
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/author/{authorId}")
    @Operation(summary = "Получение заданий с комментариями по автору")
    public ResponseEntity<Page<TaskAndCommentsForAuthorFromDbDto>> getTasksByAuthor(
            @PathVariable Long authorId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        logger.info("Запрос на получение заданий автора с ID: {}", authorId);
        Page<TaskAndCommentsForAuthorFromDbDto> tasks = taskService.getTasksAndCommentsByAuthor(authorId, page, size);
        logger.info("Задания автора успешно получены");
        return ResponseEntity.ok(tasks);
    }

    /**
     * Получение заданий по исполнителю.
     *
     * @param assigneeId идентификатор исполнителя
     * @param page номер страницы для пагинации
     * @param size размер страницы для пагинации
     * @return страница заданий и комментариев по исполнителю
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/assignee/{assigneeId}")
    @Operation(summary = "Получение заданий с комментариями по исполнителю")
    public ResponseEntity<Page<TaskAndCommentsForAssigneeFromDbDto>> getTasksByAssignee(
            @PathVariable Long assigneeId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        logger.info("Запрос на получение заданий исполнителя с ID: {}", assigneeId);
        Page<TaskAndCommentsForAssigneeFromDbDto> tasks = taskService.getTasksAndCommentsByAssignee(assigneeId, page, size);
        logger.info("Задания исполнителя успешно получены");
        return ResponseEntity.ok(tasks);
    }
}
