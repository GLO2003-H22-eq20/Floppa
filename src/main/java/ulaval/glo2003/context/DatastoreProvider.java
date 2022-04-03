package ulaval.glo2003.context;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import dev.morphia.mapping.MapperOptions;
import org.bson.UuidRepresentation;

public class DatastoreProvider {

    private final Datastore datastore;
    private final MongoDatabase database;
    private final MongoClient mongoClient;

    public DatastoreProvider(String connectionString, String dbName) {
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .uuidRepresentation(UuidRepresentation.STANDARD)
                .build();
        MapperOptions.Builder builder = MapperOptions.builder(MapperOptions.DEFAULT);
        builder.storeEmpties(true);

        this.mongoClient = MongoClients.create(mongoClientSettings);
        this.database = getMongoClient().getDatabase(dbName);
        this.datastore = Morphia.createDatastore(getMongoClient(), database.getName(), builder.build());

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
