# Spring Data Redis 
Demo project to reproduce error.

This project throws an `IllegalStateException` caused by a `SerializationException`.

# Running the application

```
./mvnw spring-boot:build-image
docker run --rm -p 8080:8080 redis-serialization:0.0.1-SNAPSHOT
```

