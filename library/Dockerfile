FROM eclipse-temurin:19-jre-focal
LABEL maintainer="YR"

COPY ./target/library-1.jar /app/

CMD ["java", "-Xmx200m", "-jar", "/app/library-1.jar"]

EXPOSE 8080 8081