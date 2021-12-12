docker rm --force delivery-manager-container
docker build . -t delivery-manager
docker run --name delivery-manager-container -p 8080:8080 delivery-manager