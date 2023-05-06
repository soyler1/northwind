package kodlamaio.northwind.integration;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsersIT {

    @LocalServerPort
    private int port;

    @BeforeAll
    void setup(){
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    @Test
    @DisplayName("When post user should return success = true, message = 'Kullanıcı eklendi' and status code = 201")
    void addScenario(){
        String requestBody = "{\n" +
                "  \"email\": \"admin@admin.com\",\n" +
                "  \"password\": \"admin\"\n" +
                "}";

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when().post("api/users/add")
                .then().assertThat().statusCode(201)
                .body("success", equalTo(true))
                .body("message", equalTo("Kullanıcı eklendi"));
    }
}
