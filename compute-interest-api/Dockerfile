FROM openjdk:8-jdk-alpine
WORKDIR /app
ADD ./target/*.jar /app/app.jar
ADD wait-for-it.sh wait-for-it.sh
EXPOSE 8080
CMD java -jar app.jar



