FROM adoptopenjdk/maven-openjdk11 as builder
WORKDIR /application

ARG JAR_FILE=target/stock-quote-manager-1.0.jar
COPY ${JAR_FILE} application.jar

COPY src ./src
COPY pom.xml ./
# RUN mvn clean package -DskipTests=true
RUN java -Djarmode=layertools -jar application.jar extract
# target/stock-quote-manager-1.0.jar

FROM adoptopenjdk:11-jre-hotspot
WORKDIR /application
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/application/ ./
EXPOSE 8090
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]