FROM openjdk:8-jdk
WORKDIR /app
ADD wait-for-it.sh wait-for-it.sh
EXPOSE 8080
CMD java -jar app.jar
ADD ./target/*.jar app.jar



