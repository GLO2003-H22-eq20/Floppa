package ulaval.glo2003.rest.resources;

import org.junit.Test;
import ulaval.glo2003.rest.EndToEndTest;

import static com.google.common.truth.Truth.assertThat;
import static io.restassured.RestAssured.when;

public class HealthResourceE2ETest extends EndToEndTest {

    @Test
    public void whenGettingHealthStatus_itShouldReturnOk() {
        var response = when().get("/health").then().extract();

        assertThat(response.statusCode()).isEqualTo(STATUS_OK);
    }
}
