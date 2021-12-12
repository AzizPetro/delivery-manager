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

![image](https://user-images.githubusercontent.com/45763123/145734056-84b638b6-db17-494b-bc4a-33dfd638e0fd.png)

Query received deliveries :

![image](https://user-images.githubusercontent.com/45763123/145734078-d788cbc9-e690-4a3c-8e37-9b372238c387.png)

Query not yet received deliveries :

![image](https://user-images.githubusercontent.com/45763123/145734107-6ef8ef04-7cfd-4365-b783-07c8486e54fc.png)

Mutation add new delivery:

![image](https://user-images.githubusercontent.com/45763123/145734184-4e78855f-beb2-42a8-bcd2-16e2511fb937.png)

Mutation delete existing delivery:

![image](https://user-images.githubusercontent.com/45763123/145734247-a623eaa9-4379-4e69-be31-bb6ca20c1cf4.png)

Mutation update delivery:

![image](https://user-images.githubusercontent.com/45763123/145734274-227c8123-5157-4a0f-b06f-a5083181b38e.png)

### PROJECT STRUCTURE

- Custom Backend Service : API Gateway to be able to establish connection between outside world and in-memory database

#### Ports
| Service 	| Port 	|
|-	|-	|
| API Gateway 	| 8080 	|
