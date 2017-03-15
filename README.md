This app mimicks the fictitious app idea from Michael Bolton in the movie "Office Space".

The app takes advantage of a financial program that computes interest for transactions by diverting fractions of a cent that are usually rounded off into a seperate bank account.

The app consists of a Java 8 / Spring Boot webserver that contains one method /computeinterest. In the implementation of this method, a connection to a MySQL database is made to deposit the rounded interest into an account. The Node.js app is a UI that shows the current account balance.

Transactions are simulated through the TransactionGenerator python app.

![Architecture Diagram](architecture.png)
