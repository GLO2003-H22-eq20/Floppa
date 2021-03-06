package ulaval.glo2003.product.domain;

import ulaval.glo2003.offer.domain.Offer;

import java.util.List;

public class Offers {
    private Double mean;
    private Integer count;
    private Double min;
    private Double max;
    private List<Offer> offers;

    public Offers() {
    }

    public Offers(Integer count) {
        this.count = count;
    }

    public Offers(Double mean, Integer count, Double min, Double max, List<Offer> offers) {
        this.mean = mean;
        this.count = count;
        this.min = min;
        this.max = max;
        this.offers = offers;
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

    public List<Offer> getOffers() {
        return offers;
    }
}
