# Dockerfile-backend
FROM maven:3.8.1-openjdk-17-slim
WORKDIR /app
COPY . /app
CMD ["mvn", "spring-boot:run"]
