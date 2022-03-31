package ulaval.glo2003.offer.domain;

import ulaval.glo2003.product.domain.Offers;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;
import java.util.OptionalDouble;

public class OffersAssembler {

    public Offers assembleOffers(List<Offer> offers) {
        return new Offers(getMean(offers), getCount(offers));
    }

    private int getCount(List<Offer> offers) {
        return offers.size();
    }

    private Double getMean(List<Offer> offers) {
        OptionalDouble mean = offers.stream().mapToDouble(Offer::getAmount).average();
        if (mean.isPresent()) {
            return Double.valueOf(decimalFormat.format(mean.getAsDouble()));
        } else {
            return null;
        }
    }

    private final DecimalFormat decimalFormat = new DecimalFormat(
            String.format(
                    "#%s##",
                    ((DecimalFormat) DecimalFormat.getInstance(Locale.ROOT))
                            .getDecimalFormatSymbols()
                            .getDecimalSeparator()
            )
    );
}
