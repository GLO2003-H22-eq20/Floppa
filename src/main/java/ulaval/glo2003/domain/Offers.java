package ulaval.glo2003.domain;

public class Offers {
    private final Float mean;
    private final Integer count;

    public Offers(Float mean, Integer count) {
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
