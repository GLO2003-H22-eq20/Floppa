package ulaval.glo2003.context;

import io.github.cdimascio.dotenv.Dotenv;

import java.net.URI;

public class Configuration {
    private Dotenv environmentVars;

    public Configuration() {
        environmentVars = Dotenv.configure().ignoreIfMissing().load();
    }

    public URI getUri() {
        final String port = environmentVars.get("PORT", "8080");
        final String baseUri = "http://0.0.0.0:" + port + "/";
        return URI.create(baseUri);
    }

    public DatastoreProvider createDatastoreProvider() {
        String mongoUrl = environmentVars.get("FLOPPA_MONGO_CONNECTION_STRING", "mongodb://localhost");
        String mongoDatabase = environmentVars.get("FLOPPA_MONGO_DATABASE", "floppa-dev");

        return new DatastoreProvider(mongoUrl, mongoDatabase);
    }

}
