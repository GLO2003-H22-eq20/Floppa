package ulaval.glo2003.context;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import io.github.cdimascio.dotenv.Dotenv;
import org.bson.UuidRepresentation;

public class DatastoreProvider {

    private final Datastore datastore;
    private final MongoDatabase database;
    private final MongoClient mongoClient;

    public DatastoreProvider() {
        Dotenv environmentVars = Dotenv.load();

        String mongoUrl = environmentVars.get("FLOPPA_MONGO_CONNECTION_STRING", "mongodb://localhost");
        String mongoDatabase = environmentVars.get("FLOPPA_MONGO_DATABASE", "floppa-dev");

        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(mongoUrl))
                .uuidRepresentation(UuidRepresentation.STANDARD)
                .build();

        this.mongoClient = MongoClients.create(mongoClientSettings);
        this.database = getMongoClient().getDatabase(mongoDatabase);
        this.datastore = Morphia.createDatastore(getMongoClient(), database.getName());
        datastore.getMapper().mapPackage("ulaval.glo2003.controllers.seller.dtos");
        datastore.ensureIndexes();
    }

    private MongoClient getMongoClient() {
        return mongoClient;
    }

    private MongoDatabase getMongoDatabase() {
        return database;
    }

    public Datastore getDatastore() {
        return datastore;
    }
}
