package ulaval.glo2003.offer.ui.response;

public class OfferResponse {
    private String id;
    private Double amount;
    private String message;
    private String createdAt;
    private BuyerResponse buyer;

    public OfferResponse() {
    }

    public OfferResponse(String id, Double amount, String message, String createdAt, BuyerResponse buyer) {
        this.id = id;
        this.amount = amount;
        this.message = message;
        this.createdAt = createdAt;
        this.buyer = buyer;
    }

    public String getId() {
        return id;
    }

    public Double getAmount() {
        return amount;
    }

    public String getMessage() {
        return message;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public BuyerResponse getBuyer() {
        return buyer;
    }

}