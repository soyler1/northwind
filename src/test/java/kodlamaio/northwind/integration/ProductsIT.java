package kodlamaio.northwind.integration;


import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProductsIT {

    @LocalServerPort
    private int port;

    @BeforeAll
    void setup(){
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    @Test
    @DisplayName("When get /api/products/getall should return success true, message 'Data listelendi' and status code 200")
    void getAllScenario(){
        RestAssured.given().header("Content-Type", "application/json")
                .when().get("/api/products/getall")
                .then().assertThat().statusCode(200)
                .body("success", equalTo(true))
                .body("message", equalTo("Data listelendi"));

    }

    @Test
    @DisplayName("When get /api/products/getallByPage with page no and page size should return success true, message 'Data listelendi' and status code 200")
    void getAllByPageScenario(){
        RestAssured.given().header("Content-Type", "application/json")
                .when().get("/api/products/getallByPage?pageNo=1&pageSize=10")
                .then().assertThat().statusCode(200)
                .body("success", equalTo(true))
                .body("message", equalTo("Data listelendi"));

    }

    @Test
    @DisplayName("When get /api/products/getallAsc should return success true, message 'Data listelendi' and status code 200")
    void getAllAscScenario(){
        RestAssured.given().header("Content-Type", "application/json")
                .when().get("/api/products/getallAsc")
                .then().assertThat().statusCode(200)
                .body("success", equalTo(true))
                .body("message", equalTo("Data listelendi"));

    }

    @Test
    @DisplayName("When post product should return success = true, message = 'Ürün eklendi' and status code 201")
    void addScenario(){

        String requestBody = "{\n" +
                "  \"category\": {\n" +
                "    \"categoryId\": 1\n" +
                "  },\n" +
                "  \"productName\": \"Test Instance\",\n" +
                "  \"quantityPerUnit\": \"Test Instance\",\n" +
                "  \"unitPrice\": 0.0,\n" +
                "  \"unitsInStock\": 0\n" +
                "}";
        RestAssured.given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when().post("api/products/add")
                .then().assertThat().statusCode(201)
                .body("success", equalTo(true))
                .body("message", equalTo("Ürün eklendi"));

    }

    @Test
    @DisplayName("When get /api/products/getByProductName should return success true, message 'Data listelendi' and status code 200")
    void getByProductNameScenario(){
        RestAssured.given().header("Content-Type", "application/json")
                .when().get("/api/products/getByProductName?productName=Chai")
                .then().assertThat().statusCode(200)
                .body("success", equalTo(true))
                .body("message", equalTo("Data listelendi"));

    }

    @Test
    @DisplayName("When get /api/products/getByProductNameAndCategoryId should return success true, message 'Data listelendi' and status code 200")
    void getByProductNameAndCategoryIdScenario(){
        RestAssured.given().header("Content-Type", "application/json")
                .when().get("/api/products/getByProductNameAndCategoryId?productName=Chai&categoryId=1")
                .then().assertThat().statusCode(200)
                .body("success", equalTo(true))
                .body("message", equalTo("Data listelendi"));

    }

    @Test
    @DisplayName("When get /api/products/getByProductNameOrCategoryId should return success true, message 'Data listelendi' and status code 200")
    void getByProductNameOrCategoryIdScenario(){
        RestAssured.given().header("Content-Type", "application/json")
                .when().get("/api/products/getByProductNameOrCategoryId?productName=Chai&categoryId=2")
                .then().assertThat().statusCode(200)
                .body("success", equalTo(true))
                .body("message", equalTo("Data listelendi"));

    }

    @Test
    @DisplayName("When get /api/products/getByCategoryIdIn should return success true, message 'Data listelendi' and status code 200")
    void getByCategoryIdInScenario(){
        RestAssured.given().header("Content-Type", "application/json")
                .when().get("/api/products/getByCategoryIdIn?categories=1&categories=2")
                .then().assertThat().statusCode(200)
                .body("success", equalTo(true))
                .body("message", equalTo("Data listelendi"));

    }

    @Test
    @DisplayName("When get /api/products/getByProductNameContains should return success true, message 'Data listelendi' and status code 200")
    void getByProductNameContainsScenario(){
        RestAssured.given().header("Content-Type", "application/json")
                .when().get("/api/products/getByProductNameContains?productName=han")
                .then().assertThat().statusCode(200)
                .body("success", equalTo(true))
                .body("message", equalTo("Data listelendi"));

    }

    @Test
    @DisplayName("When get /api/products/getByProductNameStartsWith should return success true, message 'Data listelendi' and status code 200")
    void getByProductNameStartsWithScenario(){
        RestAssured.given().header("Content-Type", "application/json")
                .when().get("/api/products/getByProductNameStartsWith?productName=Ch")
                .then().assertThat().statusCode(200)
                .body("success", equalTo(true))
                .body("message", equalTo("Data listelendi"));

    }

    @Test
    @DisplayName("When get /api/products/getByNameAndCategory should return success true, message 'Data listelendi' and status code 200")
    void getByNameAndCategoryScenario(){
        RestAssured.given().header("Content-Type", "application/json")
                .when().get("/api/products/getByNameAndCategory?productName=Chai&categoryId=1")
                .then().assertThat().statusCode(200)
                .body("success", equalTo(true))
                .body("message", equalTo("Data listelendi"));

    }

    @Test
    @DisplayName("When get /api/products/getProductWithCategoryDetails should return success true, message 'Data listelendi' and status code 200")
    void getProductWithCategoryDetailsScenario(){
        RestAssured.given().header("Content-Type", "application/json")
                .when().get("/api/products/getProductWithCategoryDetails")
                .then().assertThat().statusCode(200)
                .body("success", equalTo(true))
                .body("message", equalTo("Data listelendi"));

    }

}
