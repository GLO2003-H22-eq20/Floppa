package ulaval.glo2003.E2E.fixtures;

import io.restassured.http.Header;
import io.restassured.specification.RequestSpecification;
import ulaval.glo2003.E2E.EndToEndTest;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static ulaval.glo2003.E2E.fixtures.SellerFixture.getIdFromLocation;

public class ProductFixture extends EndToEndTest {
    public static final String JSON = "application/json";
    public static final String PRODUCT_TITLE = "Willy Waller 2006";
    public static final String PRODUCT_DESCRIPTION = "Epeluche patate";
    public static final String INVALID_PRODUCT_PRICE = "0.0";
    public static final String PRODUCT_PRICE = "10.85";
    public static final String[] PRODUCT_CATEGORY = new String[]{"sports"};

    public static Map<String, Object> createProductRequest(String title,
            String description,
            String suggestedPrice,
            String[] categories
    ) {
        Map<String, Object> productRequest = new HashMap<>();
        productRequest.put("title", title);
        productRequest.put("description", description);
        productRequest.put("suggestedPrice", suggestedPrice);
        productRequest.put("categories", categories);

        return productRequest;
    }

    public static Map<String, Object> createOfferRequest(
            String name,
            String email,
            String phoneNumber,
            Double amount,
            String message) {
        Map<String, Object> offerRequest = new HashMap<>();
        offerRequest.put("name", name);
        offerRequest.put("email", email);
        offerRequest.put("phoneNumber", phoneNumber);
        offerRequest.put("amount", amount);
        offerRequest.put("message", message);

        return offerRequest;
    }

    public static Map<String, Object> givenValidProductRequest() {
        return createProductRequest(PRODUCT_TITLE, PRODUCT_DESCRIPTION, PRODUCT_PRICE, PRODUCT_CATEGORY);
    }

    public static Map<String, Object> givenInvalidPriceProductRequest() {
        return createProductRequest(PRODUCT_TITLE, PRODUCT_DESCRIPTION, INVALID_PRODUCT_PRICE, PRODUCT_CATEGORY);

    }

    public static RequestSpecification givenNewProductForSeller(Map<String, Object> request, String sellerId) {
        return given()
                .contentType(JSON)
                .header(new Header("X-Seller-Id", sellerId))
                .body(request);
    }

    public static String givenExistingProductLocation(Map<String, Object> request, String sellerId) {
        return given().contentType(JSON).body(request)
                .header(new Header("X-Seller-Id", sellerId))
                .when().post("/products")
                .then().extract()
                .headers().get("location").getValue();
    }

    public static String givenExistingProductIdForSeller(String sellerId) {
        Map<String, Object> productRequest = givenValidProductRequest();
        String productLocation = givenExistingProductLocation(productRequest, sellerId);
        return getIdFromLocation(productLocation);
    }
}
