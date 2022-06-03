package testApiTesting;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.net.http.HttpClient;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

@Slf4j
public class UsersTest {

    public static final String BASE_URl = "https://gorest.co.in/public/v2";

    @Test
    void checkGetAllUsersEndPoint() {
        log.info("START: Get All Users");
        given()
                .baseUri(BASE_URl)
                .when()
                .get("/users")
                .then()
                .statusCode(HttpStatus.SC_OK);
                log.info("END: Get All Users");
    }
}
