import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestRequests {

    public static RequestSpecification getBaseRequest() {
        return RestAssured.given()
                .baseUri("https://restful-booker.herokuapp.com")
                .contentType(ContentType.JSON);
    }

    public static Response getToken(String addBody) {
        Response response = getBaseRequest()
                .body(addBody)
                .log().body(true)
                .contentType(ContentType.JSON)
                .post("/auth")
                .then()
                .statusCode(200)
                .extract()
                .response();
        System.out.println(response);
        return response;
    }

    public static Response createBooking(String addBody, Integer expectedStatusCode) {
        Response response = getBaseRequest()
                .body(addBody)
                .log().body(true)
                .contentType(ContentType.JSON)
                .post("/booking")
                .then()
                .statusCode(expectedStatusCode)
                .extract()
                .response();
        return response;
    }

    public static Response getBooking(String bookingId, Integer expectedStatusCode) {

        Response response = getBaseRequest()
                .contentType(ContentType.JSON)
                .get("/booking/" + bookingId)
                .then()
                .statusCode(expectedStatusCode)
                .extract()
                .response();
        System.out.println(response);
        return response;
    }

    public static Response updateBooking(String token, String updatedBody, String bookingid, Integer expectedStatusCode) {

        Response response = getBaseRequest()
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + token)
                .body(updatedBody)
                .log().body()
                .put("/booking/" + bookingid)
                .then()
                .statusCode(expectedStatusCode)
                .extract()
                .response();
        return response;
    }

    public static Response partialUpdateBooking(String token, String updatedBody, String bookingid, Integer expectedStatusCode) {

        Response response = getBaseRequest()
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + token)
                .body(updatedBody)
                .log().body()
                .patch("/booking/" + bookingid)
                .then()
                .statusCode(expectedStatusCode)
                .extract()
                .response();
        return response;
    }

    public static Response deleteBooking(String token, String bookingid, Integer statusCode) {

        Response response = getBaseRequest()
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + token)
                .delete("/booking/" + bookingid)
                .then()
                .statusCode(statusCode)
                .extract()
                .response();
        return response;
    }
}