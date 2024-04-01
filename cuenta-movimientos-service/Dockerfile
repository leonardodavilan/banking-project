FROM maven:3.8.4-jdk-11-slim AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn package -DskipTests

FROM openjdk:11-jre-slim
ARG JAR_FILE=/app/target/*.jar
COPY --from=build ${JAR_FILE} /cuenta-movimientos-service.jar
ENTRYPOINT ["java","-jar","/cuenta-movimientos-service.jar"]
