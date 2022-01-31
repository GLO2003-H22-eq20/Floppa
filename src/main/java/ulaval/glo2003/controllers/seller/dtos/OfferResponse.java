package ulaval.glo2003.controllers.seller.dtos;

public class OfferResponse {
    private final Float mean;
    private final Integer count;

    public OfferResponse(Float mean, Integer count) {
        this.mean = mean;
        this.count = count;
    }

    public Float getMean() {return mean;}

    public Integer getCount() {return count;}
}
