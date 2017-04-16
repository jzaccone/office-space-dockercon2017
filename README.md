
Getting started
---------------

Download [Docker](https://www.docker.com/products/overview). If you are on Mac or Windows, [Docker Compose](https://docs.docker.com/compose) will be automatically installed. On Linux, make sure you have the latest version of [Compose](https://docs.docker.com/compose/install/).

Compile the Java project
```
cd compute-interest-api
mvn package
cd ..
```
OR use the multi-stage build Dockerfile (Prereq- Edge Release: 17.04.0)
```
cd compute-interest-api
mv Dockerfile_multistage Dockerfile
cd ..
```

Run at the top level:
```
docker-compose up
```
The account summary will be running at [http://localhost:5001](http://localhost:5001).

About the app
---------------
This app mimicks the fictitious app idea from Michael Bolton in the movie "Office Space".

The app takes advantage of a financial program that computes interest for transactions by diverting fractions of a cent that are usually rounded off into a seperate bank account.

The app consists of a Java 8 / Spring Boot webserver that contains one method /computeinterest. In the implementation of this method, a connection to a MySQL database is made to deposit the rounded interest into an account. The Node.js app is a UI that shows the current account balance.

Transactions are simulated through the TransactionGenerator python app.

Architecture
---------------
![Architecture Diagram](architecture.png)
