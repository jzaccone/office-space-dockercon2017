FROM java:8
WORKDIR /app
ADD ./target/*.jar /app/app.jar
ADD wait-for-it.sh wait-for-it.sh
CMD java -jar app.jar
