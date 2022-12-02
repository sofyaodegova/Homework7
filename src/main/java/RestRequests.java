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

    public static void updateBooking(String token, String bookingid, Integer expectedStatusCode) {

        String response = getBaseRequest()
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + token)
                .body("{\n" +
                        "    \"firstname\" : \"Sofya\",\n" +
                        "    \"lastname\" : \"Odegova\",\n" +
                        "    \"totalprice\" : 3500,\n" +
                        "    \"depositpaid\" : true,\n" +
                        "    \"bookingdates\" : {\n" +
                        "        \"checkin\" : \"2022-07-13\",\n" +
                        "        \"checkout\" : \"2022-08-01\"\n" +
                        "    },\n" +
                        "    \"additionalneeds\" : \"Breakfast\"\n" +
                        "}")
                .log().body()
                .put("/booking/" + bookingid)
                .then()
                .statusCode(expectedStatusCode)
                .extract()
                .response().asString();
        System.out.println(response);
    }

    public static void partialUpdateBooking(String token, String bookingid, Integer expectedStatusCode) {

        String response = getBaseRequest()
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + token)
                .body("{\n" +
                        "    \"totalprice\" : 5000\n" +
                        "}")
                .log().body()
                .patch("/booking/" + bookingid)
                .then()
                .statusCode(expectedStatusCode)
                .extract()
                .response().asString();
        System.out.println(response);
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
        System.out.println(response);
        return response;
    }
}