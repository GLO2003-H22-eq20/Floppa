package ulaval.glo2003.controllers.product.dtos;

public class ProductSellerResponse {
    private final String id;
    private final String name;

    public ProductSellerResponse(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
