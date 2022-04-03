package ulaval.glo2003.health.response;

public class HealthResponse {
    private boolean api;
    private boolean db;

    public HealthResponse() {
    }

    public HealthResponse(boolean api, boolean db) {
        this.api = api;
        this.db = db;
    }

    public boolean isApi() {
        return api;
    }

    public boolean isDb() {
        return db;
    }
}
