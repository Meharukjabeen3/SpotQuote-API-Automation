package testcases;

import base.BaseTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CreateUserTests extends BaseTest {

    @Test
    public void createUser_validPayload_returns201() {
        Map<String, Object> body = new HashMap<>();
        body.put("name", "Jane Doe");
        body.put("job", "QA Engineer");

        given()
            .contentType("application/json")
            .body(body)
        .when()
            .post("/users")
        .then()
            .statusCode(201)
            .body("name", equalTo("Jane Doe"))
            .body("job", equalTo("QA Engineer"))
            .body("id", not(emptyOrNullString()))
            .body("createdAt", not(emptyOrNullString()));
    }

    @Test
    public void createUser_responseContainsId() {
        Map<String, Object> body = new HashMap<>();
        body.put("name", "Test User");
        body.put("job", "Developer");

        String id = given()
            .contentType("application/json")
            .body(body)
        .when()
            .post("/users")
        .then()
            .statusCode(201)
            .extract().path("id");

        assert id != null && !id.isEmpty() : "Created user should have a non-empty ID";
    }
}
