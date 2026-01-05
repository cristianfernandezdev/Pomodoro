# 1. Etapa de compilación
FROM maven:3.9.9-amazoncorretto-21 AS builder
WORKDIR /app
COPY . .
# AQUI ESTA LA MAGIA: Añadimos -Pproduction
# Esto le dice a Vaadin: "Prepara todo para prod, no intentes ser listo luego"
RUN mvn clean package -DskipTests -Pproduction

# 2. Etapa de ejecución
FROM amazoncorretto:21-alpine-jdk
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]