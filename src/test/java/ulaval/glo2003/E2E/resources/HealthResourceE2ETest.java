package ulaval.glo2003.E2E.resources;

import org.junit.Ignore;
import org.junit.Test;
import ulaval.glo2003.E2E.EndToEndTest;

import static com.google.common.truth.Truth.assertThat;
import static io.restassured.RestAssured.when;

public class HealthResourceE2ETest extends EndToEndTest {

    @Test
    @Ignore("testing")
    public void whenGettingHealthStatus_itShouldReturnOk() {
        var response = when().get("/health").then().extract();

        assertThat(response.statusCode()).isEqualTo(STATUS_OK);
    }
}
