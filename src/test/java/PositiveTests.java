import org.junit.Test;

public class PositiveTests {
    public static void main(String[] args) {
    }

    @Test
    public void positiveTestGetToken() {
        String addBody = "{\n\"username\":\"admin\"," +
                "\n\"password\":\"password123\"\n}";
        String token = RestRequests.getToken(addBody).jsonPath().get("token");
        System.out.println("Получили токен: " + token);
    }

    @Test
    public void positiveTestCreateBooking() {
        String addBody = "{\n" +
                "    \"firstname\": \"Sofya\",\n" +
                "    \"lastname\": \"Odegova\",\n" +
                "    \"totalprice\": 3500,\n" +
                "    \"depositpaid\": true,\n" +
                "    \"bookingdates\": {\n" +
                "        \"checkin\": \"2022-07-13\",\n" +
                "        \"checkout\": \"2022-07-23\"\n" +
                "    },\n" +
                "    \"additionalneeds\": \"Breakfast\"\n" +
                "}";
        int expectedStatusCode = 200;
        String bookingId = RestRequests.createBooking(addBody, expectedStatusCode).jsonPath().getString("bookingid");
        System.out.println("Бронирование создано, id получен: " + bookingId);
    }

    @Test
    public void positiveTestGetBooking() {
        String addBody = "{\n" +
                "    \"firstname\": \"Sofya\",\n" +
                "    \"lastname\": \"Odegova\",\n" +
                "    \"totalprice\": 3500,\n" +
                "    \"depositpaid\": true,\n" +
                "    \"bookingdates\": {\n" +
                "        \"checkin\": \"2022-07-13\",\n" +
                "        \"checkout\": \"2022-07-23\"\n" +
                "    },\n" +
                "    \"additionalneeds\": \"Breakfast\"\n" +
                "}";
        int expectedStatusCode = 200;
        String bookingId = RestRequests.createBooking(addBody, expectedStatusCode).jsonPath().getString("bookingid");
        System.out.println("Информация о бронировании с id - " + bookingId);
    }

    @Test
    public void positiveTestUpdateBooking() {
        int expectedStatusCode = 200;

        String addBodyToken = "{\n\"username\":\"admin\"," +
                "\n\"password\":\"password123\"\n}";
        String token = RestRequests.getToken(addBodyToken).jsonPath().get("token");

        String addBody = "{\n" +
                "    \"firstname\": \"Sofya\",\n" +
                "    \"lastname\": \"Odegova\",\n" +
                "    \"totalprice\": 3500,\n" +
                "    \"depositpaid\": true,\n" +
                "    \"bookingdates\": {\n" +
                "        \"checkin\": \"2022-07-13\",\n" +
                "        \"checkout\": \"2022-07-23\"\n" +
                "    },\n" +
                "    \"additionalneeds\": \"Breakfast\"\n" +
                "}";
        String updatedBody = "{\n" +
                "    \"firstname\": \"Sofya\",\n" +
                "    \"lastname\": \"Odegova\",\n" +
                "    \"totalprice\": 3500,\n" +
                "    \"depositpaid\": true,\n" +
                "    \"bookingdates\": {\n" +
                "        \"checkin\": \"2022-07-13\",\n" +
                "        \"checkout\": \"2022-08-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\": \"Breakfast\"\n" +
                "}";
        String bookingId = RestRequests.createBooking(addBody, expectedStatusCode).jsonPath().getString("bookingid");
        System.out.println("Бронирование обновлено: " + RestRequests.updateBooking(token, updatedBody, bookingId, expectedStatusCode).asString());
        ;
    }

    @Test
    public void positiveTestPartialUpdateBooking() {

        int expectedStatusCode = 200;

        String addBodyToken = "{\n\"username\":\"admin\"," +
                "\n\"password\":\"password123\"\n}";
        String token = RestRequests.getToken(addBodyToken).jsonPath().get("token");

        String addBody = "{\n" +
                "    \"firstname\": \"Sofya\",\n" +
                "    \"lastname\": \"Odegova\",\n" +
                "    \"totalprice\": 3500,\n" +
                "    \"depositpaid\": true,\n" +
                "    \"bookingdates\": {\n" +
                "        \"checkin\": \"2022-07-13\",\n" +
                "        \"checkout\": \"2022-07-23\"\n" +
                "    },\n" +
                "    \"additionalneeds\": \"Breakfast\"\n" +
                "}";
        String updatedBody = "{\n" +
                "    \"totalprice\" : 5000\n" +
                "}";

        String bookingId = RestRequests.createBooking(addBody, expectedStatusCode).jsonPath().getString("bookingid");
        System.out.println("Бронирование частично обновлено: " + RestRequests.partialUpdateBooking(token, updatedBody, bookingId, expectedStatusCode).asString());
    }

    @Test
    public void positiveTestDeleteBooking() {
        int expectedStatusCode = 200;
        String addBodyToken = "{\n\"username\":\"admin\"," +
                "\n\"password\":\"password123\"\n}";
        String token = RestRequests.getToken(addBodyToken).jsonPath().get("token");
        String addBody = "{\n" +
                "    \"firstname\": \"Sofya\",\n" +
                "    \"lastname\": \"Odegova\",\n" +
                "    \"totalprice\": 3500,\n" +
                "    \"depositpaid\": true,\n" +
                "    \"bookingdates\": {\n" +
                "        \"checkin\": \"2022-07-13\",\n" +
                "        \"checkout\": \"2022-07-23\"\n" +
                "    },\n" +
                "    \"additionalneeds\": \"Breakfast\"\n" +
                "}";
        String bookingId = RestRequests.createBooking(addBody, expectedStatusCode).jsonPath().getString("bookingid");

        int statusCode = 201;

        System.out.println("Бронирование удалено. Статус: " + RestRequests.deleteBooking(token, bookingId, statusCode).statusLine());
        System.out.println("Проверка бронирования. Статус: " + RestRequests.getBooking(bookingId, 404).getStatusLine());
    }
}