package ulaval.glo2003.rest.resources;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.Test;
import ulaval.glo2003.controllers.exceptionMappers.response.ExceptionResponse;
import ulaval.glo2003.controllers.seller.dtos.SellerResponse;
import ulaval.glo2003.rest.EndToEndTest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import static com.google.common.truth.Truth.assertThat;
import static io.restassured.RestAssured.*;
import static ulaval.glo2003.rest.fixtures.ProductFixture.*;
import static ulaval.glo2003.rest.fixtures.SellerFixture.*;

public class SellerResourceE2ETest extends EndToEndTest {

    public static final String SELLERS_ENDPOINT = "/sellers";

    @Test
    public void givenValidSellerRequest_whenCreatingSeller_shouldReturnCreated201() {
        Map<String, String> sellerRequest = givenValidSellerRequest();

        ExtractableResponse<Response> response = given().contentType("application/json").body(sellerRequest).when().post(SELLERS_ENDPOINT).then().extract();

        assertThat(response.statusCode()).isEqualTo(STATUS_CREATED);
        assertThat(response.headers().get("location").getValue()).isNotEmpty();
    }

    @Test
    public void givenInvalidSellerAge_whenCreatingSeller_shouldReturnInvalidParameter() {
        Map<String, String> invalidSellerRequest = givenInvalidAgeSellerRequest();

        ExtractableResponse<Response> response = given().contentType("application/json").body(invalidSellerRequest).when().post(SELLERS_ENDPOINT).then().extract();
        ExceptionResponse error = response.body().as(ExceptionResponse.class);

        assertThat(response.statusCode()).isEqualTo(STATUS_BAD_REQUEST);
        assertThat(error.getCode()).isEqualTo("INVALID_PARAMETER");
    }

    @Test
    public void givenMissingParams_whenCreatingSeller_shouldReturnMissingParameter() {
        Map<String, String> emptyRequest = new HashMap<>();

        ExtractableResponse<Response> response = given().contentType("application/json").body(emptyRequest).when().post(SELLERS_ENDPOINT).then().extract();
        ExceptionResponse error = response.body().as(ExceptionResponse.class);

        assertThat(response.statusCode()).isEqualTo(STATUS_BAD_REQUEST);
        assertThat(error.getCode()).isEqualTo("MISSING_PARAMETER");
    }

    @Test
    public void givenExistingSeller_whenGettingSeller_shouldReturnSeller() throws URISyntaxException {
        Map<String,String> sellerRequest = givenValidSellerRequest();
        String sellerLocation = givenExistingSellerLocation(sellerRequest);

        ExtractableResponse<Response> response = given().when().get(new URI(sellerLocation)).then().extract();
        SellerResponse sellerResponse = response.body().as(SellerResponse.class);

        assertThat(response.statusCode()).isEqualTo(STATUS_OK);
        assertThat(sellerLocation).contains(sellerResponse.getId());
        assertThat(sellerResponse.getName()).isEqualTo(sellerRequest.get("name"));
        assertThat(sellerResponse.getBio()).isEqualTo(sellerRequest.get("bio"));
        assertThat(sellerResponse.getCreatedAt()).isNotNull();
        assertThat(sellerResponse.getProducts()).isEmpty();
    }

    @Test
    public void givenInvalidId_whenGettingSeller_shouldReturnNotFound() throws URISyntaxException {
        ExtractableResponse<Response> response = given().when().get(SELLERS_ENDPOINT + "/notfound").then().extract();
        ExceptionResponse error = response.body().as(ExceptionResponse.class);

        assertThat(response.statusCode()).isEqualTo(STATUS_NOT_FOUND);
        assertThat(error.getCode()).isEqualTo("ITEM_NOT_FOUND");
    }

    @Test
    public void givenSellerWithProducts_whenGettingSeller_shouldSellerWithItsProducts() throws URISyntaxException {
        Map<String, String> sellerRequest = givenValidSellerRequest();
        String sellerLocation = givenExistingSellerLocation(sellerRequest);
        String sellerId = getIdFromLocation(sellerLocation);
        String productId = givenExistingProductIdForSeller(sellerId);


        ExtractableResponse<Response> response = given().when().get(new URI(sellerLocation)).then().extract();
        SellerResponse sellerResponse = response.body().as(SellerResponse.class);

        assertThat(response.statusCode()).isEqualTo(STATUS_OK);
        assertThat(sellerResponse.getProducts()).isNotEmpty();
        assertThat(sellerResponse.getProducts().get(0).getId()).isEqualTo(productId);
    }
}
