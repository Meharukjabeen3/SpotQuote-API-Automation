package base;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.BeforeSuite;
import utilities.ConfigReader;

public class BaseTest {

    @BeforeSuite
    public void setup() {
        RestAssured.baseURI = ConfigReader.get("base.url");
        RestAssured.requestSpecification = new RequestSpecBuilder()
            .addHeader("x-api-key", ConfigReader.get("api.key"))
            .build();
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }
}
