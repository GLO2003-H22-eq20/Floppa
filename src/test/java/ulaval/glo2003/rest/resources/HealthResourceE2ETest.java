package ulaval.glo2003.rest.resources;

import jakarta.ws.rs.core.Response;
import org.junit.Test;
import ulaval.glo2003.rest.EndToEndTest;

import static io.restassured.RestAssured.*;
import static com.google.common.truth.Truth.*;

public class HealthResourceE2ETest extends EndToEndTest {

    @Test
    public void whenGettingHealthStatus_itShouldReturnOk() {
        var response = when().get("/health").then().extract();

        assertThat(response.statusCode()).isEqualTo(STATUS_OK);
    }
}
