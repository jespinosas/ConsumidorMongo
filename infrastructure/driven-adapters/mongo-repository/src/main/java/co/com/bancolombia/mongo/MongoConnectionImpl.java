package co.com.bancolombia.mongo;

import co.com.bancolombia.mongo.interfaces.MongoConnection;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MongoConnectionImpl implements MongoConnection {

    private static final String MONGO_CONNECTION = "mongodb://localhost:27017";

    @Override
    public MongoDatabase connectionFactory(String databaseName) {
        MongoClient client = new MongoClient("localhost", 27017);
        return client.getDatabase(databaseName);
    }
}