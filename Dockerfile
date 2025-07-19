# ---- Build Stage ----
FROM maven:3-amazoncorretto-21-alpine AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn -q clean package -DskipTests

# ---- Runtime Stage ----
FROM amazoncorretto:21-alpine-jdk AS runtime
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8084
ENV JAVA_TOOL_OPTIONS="-XX:-UseContainerSupport -Xms128m -Xmx976m -XX:MinHeapFreeRatio=20 -XX:MaxHeapFreeRatio=50"
ENTRYPOINT ["java","-jar","app.jar"]