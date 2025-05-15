# Sử dụng image chuẩn của OpenJDK
FROM openjdk:17-jdk-slim

# Thiết lập thư mục làm việc
WORKDIR /app

# Copy file JAR vào container
COPY target/project3ae-0.0.1-SNAPSHOT.jar app.jar

# Expose port ứng dụng Spring Boot chạy
EXPOSE 8081

# Chạy ứng dụng khi container khởi động
ENTRYPOINT ["java", "-jar", "app.jar"]
