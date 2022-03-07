package ulaval.glo2003.rest.fixtures;

import io.restassured.http.Header;
import io.restassured.specification.RequestSpecification;
import ulaval.glo2003.rest.EndToEndTest;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static ulaval.glo2003.rest.fixtures.SellerFixture.getIdFromLocation;

public class ProductFixture extends EndToEndTest {

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

    public static Map<String, Object> givenValidProductRequest() {
        return createProductRequest("Willy Waller 2006", "Epeluche patate", "10.85", new String[]{"sports"});
    }

    public static Map<String, Object> givenInvalidPriceProductRequest() {
        return createProductRequest("Willy Waller 2006", "Epeluche patate", "0.0", new String[]{"sports"});

    }

    public static RequestSpecification givenNewProductForSeller(Map<String, Object> request, String sellerId) {
        return given()
                .contentType("application/json")
                .header(new Header("X-Seller-Id", sellerId))
                .body(request);
    }

    public static String givenExistingProductLocation(Map<String, Object> request, String sellerId) {
        return given().contentType("application/json").body(request)
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
