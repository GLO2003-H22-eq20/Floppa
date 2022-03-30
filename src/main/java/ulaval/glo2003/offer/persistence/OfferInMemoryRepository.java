package ulaval.glo2003.offer.persistence;

import ulaval.glo2003.domain.Offer;
import ulaval.glo2003.offer.domain.OfferRepository;
import ulaval.glo2003.product.domain.Offers;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.OptionalDouble;


public class OfferInMemoryRepository implements OfferRepository {
    private final DecimalFormat decimalFormat = new DecimalFormat(
            String.format(
                    "#%s##",
                    ((DecimalFormat) DecimalFormat.getInstance(Locale.ROOT))
                            .getDecimalFormatSymbols()
                            .getDecimalSeparator()
            )
    );
    private final Map<String, Offer> offerMap = Collections.synchronizedMap(new HashMap<>());

    public void saveOffer(Offer offer) {
        offerMap.put(offer.getId().toString(), offer);
    }

    private Long getCount(String productId) {
        return offerMap.values().stream().filter(offer -> Objects.equals(productId,offer.getProductId())).count();
    }

    private Double getMean(String productId) {
        Locale.setDefault(Locale.US);
        OptionalDouble mean = offerMap.values().stream()
                .filter(offer -> Objects.equals(productId, offer.getProductId()))
                .mapToDouble(Offer::getAmount).average();
        if (mean.isPresent()) {
            return Double.valueOf(decimalFormat.format(mean.getAsDouble()));
        } else {
            return Double.NaN;
        }
    }

    public Offers getOffers(String productId) {
        Double offersMean = getMean(productId);
        if (offersMean.isNaN()) {
            return new Offers(getCount(productId));
        } else {
            return new Offers(offersMean, getCount(productId));
        }
    }
}
