package ulaval.glo2003.product.domain;

public class Offers {
    private Double mean;
    private Long count;

    public Offers() {}

    public Offers(Long count) {
        this.count = count;
    }

    public Offers(Double mean, Long count) {
        this.mean = mean;
        this.count = count;
    }

    public Double getMean() {
        return mean;
    }

    public Long getCount() {
        return count;
    }
}
