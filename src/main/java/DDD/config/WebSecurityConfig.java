package DDD.config;

import DDD.filter.JwtRequestFilter;
import DDD.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private JwtRequestFilter jwtRequestFilter;
//
//    @Autowired
//    private UserService userService;
//
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    private static final String[] AUTH_WHITELIST = {
//            "/swagger-resources/**",
//            "/v3/api-docs/**", // Если используете SpringDoc
//            "/swagger-ui/**", // Это для Swagger UI
//            "/login",
//            "/register"
//    };
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeRequests()
//                .antMatchers(AUTH_WHITELIST)
//                .permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}

/**
 * Конфигурация безопасности веб-приложения.
 *
 * Этот класс настраивает безопасность приложения, включая аутентификацию и авторизацию пользователей.
 * Он использует JWT для аутентификации запросов и определяет, какие конечные точки являются
 * общедоступными, а какие требуют аутентификации.
 *
 * Основные функции:
 * - Настройка фильтров для обработки JWT токенов
 * - Определение общедоступных конечных точек (например, для регистрации и входа)
 * - Настройка метода аутентификации
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    private UserService userService;

    private static final String[] AUTH_WHITELIST = {
            "/swagger-resources/**",
            "/v3/api-docs/**", // Если используете SpringDoc
            "/swagger-ui/**", // Это для Swagger UI
            "/login",
            "/register"
    };

    /**
     * Определяет менеджер аутентификации.
     *
     * @return AuthenticationManager для управления аутентификацией пользователей
     * @throws Exception в случае ошибки при создании менеджера аутентификации
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        logger.info("Создание AuthenticationManager");
        return super.authenticationManagerBean();
    }

    /**
     * Конфигурация HTTP безопасности.
     *
     * Настраивает правила доступа к конечным точкам приложения, включая разрешения
     * для общедоступных URL и установку политики управления сессиями.
     *
     * @param http объект HttpSecurity для настройки безопасности
     * @throws Exception в случае ошибки настройки безопасности
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        logger.info("Настройка HTTP безопасности");

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(AUTH_WHITELIST)
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        logger.info("Фильтр JWT добавлен перед UsernamePasswordAuthenticationFilter");
    }

    /**
     * Определяет кодировщик паролей.
     *
     * Используется для хеширования паролей пользователей перед их сохранением в базе данных.
     *
     * @return PasswordEncoder для хеширования паролей
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        logger.info("Создание PasswordEncoder с использованием BCrypt");
        return new BCryptPasswordEncoder();
    }
}
