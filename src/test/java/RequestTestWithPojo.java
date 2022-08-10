import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import pojo.LoginBodyPojoModel;
import pojo.LoginResponsePojoModel;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RequestTestWithPojo {

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "https://reqres.in";
    }

    @Test
    void checkSingleUser() {

        LoginBodyPojoModel body = new LoginBodyPojoModel();
        body.setEmail("janet.weaver@reqres.in");

        given()
                .contentType(JSON)
                .body(body)
                .when()
                .get("/users/2")
                .then()
                .log().body()
                .log().status()
                .statusCode(200);

        assertEquals("To keep ReqRes free, contributions towards server costs are appreciated!",
                "To keep ReqRes free, contributions towards server costs are appreciated!");
    }

    @Test
    void checkSingleUsers23() {

        LoginBodyPojoModel body = new LoginBodyPojoModel();
        body.setBodyNull("{}");

        given()
                .body(body)
                .when()
                .get("/users/23")
                .then()
                .log().body()
                .log().status()
                .statusCode(404);

        assertEquals("{}", "{}");
    }

    @Test
    void checkSingleUnknown23() {

        LoginBodyPojoModel body = new LoginBodyPojoModel();
        body.setBodyNull("{}");

        given()
                .body(body)
                .when()
                .get("/unknown/23")
                .then()
                .log().body()
                .log().status()
                .statusCode(404);

        assertEquals("{}", "{}");
    }

    @Test
    void checkAuth() {

        LoginBodyPojoModel body = new LoginBodyPojoModel();
        body.setEmail("eve.holt@reqres.in");
        body.setPassword("pistol");body.setPassword("pistol");

        LoginResponsePojoModel response = given()
                //.filter(withCustomTemplates())
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(body)
                .when()
                .post("/login")
                .then()
                .log().body()
                .log().status()
                .statusCode(200)
                .extract().as(LoginResponsePojoModel.class);

        assertEquals("QpwL5tke4Pnpja7X4", response.getToken());
    }


    @Test
    void checkUNSUCCESSFUL() {

        LoginBodyPojoModel body = new LoginBodyPojoModel();
        body.setEmail("peter@klaven");

        LoginResponsePojoModel response = given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(body)
                .when()
                .post("/login")
                .then()
                .log().body()
                .log().status()
                .statusCode(400)
                .extract().as(LoginResponsePojoModel.class);

        assertEquals("Missing password", response.getError());

    }

}