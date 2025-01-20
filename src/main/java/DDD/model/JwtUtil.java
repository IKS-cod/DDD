package DDD.model;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

//@Component
//public class JwtUtil {
//    private final String secretKey = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437"; // Замените на более безопасный ключ
//
//    public String generateToken(UserDetails userDetails) {
//        return Jwts.builder()
//                .setSubject(userDetails.getUsername())
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // Токен действителен 10 часов
//                .signWith(SignatureAlgorithm.HS256, secretKey)
//                .compact();
//    }
//
//    public boolean validateToken(String token, UserDetails userDetails) {
//        final String username = extractUsername(token);
//        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//    }
//
//    public String extractUsername(String token) {
//        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
//    }
//
//    private boolean isTokenExpired(String token) {
//        return extractExpiration(token).before(new Date());
//    }
//
//    private Date extractExpiration(String token) {
//        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getExpiration();
//    }
//}
/**
 * Утилита для работы с JSON Web Tokens (JWT).
 *
 * Этот класс предоставляет методы для генерации, валидации и извлечения
 * информации из JWT. Он использует секретный ключ для подписи токенов.
 */
@Component
public class JwtUtil {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    private final String secretKey = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437"; // Замените на более безопасный ключ

    /**
     * Генерирует JWT для указанного пользователя.
     *
     * @param userDetails объект, содержащий информацию о пользователе
     * @return сгенерированный JWT
     */
    public String generateToken(UserDetails userDetails) {
        String token = Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // Токен действителен 10 часов
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

        logger.debug("Сгенерирован токен для пользователя: {}", userDetails.getUsername());
        return token;
    }

    /**
     * Проверяет, действителен ли указанный токен для данного пользователя.
     *
     * @param token      JWT для проверки
     * @param userDetails объект, содержащий информацию о пользователе
     * @return true, если токен действителен; false в противном случае
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        boolean isValid = (username.equals(userDetails.getUsername()) && !isTokenExpired(token));

        logger.debug("Валидация токена: {}. Результат: {}", token, isValid);
        return isValid;
    }

    /**
     * Извлекает имя пользователя из токена.
     *
     * @param token JWT из которого нужно извлечь имя пользователя
     * @return имя пользователя
     */
    public String extractUsername(String token) {
        String username = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
        logger.debug("Извлечено имя пользователя из токена: {}", username);
        return username;
    }

    /**
     * Проверяет, истек ли срок действия токена.
     *
     * @param token JWT для проверки
     * @return true, если токен истек; false в противном случае
     */
    private boolean isTokenExpired(String token) {
        boolean expired = extractExpiration(token).before(new Date());
        logger.debug("Проверка истечения срока действия токена: {}. Результат: {}", token, expired);
        return expired;
    }

    /**
     * Извлекает дату истечения срока действия из токена.
     *
     * @param token JWT из которого нужно извлечь дату истечения
     * @return дата истечения срока действия токена
     */
    private Date extractExpiration(String token) {
        Date expiration = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getExpiration();
        logger.debug("Извлечена дата истечения срока действия токена: {}", expiration);
        return expiration;
    }
}