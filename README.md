# subscription-microservices

Example of the use of microservices using Spring Boot, Spring Cloud, Spring OAuth, RabbitMQ, Docker, MongoDB and Spring Netflix OSS frameworks. 


## Application Structure

The application has the following modules:

* eureka-server - Eureka server is the service discovery that will register all the services 
* zull-server - The Zuul server is the api gateway and load balancer.
* auth-server - The OAuth2 authorization server that is responsible for managing the access to the resource servers.
* email-service - RabbitMQ listener responsible for processing all send email requests and call the email interface.
* subscription-processor - RabbitMQ listener responsible for processing all save subscription requests and call the save subscription api.
* subscription-application - The open subscription api that is the responsible for getting the users request and then send the create/cancel/get subscription requests. 
* subscription-service - The secured subscription api that will store all the subscription data and also will create events for save and send email. 


## Functional Details

The subscription process starts whith the POST to the submission endpoint. After that the subscription-service will send the subscription request to the subscription.queue and then the subscription-processor will get the message and call the subscription-service but now sending the request the POST request to the /create endpoint. If this process fails the message will be requeued and the processor will process the message again. The subscription-service/create method will save the subscription in the mongodb database and then sending the send email request to the email.queue queue and then the final step of the subscription process will be started. The email-service will get the message and try to send the email and in case of error the message will be requeued and reprocessed until it reaches the retry limit configured in the properties. 
The subscription-application and the subscpription-processor uses Feign client to communicate with subscription-service and has a Hystrix Circuit Break to deal with errors during the api commuction and avoid propagating the errors. The subscription application is also responsible for requesting the tokens from the auth-server in order to obtain a secure machine to machine communication using client credentials. 

## API Docs

The API documentation can be found in the following address: http://localhost:8085/subscription-app/swagger-ui.html

The Postman collection contains all the rest calls available in the solution.

## Build and Run

- Install Docker and Docker Compose.
- Build the project: mvn clean package [-DskipTests]
- Build the docker images: ./buildDockerImages.sh
- Run the container using docker compose: docker-compose up -d


## TODOS

- Add logstash to the application
