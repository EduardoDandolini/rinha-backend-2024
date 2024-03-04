FROM openjdk:17-alpine
ARG JAR_FILE=target/rinhadebackend.jar
RUN mkdir app
WORKDIR /app
COPY ${JAR_FILE} .
CMD ["java", "-jar", "-Dspring.profiles.active=prod" ,"./rinhadebackend.jar"]