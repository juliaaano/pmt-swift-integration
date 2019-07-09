# Payment Swift Integration Demo

## Setup environment

```
docker-compose up --detach
```

## Payment Service

1. Create Kafka topic

```
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

1. Connect to IBM MQ console

```
https://localhost:9443 (login: admin / passw0rd)
```

2. Run Fuse application

```
mvn --file legacy-integration/pom.xml spring-boot:run
```

## Batch Handler

## Aggregation Service
