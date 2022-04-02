package ulaval.glo2003.offer.ui.response;

import java.util.List;

public class OffersResponse {
    private Double mean;
    private Integer count;
    private Double min;
    private Double max;
    private List<OfferResponse> items;

    public OffersResponse(Double mean, Integer count) {
        this.mean = mean;
        this.count = count;
    }

    public OffersResponse(Double mean, Integer count, Double min, Double max, List<OfferResponse> items) {
        this.mean = mean;
        this.count = count;
        this.min = min;
        this.max = max;
        this.items = items;
    }

    public Double getMean() {
        return mean;
    }

    public Integer getCount() {
        return count;
    }

    public Double getMin() {
        return min;
    }

    public Double getMax() {
        return max;
    }

    public List<OfferResponse> getItems() {
        return items;
    }
}