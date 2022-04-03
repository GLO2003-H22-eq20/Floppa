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
        Locale.setDefault(Locale.US);
        OptionalDouble mean = offers.stream().mapToDouble(Offer::getAmount).average();
        if (mean.isPresent()) {
            double test1 = mean.getAsDouble();
            long test = Math.round(test1*100.0);
            return test/100.0;
            //return Math.round(mean.getAsDouble()*100.0)/100.0;
            //return Double.valueOf(decimalFormat.format(mean.getAsDouble()));
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
