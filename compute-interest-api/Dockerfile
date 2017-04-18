FROM maven:3.3.9-jdk-8-alpine
COPY . /app
WORKDIR /app
RUN mvn package
CMD java -jar target/*.jar


