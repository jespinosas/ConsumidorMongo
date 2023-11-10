package co.com.bancolombia.mongo.interfaces;

import com.mongodb.client.MongoDatabase;

public interface MongoConnection {
    MongoDatabase connectionFactory(String databaseName);
}
