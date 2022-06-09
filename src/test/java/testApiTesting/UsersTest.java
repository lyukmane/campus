package testApiTesting;

import io.restassured.response.Response;
import jdk.jfr.ContentType;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.RandomString;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import testClient.ModelClient;
import testClient.UsersModel;

@Slf4j
public class UsersTest {

    public static final String BASE_URl = "https://gorest.co.in/public/v2";

    @Test
    void checkGetAllUsersEndPoint() {
        log.info("START: Get All Users");
       Response response = given()
                .baseUri(BASE_URl)
                .when()
                .get("/users");
                response.then().statusCode(HttpStatus.SC_OK);
                response.then().statusCode(200);
                response.then().statusLine("HTTP/1.1 200 OK");
                response.then().body(Matchers.not(Matchers.empty()));
                response.then().header("Content-type", "aplication/json");
                log.info("END: Get All Users");
    }

    @Test
    void checkAddUserBeenTaken() {
        log.info("START: Create New User");
        Response response = given()
                .baseUri(BASE_URl)
                .when()
                .header("Content-Type","application/json")
                .header("Authorization","Bearer 4cbbc107b12accc2e283bcfc0c8e03bc702ca91367e2e3a7191edffb5d3d79e0")
                .body("{" +
                        "\"name\":\"Tenali Ramakrishna\", " +
                        "\"gender\":\"male\", " +
                        "\"email\":\"tenali.ramakrishna@15ce.com\", " +
                        "\"status\":\"active\"" +
                        "}")
                .post("/users");
        response.prettyPrint();
        response.then().statusCode(HttpStatus.SC_UNPROCESSABLE_ENTITY);
        response.then().statusCode(422);
        response.then().statusLine("HTTP/1.1 422 Unprocessable Entity");
        response.then().body(Matchers.not(Matchers.empty()));
        response.then().header("Content-type", "application/json; charset=utf-8");
        log.info("END: Get All Users");
    }

    @Test
    void checkAddNewUserEndpoint_returns_401_not_authorized() {
        Response postResponse = given().baseUri(BASE_URl)
                .when()
                .post("/users");
        postResponse.then().statusCode(401);
    }
    @Test
    void checkAddNewUserSucsses() {
        log.info("START: Create New User");
        String uniqueGenerateEmail = RandomString.make(5) + "@mail.com";
        Response postResponse = given()
                .baseUri(BASE_URl)
                .when()
                .header("Content-Type","application/json")
                .header("Authorization","Bearer 4cbbc107b12accc2e283bcfc0c8e03bc702ca91367e2e3a7191edffb5d3d79e0")
                .body(String.format("{" +
                        "\"name\":\"Tenali Ramakrishna\", " +
                        "\"gender\":\"male\", " +
                        "\"email\":\"%s\", " +
                        "\"status\":\"active\"" +
                        "}", uniqueGenerateEmail))
                .post("/users");
        postResponse.prettyPrint();
        System.out.println(postResponse.toString());
        postResponse.then().statusCode(HttpStatus.SC_CREATED);
        postResponse.then().statusCode(201);
        postResponse.then().statusLine("HTTP/1.1 201 Created");
        postResponse.then().body(Matchers.not(Matchers.empty()));
        postResponse.then().header("Content-type", "application/json; charset=utf-8");
        postResponse.then().body("date", Matchers.hasEntry("email", uniqueGenerateEmail));

        log.info("END: Get All Users");
    }

    @Test
    void checkAddNewUserEndpoint_success() {
        String uniqueEmailGenerated = RandomString.make(5) + "@test.com";
        String name = "Test name";

        ModelClient testClient = new ModelClient();
        testClient.setName(name);
        testClient.setEmail(uniqueEmailGenerated);
        testClient.setGender("male");
        testClient.setStatus("active");

        Response postResponse = given().baseUri(BASE_URl)
                .when()
                .header("Authorization", "Bearer 6a2e66915f5232398603c71eda843f6076c46a853840ec5046ae6b7190db7f36")
                .header("Content-type", "application/json")
                .body(testClient)
                .post("/users");

        postResponse.prettyPrint();
        postResponse.then().statusCode(201);

        ModelClient actualModel = postResponse.body().as(ModelClient.class);

        //with assertions
        Assertions.assertThat(actualModel.getName()).isEqualTo(name);
        Assertions.assertThat(actualModel.getEmail()).isEqualTo(uniqueEmailGenerated);
        Assertions.assertThat(actualModel.getGender()).isEqualTo(testClient.getGender());
        Assertions.assertThat(actualModel.getStatus()).isEqualTo(testClient.getStatus());
        org.junit.jupiter.api.Assertions.assertNotNull(actualModel.getId());

        //with matchers
//        postResponse.then().body("data", Matchers.hasEntry("email", uniqueEmailGenerated));
//        postResponse.then().body("data", Matchers.hasEntry("name", testClient.getName()));
//        postResponse.then().body("data", Matchers.hasEntry("gender", testClient.getGender()));
//        postResponse.then().body("data", Matchers.hasEntry("status", testClient.getStatus()));
//
//        postResponse.then().body("data", Matchers.hasKey("id"));
//        postResponse.then().body("data.id", Matchers.not(Matchers.empty()));
    }

}
