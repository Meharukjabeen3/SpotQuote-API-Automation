package testcases;

import base.BaseTest;
import models.LoginRequest;
import models.LoginResponse;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class LoginTests extends BaseTest {

    @Test
    public void login_validCredentials_returns200WithToken() {
        LoginRequest request = new LoginRequest("eve.holt@reqres.in", "cityslicka");

        LoginResponse response = given()
            .contentType("application/json")
            .body(request)
        .when()
            .post("/login")
        .then()
            .statusCode(200)
            .extract().as(LoginResponse.class);

        assertThat(response.getToken(), not(emptyOrNullString()));
    }

    @Test
    public void login_missingPassword_returns400WithError() {
        LoginRequest request = new LoginRequest("peter@klaven.com", null);

        LoginResponse response = given()
            .contentType("application/json")
            .body(request)
        .when()
            .post("/login")
        .then()
            .statusCode(400)
            .extract().as(LoginResponse.class);

        assertThat(response.getError(), not(emptyOrNullString()));
    }

    @DataProvider(name = "invalidLogins")
    public Object[][] invalidLogins() {
        return new Object[][] {
            {"peter@klaven.com", null},
            {null, "password123"},
            {"notanemail", null},
        };
    }

    @Test(dataProvider = "invalidLogins")
    public void login_invalidCredentials_returns400(String email, String password) {
        LoginRequest request = new LoginRequest(email, password);

        given()
            .contentType("application/json")
            .body(request)
        .when()
            .post("/login")
        .then()
            .statusCode(400)
            .body("error", not(emptyOrNullString()));
    }
}
