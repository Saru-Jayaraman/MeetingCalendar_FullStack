## EMAIL SENDER MICROSERVICE
### Objective:
* Task is to create a Java EE application (Microservice) for an Email Sender API. The goal is to send an email from Gmail account using SMTP protocol.
* Make the controller as Endpoint in order to make it as a Micro-Webservice.
* Added Swagger UI configuration for documentation.
### Operations:
* Send email using Gmail.
* Sent email is saved inside the Database.
### Components:
1. Entities, Controllers, Service and Repository layers are created.
2. Host email account(GMAIL) details are saved inside properties file in IntelliJ IDE.
3. Mail is constructed as Mime message and sent using Java Mail Sender.
4. Sent email is saved inside the Database.
### Project Requirements:
* Validate HTTP request parameters.
* Implement exception handling.
* Document APIs using Spring-doc API and Swagger.
### Technical Requirements:
#### Backend:
* Java 17
* Spring Boot REST
* Spring Data JPA
* Validation
* Maven
#### Database:
* MySQL
#### Tools:
* IntelliJ Idea
* Postman
