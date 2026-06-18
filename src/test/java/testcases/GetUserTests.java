package testcases;

import base.BaseTest;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetUserTests extends BaseTest {

    @Test
    public void getSingleUser_validId_returns200() {
        given()
            .pathParam("id", 2)
        .when()
            .get("/users/{id}")
        .then()
            .statusCode(200)
            .body("data.id", equalTo(2))
            .body("data.email", not(emptyOrNullString()))
            .body("data.first_name", not(emptyOrNullString()));
    }

    @Test
    public void getSingleUser_invalidId_returns404() {
        given()
            .pathParam("id", 9999)
        .when()
            .get("/users/{id}")
        .then()
            .statusCode(404);
    }

    @Test
    public void getUserList_page1_returnsNonEmptyList() {
        given()
            .queryParam("page", 1)
        .when()
            .get("/users")
        .then()
            .statusCode(200)
            .body("data", not(empty()))
            .body("page", equalTo(1))
            .body("per_page", greaterThan(0));
    }

    @Test
    public void getUserList_responseTime_underTwoSeconds() {
        Response response = given()
            .queryParam("page", 1)
        .when()
            .get("/users");

        response.then().statusCode(200);
        long responseTime = response.getTime();
        assert responseTime < 2000 : "Response time exceeded 2s: " + responseTime + "ms";
    }
}
