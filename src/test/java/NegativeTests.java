import org.junit.Test;

public class NegativeTests {
    public static void main(String[] args) {
    }

    @Test
    public void negativeTestGetToken() {
        String addBody = "{\n\"username\":\"addmin\"," +
                "\n\"password\":\"password1263\"\n}";
        String reason = RestRequests.getToken(addBody).jsonPath().get("reason");

        System.out.println("Не получили токен. Причина: " + reason);
    }

    @Test
    public void negativeTestCreateBooking() {
        String addBody = "{\n" +
                "    \"firstnammmmme\": \"Sofya\",\n" +
                "    \"lastname\": \"Odegova\",\n" +
                "    \"totalprice\": 3500,\n" +
                "    \"depositpaid\": true,\n" +
                "    \"bookingdates\": {\n" +
                "        \"checkin\": \"2022-07-13\",\n" +
                "        \"checkout\": \"2022-07-23\"\n" +
                "    },\n" +
                "    \"additionalneeds\": \"Breakfast\"\n" +
                "}";
        int expectedStatusCode = 500;
        System.out.println("Бронирование не было создано, id не получен. Ошибка: " + RestRequests.createBooking(addBody, expectedStatusCode).statusLine());
    }

    @Test
    public void negativeTestGetBooking() {
        String bookingId = "psd45";
        int expectedStatusCode = 404;
        System.out.println("Ошибка: " + RestRequests.getBooking(bookingId, expectedStatusCode).getStatusLine());
    }

    @Test
    public void negativeTestDeleteBooking() {
        String addBodyToken = "{\n\"username\":\"admin\"," +
                "\n\"password\":\"password123\"\n}";
        String token = RestRequests.getToken(addBodyToken).jsonPath().get("token");
        String bookingId = "psd45";
        int statusCode = 405;
        System.out.println("Ошибка удаления. Статус: " + RestRequests.deleteBooking(token, bookingId, statusCode).statusLine());
    }
}