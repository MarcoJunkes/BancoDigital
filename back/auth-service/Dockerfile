FROM --platform=linux/amd64 openjdk:17
WORKDIR /app
COPY src ./src
COPY mvnw pom.xml ./
CMD ["./mvnw", "spring-boot:run"]
