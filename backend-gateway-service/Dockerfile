FROM openjdk:11-jre-slim
LABEL maintainer="ensu6788@gmail.com"
VOLUME /tmp
ARG JAR_FILE=./build/libs/*.jar
ADD ${JAR_FILE} app.jar
EXPOSE 6030
ENTRYPOINT ["java","-jar","/app.jar"]