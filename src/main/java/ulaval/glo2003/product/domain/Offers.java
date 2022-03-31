package ulaval.glo2003.product.domain;

public class Offers {
    private Double mean;
    private Integer count;

    public Offers() {}

    public Offers(Integer count) {
        this.count = count;
    }

    public Offers(Double mean, Integer count) {
        this.mean = mean;
        this.count = count;
    }

    public Double getMean() {
        return mean;
    }

    public Integer getCount() {
        return count;
    }
}
