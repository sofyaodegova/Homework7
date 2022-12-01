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

    public static Response getToken() {
        Response response = getBaseRequest()
                .body("{\n\"username\":\"admin\"," +
                        "\n\"password\":\"password123\"\n}")
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

    public static String createBookingPositive() {
        String response = getBaseRequest()
                .body("{\n" +
                        "    \"firstname\": \"Sofya\",\n" +
                        "    \"lastname\": \"Odegova\",\n" +
                        "    \"totalprice\": 3500,\n" +
                        "    \"depositpaid\": true,\n" +
                        "    \"bookingdates\": {\n" +
                        "        \"checkin\": \"2022-07-13\",\n" +
                        "        \"checkout\": \"2022-07-23\"\n" +
                        "    },\n" +
                        "    \"additionalneeds\": \"Breakfast\"\n" +
                        "}")
                .log().body(true)
                .contentType(ContentType.JSON)
                .post("/booking")
                .then()
                .statusCode(200)
                .extract()
                .response().jsonPath().get("bookingid").toString();
        String bookingId = response;
        return response;
    }
    public static void createBookingNegative() {

        String response = getBaseRequest()
                .body("{\n" +
                        "    \"firstnaaaame\": \"Sofya\",\n" +
                        "    \"lastname\": \"Odegova\",\n" +
                        "    \"totalprice\": 3500,\n" +
                        "    \"depositpaid\": true,\n" +
                        "    \"bookingdates\": {\n" +
                        "        \"checkin\": \"2022-07-13\",\n" +
                        "        \"checkout\": \"2022-07-23\"\n" +
                        "    },\n" +
                        "    \"additionalneeds\": \"Breakfast\"\n" +
                        "}")
                //.log().(true)
                .contentType(ContentType.JSON)
                .post("/booking")
                .then()
                .statusCode(500)
                .log().status()
                .extract()
                .response().asString();

        System.out.println("Бронирование не создано, id не получен. Ошибка: " + response);
    }

    public static void getBooking(String bookingId, Integer expectedStatusCode) {

        String response = getBaseRequest()
                .contentType(ContentType.JSON)
                .get("/booking/" + bookingId)
                .then()
                .statusCode(expectedStatusCode)
                .extract()
                .response().asString();
        System.out.println(response);
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

    public static void deleteBooking(String token, String bookingid, Integer expectedStatusCode) {

        String response = getBaseRequest()
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + token)
                .body("{\n" +
                        "    \"totalprice\" : 5000\n" +
                        "}")
                .delete("/booking/" + bookingid)
                .then()
                .statusCode(expectedStatusCode)
                .extract()
                .response().asString();
        System.out.println(response);
    }
}