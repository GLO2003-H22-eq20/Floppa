package ulaval.glo2003.rest;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import ulaval.glo2003.Main;

import java.io.IOException;

public class EndToEndTest {

    public static final int STATUS_OK = 200;
    public static final int STATUS_CREATED = 201;
    public static final int STATUS_NOT_FOUND = 404;
    public static final int STATUS_BAD_REQUEST = 400;

    @BeforeClass
    public static void setup() throws IOException {
        setPort();
        setBasePath();
        setHost();
        RestAssured.defaultParser = Parser.JSON;

        Main.main(new String[]{});
    }

    private static void setHost() {
        String baseHost = System.getProperty("server.host");
        if (baseHost == null) {
            baseHost = "http://localhost";
        }
        RestAssured.baseURI = baseHost;
    }

    private static void setBasePath() {
        String basePath = System.getProperty("server.base");
        if (basePath == null) {
            basePath = "/";
        }
        RestAssured.basePath = basePath;
    }

    private static void setPort() {
        String port = System.getProperty("server.port");
        if (port == null) {
            RestAssured.port = 8080;
        } else {
            RestAssured.port = Integer.parseInt(port);
        }
    }

    @AfterClass
    public static void teardown() {
        Main.stop();
    }
}
