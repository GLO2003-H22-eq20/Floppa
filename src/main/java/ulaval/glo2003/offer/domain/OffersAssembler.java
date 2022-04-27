package ulaval.glo2003.offer.domain;

import ulaval.glo2003.product.domain.Offers;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;
import java.util.OptionalDouble;

public class OffersAssembler {

    public Offers assembleOffers(List<Offer> offers) {
        return new Offers(getMean(offers), getCount(offers), getMin(offers), getMax(offers), offers);
    }

    private int getCount(List<Offer> offers) {
        return offers.size();
    }

    private Double getMean(List<Offer> offers) {
        Locale.setDefault(Locale.US);
        OptionalDouble mean = offers.stream().mapToDouble(Offer::getAmount).average();
        if (mean.isPresent()) {
            return Math.round(mean.getAsDouble() * 100.0) / 100.0;
        } else {
            return null;
        }
    }

    private Double getMin(List<Offer> offers) {
        OptionalDouble min = offers.stream().mapToDouble(Offer::getAmount).min();
        if (min.isPresent()) {
            return min.getAsDouble();
        } else {
            return null;
        }
    }

    private Double getMax(List<Offer> offers) {
        OptionalDouble max = offers.stream().mapToDouble(Offer::getAmount).max();
        if (max.isPresent()) {
            return max.getAsDouble();
        } else {
            return null;
        }
    }

}
