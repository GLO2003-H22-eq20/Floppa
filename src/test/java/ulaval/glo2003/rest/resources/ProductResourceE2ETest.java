package ulaval.glo2003.rest.resources;

import io.restassured.http.Header;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.Test;
import ulaval.glo2003.controllers.exception.response.ExceptionResponse;
import ulaval.glo2003.controllers.product.dtos.ProductResponse;
import ulaval.glo2003.controllers.product.dtos.ProductsResponse;
import ulaval.glo2003.rest.EndToEndTest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import static com.google.common.truth.Truth.assertThat;
import static io.restassured.RestAssured.given;
import static ulaval.glo2003.rest.fixtures.ProductFixture.createProductRequest;
import static ulaval.glo2003.rest.fixtures.ProductFixture.givenExistingProductIdForSeller;
import static ulaval.glo2003.rest.fixtures.ProductFixture.givenExistingProductLocation;
import static ulaval.glo2003.rest.fixtures.ProductFixture.givenInvalidPriceProductRequest;
import static ulaval.glo2003.rest.fixtures.ProductFixture.givenNewProductForSeller;
import static ulaval.glo2003.rest.fixtures.ProductFixture.givenValidProductRequest;
import static ulaval.glo2003.rest.fixtures.SellerFixture.givenNewSellerId;

public class ProductResourceE2ETest extends EndToEndTest {

    public static final String PRODUCTS_ENDPOINT = "/products";

    @Test
    public void givenSellerAndValidProductRequest_whenCreatingProduct_shouldReturnCreated201() {
        String sellerId = givenNewSellerId();
        Map<String, Object> productRequest = givenValidProductRequest();

        ExtractableResponse<Response> response = givenNewProductForSeller(productRequest, sellerId)
                .when().post(PRODUCTS_ENDPOINT)
                .then().extract();

        assertThat(response.statusCode()).isEqualTo(STATUS_CREATED);
        assertThat(response.headers().get("location").getValue()).isNotEmpty();
    }

    @Test
    public void givenNoSellerId_whenCreatingProduct_shouldReturnNotFound() {
        Map<String, Object> productRequest = givenValidProductRequest();

        ExtractableResponse<Response> response = givenNewProductForSeller(productRequest, null)
                .when().post(PRODUCTS_ENDPOINT)
                .then().extract();

        assertThat(response.statusCode()).isEqualTo(STATUS_NOT_FOUND);
    }

    @Test
    public void givenProductWithSuggestedPriceBelowMinimum_whenCreatingProduct_shouldReturnInvalidParameter() {
        String sellerId = givenNewSellerId();
        Map<String, Object> emptyRequest = givenInvalidPriceProductRequest();

        ExtractableResponse<Response> response = given().contentType("application/json")
                .header(new Header("X-Seller-Id", sellerId))
                .body(emptyRequest)
                .when().post(PRODUCTS_ENDPOINT)
                .then().extract();
        ExceptionResponse error = response.body().as(ExceptionResponse.class);

        assertThat(response.statusCode()).isEqualTo(STATUS_BAD_REQUEST);
        assertThat(error.getCode()).isEqualTo("INVALID_PARAMETER");
    }

    @Test
    public void givenProductWithMissingParameters_whenCreatingProduct_shouldReturnMissingParameter() {
        String sellerId = givenNewSellerId();
        Map<String, String> emptyRequest = new HashMap<>();

        ExtractableResponse<Response> response = given().contentType("application/json")
                .header(new Header("X-Seller-Id", sellerId))
                .body(emptyRequest)
                .when().post(PRODUCTS_ENDPOINT)
                .then().extract();
        ExceptionResponse error = response.body().as(ExceptionResponse.class);

        assertThat(response.statusCode()).isEqualTo(STATUS_BAD_REQUEST);
        assertThat(error.getCode()).isEqualTo("MISSING_PARAMETER");
    }

    @Test
    public void givenExistingProductLocation_whenGettingProduct_shouldReturnProduct() throws URISyntaxException {
        Map<String, Object> productRequest = givenValidProductRequest();
        String sellerId = givenNewSellerId();
        String productLocation = givenExistingProductLocation(productRequest, sellerId);

        ExtractableResponse<Response> response = given().when().get(new URI(productLocation)).then().extract();
        ProductResponse productResponse = response.body().as(ProductResponse.class);

        assertThat(response.statusCode()).isEqualTo(STATUS_OK);
        assertThat(productResponse.getTitle()).isEqualTo(productRequest.get("title"));
        assertThat(productResponse.getDescription()).isEqualTo(productRequest.get("description"));
        assertThat(productResponse.getSeller().getId()).isEqualTo(sellerId);
        assertThat(productResponse.getSuggestedPrice().toString()).isEqualTo(productRequest.get("suggestedPrice"));
        assertThat(productResponse.getOffers().getCount()).isEqualTo(0);
        assertThat(productResponse.getCreatedAt()).isNotNull();
    }

    @Test
    public void givenNotExistingProductLocation_whenGettingProduct_shouldNotFound() throws URISyntaxException {
        ExtractableResponse<Response> response = given().when().get("/products/notfound").then().extract();
        ExceptionResponse error = response.body().as(ExceptionResponse.class);

        assertThat(response.statusCode()).isEqualTo(STATUS_NOT_FOUND);
        assertThat(error.getCode()).isEqualTo("ITEM_NOT_FOUND");
    }

    @Test
    public void givenExistingProductsAndNoFilters_whenGettingProducts_shouldReturnAllProducts() {
        String sellerId = givenNewSellerId();
        String firstProductId = givenExistingProductIdForSeller(sellerId);
        String secondProductId = givenExistingProductIdForSeller(sellerId);

        ExtractableResponse<Response> response = given().when().get("/products").then().extract();
        ProductsResponse productsResponse = response.body().as(ProductsResponse.class);

        assertThat(productsResponse.getProducts()).isNotEmpty();
    }

    @Test
    public void givenInclusiveFilters_whenGettingProducts_shouldReturnMatchingProducts() {
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
    public void givenExistingProductsAndExclusiveFilters_whenGettingProducts_shouldNotReturnProducts() {
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
}