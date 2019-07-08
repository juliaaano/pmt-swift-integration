# Payment Swift Integration Demo

## Payment Service

1. Setup Kafka and Topics

```
docker-compose up --detach
docker-compose exec kafka kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic payment
```

2. Run Fuse application

```
mvn --file payment-service/pom.xml spring-boot:run
```

3. Consume (listen) Kafka messages

```
docker-compose exec kafka kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic payment --from-beginning
```

## Legacy Integration

1. Setup IBM MQ

```
docker run --rm --name ibm-mq --env LICENSE=accept --env MQ_QMGR_NAME=QM1 --env MQ_APP_PASSWORD=password --publish 1414:1414 --publish 9443:9443 --detach ibmcom/mq
```

2. Connect to IBM MQ console

```
https://localhost:9443
```

3. Run Fuse application

```
mvn --file legacy-integration/pom.xml spring-boot:run
```

## Batch Handler

## Aggregation Service
