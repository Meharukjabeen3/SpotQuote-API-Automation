package testcases;

import base.BaseTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UpdateUserTests extends BaseTest {

    @Test
    public void updateUser_put_returns200() {
        Map<String, Object> body = new HashMap<>();
        body.put("name", "Jane Doe");
        body.put("job", "Senior QA Engineer");

        given()
            .contentType("application/json")
            .pathParam("id", 2)
            .body(body)
        .when()
            .put("/users/{id}")
        .then()
            .statusCode(200)
            .body("name", equalTo("Jane Doe"))
            .body("job", equalTo("Senior QA Engineer"))
            .body("updatedAt", not(emptyOrNullString()));
    }

    @Test
    public void updateUser_patch_returns200() {
        Map<String, Object> body = new HashMap<>();
        body.put("job", "Lead QA Engineer");

        given()
            .contentType("application/json")
            .pathParam("id", 2)
            .body(body)
        .when()
            .patch("/users/{id}")
        .then()
            .statusCode(200)
            .body("job", equalTo("Lead QA Engineer"))
            .body("updatedAt", not(emptyOrNullString()));
    }
}
