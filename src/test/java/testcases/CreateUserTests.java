package testcases;

import base.BaseTest;
import models.CreateUserRequest;
import models.CreateUserResponse;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CreateUserTests extends BaseTest {

    @Test
    public void createUser_validPayload_returns201() {
        CreateUserRequest request = new CreateUserRequest("Jane Doe", "QA Engineer");

        CreateUserResponse response = given()
            .contentType("application/json")
            .body(request)
        .when()
            .post("/users")
        .then()
            .statusCode(201)
            .extract().as(CreateUserResponse.class);

        assertThat(response.getName(), equalTo("Jane Doe"));
        assertThat(response.getJob(), equalTo("QA Engineer"));
        assertThat(response.getId(), not(emptyOrNullString()));
        assertThat(response.getCreatedAt(), not(emptyOrNullString()));
    }

    @Test
    public void createUser_responseContainsId() {
        CreateUserRequest request = new CreateUserRequest("Test User", "Developer");

        CreateUserResponse response = given()
            .contentType("application/json")
            .body(request)
        .when()
            .post("/users")
        .then()
            .statusCode(201)
            .extract().as(CreateUserResponse.class);

        assertThat(response.getId(), not(emptyOrNullString()));
    }
}
