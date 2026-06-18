package testcases;

import base.BaseTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class LoginTests extends BaseTest {

    @Test
    public void login_validCredentials_returns200WithToken() {
        Map<String, Object> body = new HashMap<>();
        body.put("email", "eve.holt@reqres.in");
        body.put("password", "cityslicka");

        given()
            .contentType("application/json")
            .body(body)
        .when()
            .post("/login")
        .then()
            .statusCode(200)
            .body("token", not(emptyOrNullString()));
    }

    @Test
    public void login_missingPassword_returns400WithError() {
        Map<String, Object> body = new HashMap<>();
        body.put("email", "peter@klaven.com");

        given()
            .contentType("application/json")
            .body(body)
        .when()
            .post("/login")
        .then()
            .statusCode(400)
            .body("error", not(emptyOrNullString()));
    }
}
