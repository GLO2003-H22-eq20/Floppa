package ulaval.glo2003.E2E.resources;

import io.restassured.http.Header;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.Test;
import ulaval.glo2003.exceptions.mappers.response.ExceptionResponse;
import ulaval.glo2003.product.ui.response.ProductResponse;
import ulaval.glo2003.product.ui.response.ProductsResponse;
import ulaval.glo2003.E2E.EndToEndTest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.google.common.truth.Truth.assertThat;
import static io.restassured.RestAssured.given;
import static ulaval.glo2003.E2E.fixtures.ProductFixture.*;
import static ulaval.glo2003.E2E.fixtures.SellerFixture.givenNewSellerId;

public class ProductResourceE2ETest extends EndToEndTest {

    public static final String PRODUCTS_ENDPOINT = "/products";
    public static final String OFFERS_ENDPOINT = "offers";
    private static final String INVALID_ID = "INVALID";
    private static final String NAME = "John Doe";
    private static final String EMAIL = "john.doe@gmail.com";
    private static final String PHONE_NUMBER = "18191234567";
    private static final Double AMOUNT = 2000.333333;
    private static final String MESSAGE = "Donec porttitor interdum lacus sed finibus. Nam pulvinar facilisis "
            + "posuere. Maecenas vel lorem amet.";

    @Test
    public void givenSellerAndValidProductRequest_whenCreatingProduct_thenReturnCreated201() {
        String sellerId = givenNewSellerId();
        Map<String, Object> productRequest = givenValidProductRequest();

        ExtractableResponse<Response> response = givenNewProductForSeller(productRequest, sellerId)
                .when().post(PRODUCTS_ENDPOINT)
                .then().extract();

        assertThat(response.statusCode()).isEqualTo(STATUS_CREATED);
    }

    @Test
    public void givenSellerAndValidProductRequest_whenCreatingProduct_thenReturnLocationHeaderNotEmpty() {
        String sellerId = givenNewSellerId();
        Map<String, Object> productRequest = givenValidProductRequest();

        ExtractableResponse<Response> response = givenNewProductForSeller(productRequest, sellerId)
                .when().post(PRODUCTS_ENDPOINT)
                .then().extract();

        assertThat(response.headers().get("location").getValue()).isNotEmpty();
    }

    @Test
    public void givenBlankSellerId_whenCreatingProduct_thenReturnMissingParameterException() {
        Map<String, Object> productRequest = givenValidProductRequest();

        ExtractableResponse<Response> response = givenNewProductForSeller(productRequest, " ")
                .when().post(PRODUCTS_ENDPOINT)
                .then().extract();

        assertThat(response.statusCode()).isEqualTo(STATUS_BAD_REQUEST);
    }

    @Test
    public void givenProductWithSuggestedPriceBelowMinimum_whenCreatingProduct_thenReturnStatusBadRequest400() {
        String sellerId = givenNewSellerId();
        Map<String, Object> emptyRequest = givenInvalidPriceProductRequest();

        ExtractableResponse<Response> response = given().contentType("application/json")
                .header(new Header("X-Seller-Id", sellerId))
                .body(emptyRequest)
                .when().post(PRODUCTS_ENDPOINT)
                .then().extract();

        assertThat(response.statusCode()).isEqualTo(STATUS_BAD_REQUEST);
    }

    @Test
    public void givenProductWithSuggestedPriceBelowMinimum_whenCreatingProduct_thenReturnInvalidParameter() {
        String sellerId = givenNewSellerId();
        Map<String, Object> emptyRequest = givenInvalidPriceProductRequest();

        ExtractableResponse<Response> response = given().contentType("application/json")
                .header(new Header("X-Seller-Id", sellerId))
                .body(emptyRequest)
                .when().post(PRODUCTS_ENDPOINT)
                .then().extract();
        ExceptionResponse error = response.body().as(ExceptionResponse.class);

        assertThat(error.getCode()).isEqualTo("INVALID_PARAMETER");
    }

    @Test
    public void givenProductWithMissingParameters_whenCreatingProduct_thenReturnMissingParameter() {
        String sellerId = givenNewSellerId();
        Map<String, String> emptyRequest = new HashMap<>();

        ExtractableResponse<Response> response = given().contentType("application/json")
                .header(new Header("X-Seller-Id", sellerId))
                .body(emptyRequest)
                .when().post(PRODUCTS_ENDPOINT)
                .then().extract();
        ExceptionResponse error = response.body().as(ExceptionResponse.class);

        assertThat(error.getCode()).isEqualTo("MISSING_PARAMETER");
    }

    @Test
    public void givenProductWithMissingParameters_whenCreatingProduct_thenReturnStatusBadRequest400() {
        String sellerId = givenNewSellerId();
        Map<String, String> emptyRequest = new HashMap<>();

        ExtractableResponse<Response> response = given().contentType("application/json")
                .header(new Header("X-Seller-Id", sellerId))
                .body(emptyRequest)
                .when().post(PRODUCTS_ENDPOINT)
                .then().extract();
        ExceptionResponse error = response.body().as(ExceptionResponse.class);

        assertThat(response.statusCode()).isEqualTo(STATUS_BAD_REQUEST);
    }

    @Test
    public void givenExistingProductLocation_whenGettingProduct_thenReturnProduct() throws URISyntaxException {
        Map<String, Object> productRequest = givenValidProductRequest();
        String sellerId = givenNewSellerId();
        String productLocation = givenExistingProductLocation(productRequest, sellerId);

        ExtractableResponse<Response> response = given().when().get(new URI(productLocation)).then().extract();
        ProductResponse productResponse = response.body().as(ProductResponse.class);

        assertThat(productResponse.getTitle()).isEqualTo(productRequest.get("title"));
        assertThat(productResponse.getDescription()).isEqualTo(productRequest.get("description"));
        assertThat(productResponse.getSeller().getId()).isEqualTo(sellerId);
        assertThat(productResponse.getSuggestedPrice().toString()).isEqualTo(productRequest.get("suggestedPrice"));
        assertThat(productResponse.getOffers().getCount()).isEqualTo(0);
        assertThat(productResponse.getCreatedAt()).isNotNull();
    }

    @Test
    public void givenExistingProductLocation_whenGettingProduct_thenReturnStatusOk200() throws URISyntaxException {
        Map<String, Object> productRequest = givenValidProductRequest();
        String sellerId = givenNewSellerId();
        String productLocation = givenExistingProductLocation(productRequest, sellerId);

        ExtractableResponse<Response> response = given().when().get(new URI(productLocation)).then().extract();

        assertThat(response.statusCode()).isEqualTo(STATUS_OK);
    }

    @Test
    public void givenNotExistingProductLocation_whenGettingProduct_thenReturnItemNotFound() throws URISyntaxException {
        UUID invalidId = UUID.randomUUID();
        ExtractableResponse<Response> response = given().when().get("/products/" + invalidId).then().extract();
        ExceptionResponse error = response.body().as(ExceptionResponse.class);

        assertThat(error.getCode()).isEqualTo("ITEM_NOT_FOUND");
    }

    @Test
    public void givenNotExistingProductLocation_whenGettingProduct_thenReturnStatusNotFound404()
            throws URISyntaxException {
        UUID invalidId = UUID.randomUUID();
        ExtractableResponse<Response> response = given().when().get("/products/" + invalidId).then().extract();
        ExceptionResponse error = response.body().as(ExceptionResponse.class);

        assertThat(response.statusCode()).isEqualTo(STATUS_NOT_FOUND);
    }

    @Test
    public void givenExistingProductsAndNoFilters_whenGettingProducts_thenReturnAllProducts() {
        String sellerId = givenNewSellerId();
        String firstProductId = givenExistingProductIdForSeller(sellerId);
        String secondProductId = givenExistingProductIdForSeller(sellerId);

        ExtractableResponse<Response> response = given().when().get("/products").then().extract();
        ProductsResponse productsResponse = response.body().as(ProductsResponse.class);

        assertThat(productsResponse.getProducts()).isNotEmpty();
    }

    @Test
    public void givenInclusiveFilters_whenGettingProducts_thenReturnMatchingProducts() {
        String sellerId = givenNewSellerId();
        String productTitle = "health potion";
        Map<String, Object> productRequest = createProductRequest(productTitle,
                "10HP",
                "10",
                new String[]{"sports"}
        );
        givenExistingProductLocation(productRequest, sellerId);

        ExtractableResponse<Response> response = given()
                .queryParam("title", "PoT")
                .queryParam("minPrice", "10")
                .queryParam("maxPrice", "10")
                .queryParam("categories[]", "sports,electronics")
                .when().get("/products")
                .then().extract();
        ProductsResponse productsResponse = response.body().as(ProductsResponse.class);

        assertThat(productsResponse.getProducts()).isNotEmpty();
        assertThat(productsResponse.getProducts().get(0).getTitle()).isEqualTo(productTitle);
    }

    @Test
    public void givenExistingProductsAndExclusiveFilters_whenGettingProducts_thenNotReturnProducts() {
        String sellerId = givenNewSellerId();
        String anotherSellerId = givenNewSellerId();
        String productTitle = "mana potion";
        Map<String, Object> productRequest = createProductRequest(productTitle,
                "10MP",
                "100",
                 new String[]{"sports"}
        );
        givenExistingProductLocation(productRequest, sellerId);

        ExtractableResponse<Response> response = given()
                .queryParam("sellerId", anotherSellerId)
                .queryParam("categories[]", "electronics")
                .when().get("/products")
                .then().extract();
        ProductsResponse productsResponse = response.body().as(ProductsResponse.class);

        assertThat(productsResponse.getProducts()).isEmpty();
    }

    @Test
    public void givenExistingProduct_whenCreatingOffer_thenReturnsCreated() {
        String sellerId = givenNewSellerId();
        String productId = givenExistingProductIdForSeller(sellerId);

        var offerRequest = createOfferRequest(
                NAME,
                EMAIL,
                PHONE_NUMBER,
                AMOUNT,
                MESSAGE
        );

        ExtractableResponse<Response> response = given()
                .contentType(JSON).body(offerRequest).when()
                .post(String.format("%s/%s/%s", PRODUCTS_ENDPOINT, productId, OFFERS_ENDPOINT)).then().extract();
        assertThat(response.statusCode()).isEqualTo(STATUS_OK);
    }

    @Test
    public void givenInvalidProduct_whenCreatingOffer_thenReturnsNotFound() {
        var offerRequest = createOfferRequest(
                NAME,
                EMAIL,
                PHONE_NUMBER,
                AMOUNT,
                MESSAGE
        );
        ExtractableResponse<Response> response = given()
                .contentType(JSON).body(offerRequest).when()
                .post(String.format("%s/%s/%s", PRODUCTS_ENDPOINT, INVALID_ID, OFFERS_ENDPOINT)).then().extract();
        assertThat(response.statusCode()).isEqualTo(STATUS_NOT_FOUND);
    }
}
