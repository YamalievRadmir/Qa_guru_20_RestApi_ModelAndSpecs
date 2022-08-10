import io.restassured.RestAssured;
import lombok.LoginBodyLombokModel;
import org.junit.jupiter.api.BeforeAll;
import pojo.LoginBodyPojoModel;
import pojo.LoginResponsePojoModel;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.Specs.*;

public class RequestTestWithLombok {

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "https://reqres.in";
    }

    @Test
    void checkSingleUser() {

        LoginBodyLombokModel body = new LoginBodyLombokModel();
        body.setEmail("janet.weaver@reqres.in");

        given()
                .spec(requestSingleUser)
                .body(body)
                .when()
                .get()
                .then()
                .spec(responseUsers);

        assertEquals("To keep ReqRes free, contributions towards server costs are appreciated!",
                "To keep ReqRes free, contributions towards server costs are appreciated!");
    }

    @Test
    void checkSingleUsers23() {

        LoginBodyLombokModel body = new LoginBodyLombokModel();
        body.setBodyNull("{}");

        given()
                .spec(requestUser23)
                .body(body)
                .when()
                .get()
                .then()
                .spec(responseUsers);

        assertEquals("{}", "{}");
    }

    @Test
    void checkSingleUnknown23() {

        LoginBodyLombokModel body = new LoginBodyLombokModel();
        body.setBodyNull("{}");

        given()
                .spec(requestUsers)
                .body(body)
                .when()
                .get("/unknown/23")
                .then()
                .spec(responseUsers);

        assertEquals("{}", "{}");
    }

    @Test
    void checkAuth() {

        LoginBodyLombokModel body = new LoginBodyLombokModel();
        body.setEmail("eve.holt@reqres.in");
        body.setPassword("pistol");body.setPassword("pistol");

        LoginResponsePojoModel response = given()
                .spec(requestAuth)
                .body(body)
                .when()
                .post()
                .then()
                .spec(responseAuth)
                .extract().as(LoginResponsePojoModel.class);

        assertThat(response.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
    }


    @Test
    void checkUNSUCCESSFUL() {

        LoginBodyLombokModel body = new LoginBodyLombokModel();
        body.setEmail("peter@klaven");

        LoginResponsePojoModel response = given()
                .spec(requestAuth)
                .body(body)
                .when()
                .post()
                .then()
                .spec(responseCreate)
                .extract().as(LoginResponsePojoModel.class);

        assertThat(response.getError()).isEqualTo("Missing password");

    }

}