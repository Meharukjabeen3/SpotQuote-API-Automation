package testcases;

import base.BaseTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteUserTests extends BaseTest {

    @Test
    public void deleteUser_validId_returns204() {
        given()
            .pathParam("id", 2)
        .when()
            .delete("/users/{id}")
        .then()
            .statusCode(204);
    }

    @Test
    public void deleteUser_responseBodyIsEmpty() {
        String body = given()
            .pathParam("id", 3)
        .when()
            .delete("/users/{id}")
        .then()
            .statusCode(204)
            .extract().asString();

        assert body.isEmpty() : "DELETE response body should be empty but was: " + body;
    }
}
