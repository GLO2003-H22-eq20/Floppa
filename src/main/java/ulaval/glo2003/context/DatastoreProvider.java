package ulaval.glo2003.context;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import dev.morphia.query.DefaultQueryFactory;

public class DatastoreProvider {

    private final Datastore datastore;
    private final MongoDatabase database;
    private final MongoClient mongoClient;

    public DatastoreProvider(){
        String mongoUrl = System.getenv().getOrDefault("FLOPPA_MONGO_CLUSTER", "mongodb://localhost");
        String mongoDatabase = System.getenv().getOrDefault("FLOPPA_MONGO_DATABASE", "floppa-dev");
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder().applyConnectionString(new ConnectionString(mongoUrl)).build();

        this.mongoClient = MongoClients.create(mongoClientSettings);
        this.database = getMongoClient().getDatabase(mongoDatabase);
        this.datastore = Morphia.createDatastore(getMongoClient(), database.getName());
        datastore.getMapper().mapPackage("ulaval.glo2003.controllers.seller.dtos");
        datastore.ensureIndexes();
    }

    private MongoClient getMongoClient() {
        return mongoClient;
    }

    private MongoDatabase getMongoDatabase(){
        return database;
    }

    public Datastore getDatastore(){
        return datastore;
    }
}
