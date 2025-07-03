FROM eclipse-temurin:21-jdk
COPY build/libs/taskmanager-fat.jar /app/taskmanager.jar
ENTRYPOINT ["java", "-jar", "/app/taskmanager.jar"]

