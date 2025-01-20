# Этап сборки
FROM maven:3.8.8-openjdk-17 AS builder

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем файл pom.xml и зависимости
COPY pom.xml .
COPY src ./src

# Сборка приложения
RUN mvn clean package -DskipTests

# Этап выполнения
FROM eclipse-temurin:17-jre AS runtime

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем скомпилированный JAR-файл из предыдущего этапа
COPY --from=builder /app/target/ddd.jar app.jar

# Открываем порт, на котором будет работать приложение
EXPOSE 8080

# Указываем команду для запуска приложения
ENTRYPOINT ["java", "-jar", "app.jar"]