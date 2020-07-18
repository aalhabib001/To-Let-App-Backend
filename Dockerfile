# For Java 8, try this
# FROM openjdk:8-jdk-alpine

# For Java 11, try this
#FROM adoptopenjdk/openjdk11:alpine-jre
#FROM maven

#RUN mvn -f /usr/src/app/pom.xml clean package
#RUN mvn -f /usr/src/app/pom.xml clean package

# Refer to Maven build -> finalName
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]


FROM maven:3.6-jdk-8 as maven
WORKDIR /app
COPY ./pom.xml ./pom.xml
RUN mvn dependency:go-offline -B
COPY ./src ./src

# TODO: jollof-* should be replaced with the proper prefix
RUN mvn package && cp target/jollof-*.jar app.jar

# Rely on Docker's multi-stage build to get a smaller image based on JRE
FROM openjdk:8-jre-alpine
LABEL maintainer="xxxxx@xxx.com"
WORKDIR /app
COPY --from=maven /app/app.jar ./app.jar

# VOLUME /tmp  # optional
EXPOSE 8080    # also optional

ENTRYPOINT ["java","-jar","/app.jar"]
