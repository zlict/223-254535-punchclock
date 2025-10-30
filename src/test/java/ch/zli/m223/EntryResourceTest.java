package ch.zli.m223;

import io.quarkus.test.junit.QuarkusTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;

@QuarkusTest
public class EntryResourceTest {

    @Test
    public void testIndexEndpoint() {
        given()
                .when().get("/entries")
                .then()
                .statusCode(200)
                .body("$", notNullValue())
                .body("$", instanceOf(java.util.List.class))
                .body("size()", greaterThanOrEqualTo(0));
    }

    @Test
    public void testCreateEndpoint() {
        var entryJson = """
                    {
                      "checkIn": "2025-10-30T09:00:00",
                      "checkOut": "2025-10-30T11:00:00",
                      "description": "Test Entry",
                      "categoryId": 1,
                      "tagIds": [2]
                    }
                """;

        given()
                .header("Content-Type", "application/json")
                .body(entryJson)
                .when().post("/entries")
                .then()
                .statusCode(201)
                .body("description", is("Test Entry"));

        given()
                .when().get("/entries")
                .then()
                .statusCode(200)
                .body("description", hasItem("Test Entry"));
    }

}