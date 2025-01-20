package DDD.repository;

import DDD.model.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository
//public interface TaskRepository extends JpaRepository<Task, Long> {
//    Page<Task> findByAuthorId(Long authorId, Pageable pageable);
//
//    Page<Task> findByAssigneeId(Long assigneeId, Pageable pageable);
//
//}

/**
 * Репозиторий для доступа к задачам в базе данных.
 * <p>
 * Этот интерфейс расширяет JpaRepository, что позволяет выполнять стандартные операции CRUD
 * для сущности Task. Также включает в себя методы для поиска задач по идентификатору автора и исполнителя.
 * </p>
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    /**
     * Находит страницы задач, созданных указанным автором.
     *
     * @param authorId идентификатор автора задач
     * @param pageable объект, содержащий информацию о странице и размере страницы
     * @return страница задач, созданных автором с указанным идентификатором
     */
    Page<Task> findByAuthorId(Long authorId, Pageable pageable);

    /**
     * Находит страницы задач, назначенных указанному исполнителю.
     *
     * @param assigneeId идентификатор исполнителя задач
     * @param pageable объект, содержащий информацию о странице и размере страницы
     * @return страница задач, назначенных исполнителю с указанным идентификатором
     */
    Page<Task> findByAssigneeId(Long assigneeId, Pageable pageable);
}