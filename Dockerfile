FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY . .

RUN ./gradlew clean build --no-daemon

CMD ["sh", "-c", "java -Dserver.port=$PORT -jar build/libs/shorturl-0.0.1-SNAPSHOT.jar"]
