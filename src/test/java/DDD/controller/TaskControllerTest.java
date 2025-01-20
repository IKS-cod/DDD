package DDD.controller;

import DDD.dto.AuthRequest;
import DDD.dto.CreateTaskDto;
import DDD.dto.TaskFromDbDto;
import DDD.dto.UpdateTaskDto;
import DDD.enums.Role;
import DDD.model.Task;
import DDD.model.UserInfo;
import DDD.repository.CommentRepository;
import DDD.repository.TaskRepository;
import DDD.repository.UserInfoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static DDD.enums.Priority.HIGH;
import static DDD.enums.Priority.LOW;
import static DDD.enums.Status.COMPLETED;
import static DDD.enums.Status.PENDING;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TaskControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private CommentRepository commentRepository;

    @BeforeEach
    public void setUp() {
        // очистка БД
        commentRepository.deleteAll();
        taskRepository.deleteAll();
        userInfoRepository.deleteAll();
    }

    @Test
    @DisplayName("Проверка метода по созданию задания")
    void createTaskTask() throws Exception {
        // Подготовка данных
        String emailUser1 = "user1@example.com";
        String emailAdmin = "admin@example.com";
        UserInfo userInfoUser = new UserInfo();
        userInfoUser.setRole(Role.USER);
        userInfoUser.setEmail(emailUser1);
        String password = "password";
        userInfoUser.setPassword(passwordEncoder.encode(password));
        userInfoRepository.save(userInfoUser);
        UserInfo userInfoAdmin = new UserInfo();
        userInfoAdmin.setRole(Role.ADMIN);
        userInfoAdmin.setEmail(emailAdmin);
        userInfoAdmin.setPassword(passwordEncoder.encode(password));
        userInfoRepository.save(userInfoAdmin);
        // Получение токена
        AuthRequest authRequest = new AuthRequest();
        authRequest.setEmail(emailAdmin);
        authRequest.setPassword(password);
        // Act
        ResponseEntity<String> response = testRestTemplate.postForEntity("/login", authRequest, String.class);

        // Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        String token = response.getBody();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        // Создание тестового объекта CreateTaskDto
        CreateTaskDto createTaskDto = new CreateTaskDto();
        // Заполните createTaskDto необходимыми данными
        createTaskDto.setDescription("test");
        createTaskDto.setEmailAssignee(emailUser1);
        createTaskDto.setEmailAuthor(emailAdmin);
        createTaskDto.setPriority(HIGH);
        createTaskDto.setStatus(PENDING);
        createTaskDto.setTitle("task");
        // check
        // Создание HTTP-запроса
        HttpEntity<CreateTaskDto> requestEntity = new HttpEntity<>(createTaskDto, headers);

        // Выполнение POST-запроса
        ResponseEntity<TaskFromDbDto> responseEntity = testRestTemplate.exchange(
                "/tasks",
                HttpMethod.POST,
                requestEntity,
                TaskFromDbDto.class
        );
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().getTitle()).isEqualTo(createTaskDto.getTitle());
    }

    @Test
    @DisplayName("Проверка метода по изменению задания")
    void updateTask() {
        // Подготовка данных
        //Создаем User и Admin
        String emailUser1 = "user1@example.com";
        String emailAdmin = "admin@example.com";
        UserInfo userInfoUser = new UserInfo();
        userInfoUser.setRole(Role.USER);
        userInfoUser.setEmail(emailUser1);
        String password = "password";
        userInfoUser.setPassword(passwordEncoder.encode(password));
        userInfoRepository.save(userInfoUser);
        UserInfo userInfoAdmin = new UserInfo();
        userInfoAdmin.setRole(Role.ADMIN);
        userInfoAdmin.setEmail(emailAdmin);
        userInfoAdmin.setPassword(passwordEncoder.encode(password));
        userInfoRepository.save(userInfoAdmin);
        //Создаем задание
        Task taskForDb = new Task();
        taskForDb.setAuthor(userInfoAdmin);
        taskForDb.setAssignee(userInfoUser);
        taskForDb.setStatus(PENDING);
        taskForDb.setDescription("TEST");
        taskForDb.setPriority(HIGH);
        taskForDb.setTitle("TaskTest");
        taskRepository.save(taskForDb);
        List<Task> taskList = taskRepository.findAll();
        Long idTaskForDelete = taskList.get(0).getId();
        // Создаем данные для изменения
        UpdateTaskDto updateTaskDto = new UpdateTaskDto();
        updateTaskDto.setDescription("Update");
        updateTaskDto.setPriority(LOW);
        updateTaskDto.setEmailAssignee(emailUser1);
        updateTaskDto.setStatus(COMPLETED);
        updateTaskDto.setTitle("NEW");
        // check
        // Получение токена
        AuthRequest authRequest = new AuthRequest();
        authRequest.setEmail(emailAdmin);
        authRequest.setPassword(password);
        // Act
        ResponseEntity<String> response = testRestTemplate.postForEntity("/login", authRequest, String.class);

        // Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        String token = response.getBody();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        // Создание HTTP-запроса
        HttpEntity<UpdateTaskDto> requestEntity = new HttpEntity<>(updateTaskDto, headers);

        // Выполнение POST-запроса
        ResponseEntity<TaskFromDbDto> responseEntity = testRestTemplate.exchange(
                "/tasks/{id}",
                HttpMethod.PUT,
                requestEntity,
                TaskFromDbDto.class,
                idTaskForDelete
        );
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().getTitle()).isEqualTo(updateTaskDto.getTitle());
    }

    @Test
    @DisplayName("Проверка метода по удалению задания")
    void deleteTask() {
        // Подготовка данных
        //Создаем User и Admin
        String emailUser1 = "user1@example.com";
        String emailAdmin = "admin@example.com";
        UserInfo userInfoUser = new UserInfo();
        userInfoUser.setRole(Role.USER);
        userInfoUser.setEmail(emailUser1);
        String password = "password";
        userInfoUser.setPassword(passwordEncoder.encode(password));
        userInfoRepository.save(userInfoUser);
        UserInfo userInfoAdmin = new UserInfo();
        userInfoAdmin.setRole(Role.ADMIN);
        userInfoAdmin.setEmail(emailAdmin);
        userInfoAdmin.setPassword(passwordEncoder.encode(password));
        userInfoRepository.save(userInfoAdmin);
        //Создаем задание
        Task taskForDb = new Task();
        taskForDb.setAuthor(userInfoAdmin);
        taskForDb.setAssignee(userInfoUser);
        taskForDb.setStatus(PENDING);
        taskForDb.setDescription("TEST");
        taskForDb.setPriority(HIGH);
        taskForDb.setTitle("TaskTest");
        taskRepository.save(taskForDb);
        List<Task> taskList = taskRepository.findAll();
        Long idTaskForDelete = taskList.get(0).getId();
        // Получение токена
        AuthRequest authRequest = new AuthRequest();
        authRequest.setEmail(emailAdmin);
        authRequest.setPassword(password);
        // Act
        ResponseEntity<String> response = testRestTemplate.postForEntity("/login", authRequest, String.class);
        // Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        String token = response.getBody();
        // Настройка заголовков с токеном
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        // Выполнение DELETE-запроса с заголовками
        ResponseEntity<Void> responseEntity = testRestTemplate.exchange(
                "/tasks/{id}",
                HttpMethod.DELETE,
                requestEntity,
                Void.class,
                idTaskForDelete
        );
        // Проверка статуса ответа
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(204); // HTTP 204 No Content
        taskList = taskRepository.findAll();
        assertThat(taskList).isEmpty();
    }
}