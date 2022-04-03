package ulaval.glo2003.health;

public class HealthResponse {
    private boolean api;
    private boolean db;

    public HealthResponse(boolean api, boolean db) {
        this.api = api;
        this.db = db;
    }

    public HealthResponse() {
    }

    public boolean getApi() {
        return api;
    }

    public boolean getDb() {
        return db;
    }
}
