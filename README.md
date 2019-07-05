# Payment Swift Integration Demo

## Payment Service

1. Setup Kafka and Topics

```
docker-compose up -d
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

## Batch Handler

## Aggregation Service
