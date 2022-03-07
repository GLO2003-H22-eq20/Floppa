package ulaval.glo2003.rest.fixtures;

import ulaval.glo2003.rest.EndToEndTest;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class SellerFixture extends EndToEndTest {

    public static Map<String, String> givenInvalidAgeSellerRequest() {
        Map<String,String> invalidRequest = new HashMap<>();
        invalidRequest.put("name", "Boba Fett");
        invalidRequest.put("bio", "Bounty Hunter");
        invalidRequest.put("birthDate", LocalDate.now().toString());

        return invalidRequest;
    }

    public static Map<String, String> givenValidSellerRequest() {
        Map<String,String> sellerRequest = new HashMap<>();
        sellerRequest.put("name", "Boba Fett");
        sellerRequest.put("bio", "Bounty Hunter");
        sellerRequest.put("birthDate", "1996-09-15");

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
        String[] sections =  location.split("/");
        return sections[sections.length - 1];
    }
}
