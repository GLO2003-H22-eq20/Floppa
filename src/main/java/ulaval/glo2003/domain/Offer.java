package ulaval.glo2003.domain;

public class Offer {
    private Float mean;
    private Integer count;

    public Offer(Float mean, Integer count) {
        this.mean = mean;
        this.count = count;
    }

    public Float getMean() {
        return mean;
    }

    public Integer getCount() {
        return count;
    }
}
