FROM eclipse-temurin:17-jdk-jammy
COPY build/libs/shorturl-0.0.1-SNAPSHOT.jar /app.jar
ENTRYPOINT ["sh", "-c", "java -Dserver.port=$PORT -jar /app.jar"]
EXPOSE 8080
