FROM amazoncorretto:24
WORKDIR /app
COPY target/persephone.jar /app/persephone.jar
EXPOSE 8084
CMD ["java", "-jar", "/app/persephone.jar"]