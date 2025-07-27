FROM openjdk:21-slim
WORKDIR /app
COPY target/cepapi-0.0.1-SNAPSHOT.jar cep-app.jar
EXPOSE 8080
CMD ["java", "-jar", "cep-app.jar"]