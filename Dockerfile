FROM openjdk:11

ENV SPRING_PROFILES_ACTIVE=production

VOLUME /tmp
COPY build/libs/*.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]