# cache as most as possible in this multistage dockerfile.
FROM maven:3.6-alpine as BUILDER

WORKDIR /opt/app

COPY scrapper ./scrapper
COPY link-parser ./link-parser
COPY bot ./bot
COPY pom.xml .

RUN mvn package -pl scrapper -am

FROM openjdk:17-alpine
WORKDIR /opt/app
COPY --from=builder /opt/app/scrapper/target/*.jar ./app.jar
CMD [ "java", "-jar", "/opt/app/app.jar" ]