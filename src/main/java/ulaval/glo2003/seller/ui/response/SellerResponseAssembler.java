package ulaval.glo2003.seller.ui.response;

import ulaval.glo2003.offer.domain.Offer;
import ulaval.glo2003.offer.domain.ProductOffers;
import ulaval.glo2003.offer.ui.response.BuyerResponse;
import ulaval.glo2003.offer.ui.response.OfferResponse;
import ulaval.glo2003.offer.ui.response.OffersResponse;
import ulaval.glo2003.product.domain.Offers;
import ulaval.glo2003.product.ui.response.ProductResponse;
import ulaval.glo2003.seller.domain.SellerProducts;

import java.util.List;
import java.util.stream.Collectors;

public class SellerResponseAssembler {
    public SellerResponse presentSeller(SellerProducts sellerProducts) {
        return new SellerResponse(sellerProducts.getSeller().getId(),
                sellerProducts.getSeller().getName(),
                sellerProducts.getSeller().getCreatedAt().toString(),
                sellerProducts.getSeller().getBio(),
                presentProducts(sellerProducts.getProducts()));
    }

    public SellerResponse presentCurrentSeller(SellerProducts sellerProducts) {
        return new SellerResponse(sellerProducts.getSeller().getId(),
                sellerProducts.getSeller().getName(),
                sellerProducts.getSeller().getCreatedAt().toString(),
                sellerProducts.getSeller().getBio(),
                sellerProducts.getSeller().getBirthDate().toString(),
                presentCurrentProducts(sellerProducts.getProducts()));
    }

    private List<ProductResponse> presentProducts(List<ProductOffers> productsOffers) {
        return productsOffers.stream()
                .map(productOffers ->
                        new ProductResponse(productOffers.getProduct().getId(),
                                productOffers.getProduct().getCreatedAt().toString(),
                                productOffers.getProduct().getTitle(),
                                productOffers.getProduct().getDescription(),
                                productOffers.getProduct().getSuggestedPrice(),
                                presentOffers(productOffers.getOffers()),
                                productOffers.getProduct().getCategories().stream()
                                        .map(Enum::toString)
                                        .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }

    private List<ProductResponse> presentCurrentProducts(List<ProductOffers> productsOffers) {
        return productsOffers.stream()
                .map(productOffers ->
                        new ProductResponse(productOffers.getProduct().getId(),
                                productOffers.getProduct().getCreatedAt().toString(),
                                productOffers.getProduct().getTitle(),
                                productOffers.getProduct().getDescription(),
                                productOffers.getProduct().getSuggestedPrice(),
                                presentOffersWithOffer(productOffers.getOffers()),
                                productOffers.getProduct().getCategories().stream()
                                        .map(Enum::toString)
                                        .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }

    private OffersResponse presentOffers(Offers offers) {
        return new OffersResponse(offers.getMean(), offers.getCount());
    }

    private OffersResponse presentOffersWithOffer(Offers offers) {
        return new OffersResponse(offers.getMean(),
                offers.getCount(),
                offers.getMin(),
                offers.getMax(),
                presentOffer(offers.getOffers()));
    }

    private List<OfferResponse> presentOffer(List<Offer> offerList) {
        return offerList.stream()
                .map(offer ->
                        new OfferResponse(offer.getId().toString(),
                                offer.getAmount(),
                                offer.getMessage(),
                                offer.getCreatedAt().toString(),
                                presentBuyer(offer)
                        ))
                .collect(Collectors.toList());
    }

    private BuyerResponse presentBuyer(Offer offer) {
        return new BuyerResponse(offer.getName(),
                offer.getEmail(),
                offer.getPhoneNumber());
    }
}