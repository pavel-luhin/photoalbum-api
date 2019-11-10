FROM gradle:4.10.3-jdk8

COPY build/libs/*.jar /app/photoalbum.jar

ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-jar", "-Dspring.profiles.active=docker", "/app/photoalbum.jar"]
