FROM openjdk:21-jdk-slim AS build
RUN apt-get update && apt-get install -y \
    curl \
    unzip \
    && curl -sL https://archive.apache.org/dist/maven/maven-3/3.9.6/binaries/apache-maven-3.9.6-bin.zip -o /tmp/maven.zip \
    && unzip /tmp/maven.zip -d /opt \
    && rm /tmp/maven.zip \
    && ln -s /opt/apache-maven-3.9.6/bin/mvn /usr/bin/mvn
RUN mvn -v
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests
FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
