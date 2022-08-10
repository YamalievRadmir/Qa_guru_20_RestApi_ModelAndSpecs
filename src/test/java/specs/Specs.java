package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.notNullValue;

public class Specs {

    public static RequestSpecification requestAuth = with()
            .baseUri("https://reqres.in")
            .basePath("/api/login")
            .log().uri()
            .log().all()
            .log().body()
            .contentType(JSON);

    public static RequestSpecification requestSingleUser = with()
            .baseUri("https://reqres.in")
            .basePath("/users/2")
            .log().uri()
            .log().all()
            .log().body()
            .contentType(JSON);

    public static RequestSpecification requestUser23 = with()
            .baseUri("https://reqres.in")
            .basePath("/users/23")
            .log().uri()
            .log().all()
            .log().body()
            .contentType(JSON);

    public static RequestSpecification requestUsers = with()
            .baseUri("https://reqres.in")
            .log().uri()
            .log().all()
            .log().body()
            .contentType(JSON);

    public static ResponseSpecification responseAuth = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(LogDetail.BODY)
            .log(LogDetail.STATUS)
            .expectBody("token", notNullValue())
            .build();

    public static ResponseSpecification responseCreate = new ResponseSpecBuilder()
            .expectStatusCode(400)
            .log(LogDetail.BODY)
            .log(LogDetail.STATUS)
            .build();

    public static ResponseSpecification responseUsers = new ResponseSpecBuilder()
            .expectStatusCode(404)
            .log(LogDetail.BODY)
            .log(LogDetail.STATUS)
            .build();
}
