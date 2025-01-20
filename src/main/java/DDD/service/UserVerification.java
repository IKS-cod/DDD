package DDD.service;

import DDD.exception.TaskNotFoundException;
import DDD.model.Task;
import DDD.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

//@Service
//public class UserVerification {
//    private final TaskRepository taskRepository;
//
//    public UserVerification(TaskRepository taskRepository) {
//        this.taskRepository = taskRepository;
//    }
//
//    public boolean verificationUserForComment(Long taskId) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        Task task = taskRepository.findById(taskId)
//                .orElseThrow(() -> new TaskNotFoundException("Task not found with ID: " + taskId));
//
//        boolean hasPermission = task.getAssignee().getEmail().equals(authentication.getName());
//
//        return hasPermission;
//    }
//
//
//}


/**
 * Сервис для проверки прав пользователей.
 *
 * Этот класс отвечает за верификацию прав пользователя на выполнение
 * определенных действий, таких как добавление комментариев к задачам.
 */
@Service
public class UserVerification {

    private static final Logger logger = LoggerFactory.getLogger(UserVerification.class);

    private final TaskRepository taskRepository;

    /**
     * Конструктор для создания экземпляра UserVerification.
     *
     * @param taskRepository репозиторий для работы с задачами
     */
    public UserVerification(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /**
     * Проверяет, имеет ли текущий аутентифицированный пользователь право
     * добавлять комментарии к указанной задаче.
     *
     * @param taskId идентификатор задачи, к которой проверяется доступ
     * @return true, если пользователь имеет право добавлять комментарии; false в противном случае
     */
    public boolean verificationUserForComment(Long taskId) {
        logger.debug("Проверка прав пользователя на добавление комментария к задаче с ID: {}", taskId);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> {
                    logger.error("Задача с ID {} не найдена.", taskId);
                    return new TaskNotFoundException("Task not found with ID: " + taskId);
                });

        boolean hasPermission = task.getAssignee().getEmail().equals(authentication.getName());

        if (hasPermission) {
            logger.info("Пользователь {} имеет право добавлять комментарии к задаче с ID: {}",
                    authentication.getName(), taskId);
        } else {
            logger.warn("Пользователь {} не имеет права добавлять комментарии к задаче с ID: {}",
                    authentication.getName(), taskId);
        }

        return hasPermission;
    }
}