package ulaval.glo2003.product.ui.response;

public class ProductStatisticsResponse {
    private String id;
    private String title;
    private int viewsCount;

    public ProductStatisticsResponse() {
    }

    public ProductStatisticsResponse(String id, String title, int viewsCount) {
        this.id = id;
        this.title = title;
        this.viewsCount = viewsCount;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getViewsCount() {
        return viewsCount;
    }
}
