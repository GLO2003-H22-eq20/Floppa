package ulaval.glo2003.E2E.fixtures;

import io.restassured.http.Header;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import ulaval.glo2003.E2E.EndToEndTest;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class SellerFixture extends EndToEndTest {
    public static final String SELLER_NAME = "Boba Fett";
    public static final String SELLER_BIO = "Bounty Hunter";
    public static final String SELLER_BIRTH_DATE = "1996-09-15";
    public static final String SELLER_INVALID_BIRTH_DATE = LocalDate.now().toString();

    public static Map<String, String> givenInvalidAgeSellerRequest() {
        Map<String, String> invalidRequest = new HashMap<>();
        invalidRequest.put("name", SELLER_NAME);
        invalidRequest.put("bio", SELLER_BIO);
        invalidRequest.put("birthDate", SELLER_INVALID_BIRTH_DATE);

        return invalidRequest;
    }

    public static Map<String, String> givenValidSellerRequest() {
        Map<String, String> sellerRequest = new HashMap<>();
        sellerRequest.put("name", SELLER_NAME);
        sellerRequest.put("bio", SELLER_BIO);
        sellerRequest.put("birthDate", SELLER_BIRTH_DATE);

        return sellerRequest;
    }

    public static String givenNewSellerId() {
        Map<String, String> request = givenValidSellerRequest();
        String location = givenExistingSellerLocation(request);
        return getIdFromLocation(location);
    }

    public static String givenExistingSellerLocation(Map<String, String> request) {
        return given().contentType("application/json").body(request)
                .when().post("/sellers")
                .then().extract()
                .headers().get("location").getValue();
    }

    public static String getIdFromLocation(String location) {
        String[] sections = location.split("/");
        return sections[sections.length - 1];
    }
}
