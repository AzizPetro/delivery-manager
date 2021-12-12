FROM maven:3.6.1-jdk-8-slim AS build
COPY . /delivery-manager
WORKDIR /delivery-manager
RUN mvn clean install
EXPOSE 8080
CMD java -jar target/delivery-manager-0.0.1-SNAPSHOT.jar