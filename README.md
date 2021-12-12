# Delivery Manager
The aim of the project is creating an application which manages new deliveries and their states.

### SETUP
- Maven 3.6.1
- Java 1.8.0
- Docker 20.10.11

#### Run Dockerized Services
The project uses custom backend service as docker container. Run startDocker.cmd in order to run application as docker container.

#### Run Without Docker
Run start.cmd in order to run application from command line.

### USAGE

Client can send querys and mutations with GraphQl in order to get all deliveries, received deliveries, 
not yet received deliveries, add new delivery, remove existing delivery and update state of existing delivery.

Example requests can be seen below : 

Query deliveries : 


