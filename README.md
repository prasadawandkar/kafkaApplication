# starting a docker container. Navigate to docker folder inside root and run the below command.
# ->    docker compose up -d

# check logs (optional)
# docker compose logs -f kafka


# Command to create topic in Container. Replace docker-kafka-1 with your container name

#  docker exec -it docker-kafka-1 \
#  sh -c "kafka-topics --create --topic trades --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1"


# Run t6he application and trigger the Rest controllers.
