package ulaval.glo2003;

public class Offers {
    private Float mean;
    private Integer count;

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