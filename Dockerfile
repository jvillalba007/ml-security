#Maven
FROM maven:3.6.3-jdk-11 as builder
WORKDIR /ml
COPY . .
RUN mvn clean package

#OpenJdk11
FROM openjdk:11.0-jre-slim
WORKDIR /usr/app
COPY --from=builder /ml/target/ml-challenge-0.0.1-SNAPSHOT.jar /usr/app/
RUN sh -c 'touch ml-challenge-0.0.1-SNAPSHOT.jar'
EXPOSE 8080
ENTRYPOINT ["java","-jar","ml-challenge-0.0.1-SNAPSHOT.jar"]
