
FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /app
COPY . .


RUN apt-get update && apt-get install -y nodejs npm


RUN mvn clean package -DskipTests -Pproduction


FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]