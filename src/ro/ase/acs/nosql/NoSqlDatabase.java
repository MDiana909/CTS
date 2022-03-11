package ro.ase.acs.nosql;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import ro.ase.acs.utils.DatabaseOperations;

public class NoSqlDatabase implements DatabaseOperations<MongoDatabase> {
    @Override
    public MongoDatabase createConnection() {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase mongoDb = mongoClient.getDatabase("test");

        return mongoDb;
    }

    @Override
    public void createTable(MongoDatabase mongoDb) {
        if (mongoDb.getCollection("employees") != null) {
            mongoDb.getCollection("employees").drop();
        }

        mongoDb.createCollection("employees");
    }

    @Override
    public void insertData(MongoDatabase mongoDb) {
        Document employee1 = new Document().append("name", "Popescu Ion").
                append("address", "Bucharest").append("salary", 4000);

        MongoCollection<Document> collection = mongoDb.getCollection("employees");
        collection.insertOne(employee1);

        Document employee2 = new Document().append("name", "Ionescu Vasile").
                append("salary", 4500);
        collection.insertOne(employee2);
    }

    @Override
    public void readData(MongoDatabase mongoDb) {
        MongoCollection<Document> collection = mongoDb.getCollection("employees");
        FindIterable<Document> result = collection.find();
        for (Document doc : result) {
            System.out.println(doc);
        }
    }
}
