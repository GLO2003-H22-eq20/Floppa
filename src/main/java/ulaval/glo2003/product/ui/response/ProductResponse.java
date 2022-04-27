package ulaval.glo2003.product.ui.response;

import ulaval.glo2003.offer.ui.response.OffersResponse;
import ulaval.glo2003.seller.ui.response.SellerResponse;

import java.util.List;

public class ProductResponse {
    private String id;
    private String createdAt;
    private String title;
    private String description;
    private Double suggestedPrice;
    private List<String> categories;
    private SellerResponse seller;
    private OffersResponse offers;

    public ProductResponse() {
    }

    public ProductResponse(String id, String createdAt, String title, String description, Double suggestedPrice,
                           OffersResponse offers, List<String> categories, SellerResponse seller) {
        this.id = id;
        this.createdAt = createdAt;
        this.title = title;
        this.description = description;
        this.suggestedPrice = suggestedPrice;
        this.offers = offers;
        this.categories = categories;
        this.seller = seller;
    }

    public ProductResponse(String id, String createdAt, String title, String description,
                           Double suggestedPrice, OffersResponse offers, List<String> categories) {
        this.id = id;
        this.createdAt = createdAt;
        this.title = title;
        this.description = description;
        this.suggestedPrice = suggestedPrice;
        this.offers = offers;
        this.categories = categories;
    }

    public String getId() {
        return id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Double getSuggestedPrice() {
        return suggestedPrice;
    }

    public OffersResponse getOffers() {
        return offers;
    }

    public List<String> getCategories() {
        return categories;
    }

    public SellerResponse getSeller() {
        return seller;
    }
}
