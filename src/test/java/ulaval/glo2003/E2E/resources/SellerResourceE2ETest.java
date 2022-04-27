package ulaval.glo2003.E2E.resources;

import io.restassured.http.Header;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.Test;
import ulaval.glo2003.exceptions.mappers.response.ExceptionResponse;
import ulaval.glo2003.seller.ui.response.SellerResponse;
import ulaval.glo2003.E2E.EndToEndTest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import static com.google.common.truth.Truth.assertThat;
import static io.restassured.RestAssured.given;
import static ulaval.glo2003.E2E.fixtures.ProductFixture.*;
import static ulaval.glo2003.E2E.fixtures.SellerFixture.*;

public class SellerResourceE2ETest extends EndToEndTest {
    public static final String SELLERS_ENDPOINT = "/sellers";
    public static final String CURRENT_SELLERS_ENDPOINT = "/sellers/@me";

    @Test
    public void givenValidSellerRequest_whenCreatingSeller_thenReturnCreated201() {
        Map<String, String> sellerRequest = givenValidSellerRequest();

        ExtractableResponse<Response> response = given().contentType("application/json")
                .body(sellerRequest)
                .when().post(SELLERS_ENDPOINT)
                .then().extract();

        assertThat(response.statusCode()).isEqualTo(STATUS_CREATED);
    }

    @Test
    public void givenValidSellerRequest_whenCreatingSeller_thenReturnHeaderLocationNotEmpty() {
        Map<String, String> sellerRequest = givenValidSellerRequest();

        ExtractableResponse<Response> response = given().contentType("application/json")
                .body(sellerRequest)
                .when().post(SELLERS_ENDPOINT)
                .then().extract();

        assertThat(response.headers().get("location").getValue()).isNotEmpty();
    }

    @Test
    public void givenInvalidSellerAge_whenCreatingSeller_thenReturnInvalidParameter() {
        Map<String, String> invalidSellerRequest = givenInvalidAgeSellerRequest();

        ExtractableResponse<Response> response = given().contentType("application/json")
                .body(invalidSellerRequest)
                .when().post(SELLERS_ENDPOINT)
                .then().extract();
        ExceptionResponse error = response.body().as(ExceptionResponse.class);

        assertThat(error.getCode()).isEqualTo("INVALID_PARAMETER");
    }

    @Test
    public void givenInvalidSellerAge_whenCreatingSeller_thenReturnStatusBadRequest400() {
        Map<String, String> invalidSellerRequest = givenInvalidAgeSellerRequest();

        ExtractableResponse<Response> response = given().contentType("application/json")
                .body(invalidSellerRequest)
                .when().post(SELLERS_ENDPOINT)
                .then().extract();

        assertThat(response.statusCode()).isEqualTo(STATUS_BAD_REQUEST);
    }

    @Test
    public void givenMissingParams_whenCreatingSeller_thenReturnMissingParameter() {
        Map<String, String> emptyRequest = new HashMap<>();

        ExtractableResponse<Response> response = given().contentType("application/json")
                .body(emptyRequest)
                .when().post(SELLERS_ENDPOINT)
                .then().extract();
        ExceptionResponse error = response.body().as(ExceptionResponse.class);

        assertThat(error.getCode()).isEqualTo("MISSING_PARAMETER");
    }

    @Test
    public void givenMissingParams_whenCreatingSeller_thenReturnStatusBadRequest400() {
        Map<String, String> emptyRequest = new HashMap<>();

        ExtractableResponse<Response> response = given().contentType("application/json")
                .body(emptyRequest)
                .when().post(SELLERS_ENDPOINT)
                .then().extract();
        ExceptionResponse error = response.body().as(ExceptionResponse.class);

        assertThat(response.statusCode()).isEqualTo(STATUS_BAD_REQUEST);
    }

    @Test
    public void givenExistingSeller_whenGettingSeller_thenReturnSeller() throws URISyntaxException {
        Map<String, String> sellerRequest = givenValidSellerRequest();
        String sellerLocation = givenExistingSellerLocation(sellerRequest);

        ExtractableResponse<Response> response = given().when().get(new URI(sellerLocation)).then().extract();
        SellerResponse sellerResponse = response.body().as(SellerResponse.class);

        assertThat(sellerLocation).contains(sellerResponse.getId());
        assertThat(sellerResponse.getName()).isEqualTo(sellerRequest.get("name"));
        assertThat(sellerResponse.getBio()).isEqualTo(sellerRequest.get("bio"));
        assertThat(sellerResponse.getCreatedAt()).isNotNull();
        assertThat(sellerResponse.getProducts()).isEmpty();
    }

    @Test
    public void givenExistingSeller_whenGettingSeller_thenReturnStatusOk200() throws URISyntaxException {
        Map<String, String> sellerRequest = givenValidSellerRequest();
        String sellerLocation = givenExistingSellerLocation(sellerRequest);

        ExtractableResponse<Response> response = given().when().get(new URI(sellerLocation)).then().extract();

        assertThat(response.statusCode()).isEqualTo(STATUS_OK);
    }

    @Test
    public void givenInvalidId_whenGettingSeller_thenReturnItemNotFound() {
        ExtractableResponse<Response> response = given().when().get(SELLERS_ENDPOINT + "/notfound").then().extract();
        ExceptionResponse error = response.body().as(ExceptionResponse.class);

        assertThat(error.getCode()).isEqualTo("ITEM_NOT_FOUND");
    }

    @Test
    public void givenInvalidId_whenGettingSeller_thenReturnStatusNotFound404() {
        ExtractableResponse<Response> response = given().when().get(SELLERS_ENDPOINT + "/notfound").then().extract();

        assertThat(response.statusCode()).isEqualTo(STATUS_NOT_FOUND);
    }

    @Test
    public void givenSellerWithProducts_whenGettingSeller_thenReturnSellerWithItsProducts() throws URISyntaxException {
        Map<String, String> sellerRequest = givenValidSellerRequest();
        String sellerLocation = givenExistingSellerLocation(sellerRequest);
        String sellerId = getIdFromLocation(sellerLocation);
        String productId = givenExistingProductIdForSeller(sellerId);

        ExtractableResponse<Response> response = given().when().get(new URI(sellerLocation)).then().extract();
        SellerResponse sellerResponse = response.body().as(SellerResponse.class);

        assertThat(sellerResponse.getProducts()).isNotEmpty();
        assertThat(sellerResponse.getProducts().get(0).getId()).isEqualTo(productId);
    }

    @Test
    public void givenSellerWithProducts_whenGettingSeller_thenReturnStatusOk200() throws URISyntaxException {
        Map<String, String> sellerRequest = givenValidSellerRequest();
        String sellerLocation = givenExistingSellerLocation(sellerRequest);
        String sellerId = getIdFromLocation(sellerLocation);
        String productId = givenExistingProductIdForSeller(sellerId);

        ExtractableResponse<Response> response = given().when().get(new URI(sellerLocation)).then().extract();

        assertThat(response.statusCode()).isEqualTo(STATUS_OK);
    }

    @Test
    public void givenExistingSeller_whenGettingCurrentSeller_thenReturnCurrentSeller() {
        String sellerId = givenNewSellerId();

        ExtractableResponse<Response> response = given().urlEncodingEnabled(false)
                .contentType("application/json")
                .header(new Header("X-Seller-Id", sellerId))
                .when().get(CURRENT_SELLERS_ENDPOINT)
                .then().extract();
        SellerResponse sellerResponse = response.body().as(SellerResponse.class);

        assertThat(sellerResponse.getId()).isNotNull();
        assertThat(sellerResponse.getName()).isNotNull();
        assertThat(sellerResponse.getBio()).isNotNull();
        assertThat(sellerResponse.getCreatedAt()).isNotNull();
        assertThat(sellerResponse.getBirthDate()).isNotNull();
        assertThat(sellerResponse.getProducts()).isEmpty();
    }

    @Test
    public void givenExistingSeller_whenGettingCurrentSeller_thenReturnStatusOk200() {
        String sellerId = givenNewSellerId();

        ExtractableResponse<Response> response = given().urlEncodingEnabled(false)
                .contentType("application/json")
                .header(new Header("X-Seller-Id", sellerId))
                .when().get(CURRENT_SELLERS_ENDPOINT)
                .then().extract();

        assertThat(response.statusCode()).isEqualTo(STATUS_OK);
    }

    @Test
    public void givenInvalidId_whenGettingCurrentSeller_thenReturnItemNotFoundException() {
        ExtractableResponse<Response> response = given().urlEncodingEnabled(false)
                .contentType("application/json")
                .header(new Header("X-Seller-Id", "invalidSellerId"))
                .when().get(CURRENT_SELLERS_ENDPOINT).then().extract();
        ExceptionResponse error = response.body().as(ExceptionResponse.class);

        assertThat(error.getCode()).isEqualTo("ITEM_NOT_FOUND");
    }

    @Test
    public void givenInvalidId_whenGettingCurrentSeller_thenReturnStatusNotFound404() {
        ExtractableResponse<Response> response = given().urlEncodingEnabled(false)
                .contentType("application/json")
                .header(new Header("X-Seller-Id", "invalidSellerId"))
                .when().get(CURRENT_SELLERS_ENDPOINT).then().extract();

        assertThat(response.statusCode()).isEqualTo(STATUS_NOT_FOUND);
    }

    @Test
    public void givenSellerWithProducts_whenGettingCurrentSeller_thenReturnSellerWithItsProducts()
            throws URISyntaxException {
        String sellerId = givenNewSellerId();
        Map<String, Object> productRequest = givenValidProductRequest();
        givenExistingProduct(productRequest, sellerId);

        ExtractableResponse<Response> response = given().urlEncodingEnabled(false)
                .contentType("application/json")
                .header(new Header("X-Seller-Id", sellerId))
                .when().get(CURRENT_SELLERS_ENDPOINT)
                .then().extract();
        SellerResponse sellerResponse = response.body().as(SellerResponse.class);

        assertThat(sellerResponse.getId()).isNotNull();
        assertThat(sellerResponse.getName()).isNotNull();
        assertThat(sellerResponse.getBio()).isNotNull();
        assertThat(sellerResponse.getCreatedAt()).isNotNull();
        assertThat(sellerResponse.getBirthDate()).isNotNull();
        assertThat(sellerResponse.getProducts().get(0).getId()).isNotNull();
        assertThat(sellerResponse.getProducts().get(0).getCreatedAt()).isNotNull();
        assertThat(sellerResponse.getProducts().get(0).getDescription()).isNotNull();
        assertThat(sellerResponse.getProducts().get(0).getSuggestedPrice()).isNotNull();
        assertThat(sellerResponse.getProducts().get(0).getTitle()).isNotNull();
        assertThat(sellerResponse.getProducts().get(0).getOffers().getCount()).isEqualTo(0);
        assertThat(sellerResponse.getProducts().get(0).getOffers().getItems()).isEmpty();
    }

    @Test
    public void givenSellerWithProducts_whenGettingCurrentSeller_thenReturnStatusOk200()
            throws URISyntaxException {
        String sellerId = givenNewSellerId();
        Map<String, Object> productRequest = givenValidProductRequest();
        givenExistingProduct(productRequest, sellerId);

        ExtractableResponse<Response> response = given().urlEncodingEnabled(false)
                .contentType("application/json")
                .header(new Header("X-Seller-Id", sellerId))
                .when().get(CURRENT_SELLERS_ENDPOINT)
                .then().extract();

        assertThat(response.statusCode()).isEqualTo(STATUS_OK);

    }
}
