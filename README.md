# Payment Swift Integration Demo

## Payment Service

```
cd payment-service
```

1. Setup Kafka and Topics

```
docker-compose up --detach
docker-compose exec kafka kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic payment
```

2. Run Fuse application

```
mvn spring-boot:run
```

3. Consume (listen) Kafka messages

```
docker-compose exec kafka kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic payment --from-beginning
```

## Legacy Integration

```
cd legacy-integration
```

1. Setup IBM MQ

```
docker-compose up --detach
```

2. Connect to IBM MQ console

```
https://localhost:9443
Login: admin / passw0rd
```

3. Run Fuse application

```
mvn spring-boot:run
```

## Batch Handler

```
cd batch-handler
```

## Aggregation Service

```
cd aggreation-service
```

