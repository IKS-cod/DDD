package DDD.controller;

import DDD.dto.CommentFromDbDto;
import DDD.dto.CreateOrUpdateCommentsDto;
import DDD.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

//@RestController
//@RequestMapping("/comments")
//@Tag(name = "Комментарии")
//public class CommentsController {
//    private CommentService commentService;
//
//    public CommentsController(CommentService commentService) {
//        this.commentService = commentService;
//    }
//    @PreAuthorize("@userVerification.verificationUserForComment(#id) || hasAuthority('ROLE_ADMIN')")
//    @PostMapping("/{id}")
//    @Operation(summary = "Создание комментария")
//    public CommentFromDbDto createTask(@PathVariable Long id, @RequestBody CreateOrUpdateCommentsDto createOrUpdateCommentsDto) {
//        return commentService.createComment(id, createOrUpdateCommentsDto);
//    }
//}
@RestController
@RequestMapping("/comments")
@Tag(name = "Комментарии")
public class CommentsController {

    private static final Logger logger = LoggerFactory.getLogger(CommentsController.class);
    private final CommentService commentService;

    public CommentsController(CommentService commentService) {
        this.commentService = commentService;
    }

    /**
     * Создание комментария для указанного ресурса.
     *
     * @param id идентификатор ресурса, к которому добавляется комментарий
     * @param createOrUpdateCommentsDto данные комментария, который нужно создать
     * @return созданный комментарий в формате CommentFromDbDto
     */
    @PreAuthorize("@userVerification.verificationUserForComment(#id) || hasAuthority('ROLE_ADMIN')")
    @PostMapping("/{id}")
    @Operation(summary = "Создание комментария")
    public ResponseEntity<CommentFromDbDto> createComment(
            @PathVariable Long id,
            @RequestBody CreateOrUpdateCommentsDto createOrUpdateCommentsDto) {

        logger.info("Запрос на создание комментария для ресурса с ID: {}", id);

        try {
            CommentFromDbDto createdComment = commentService.createComment(id, createOrUpdateCommentsDto);
            logger.info("Комментарий успешно создан: {}", createdComment);
            return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Ошибка при создании комментария для ресурса с ID: {}", id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
