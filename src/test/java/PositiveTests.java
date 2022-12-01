import org.junit.Test;

public class PositiveTests {
    public static void main(String[] args) {
    }

    String token = RestRequests.getToken().jsonPath().get("token");
    String bookingId = RestRequests.createBookingPositive();
    String reason = RestRequests.getToken().jsonPath().get("reason");

    @Test
    public void getToken() {
        RestRequests.getToken();
        if (token != null) {
            System.out.println("Получили токен: " + token);
        } else if (reason != null) {
            System.out.println("Не получили токен. Причина: " + reason);
        }
    }

    @Test
    public void createBookingPositive() {
        RestRequests.createBookingPositive();
        System.out.println("Бронирование создано, id получен: " + bookingId);
    }

    @Test
    public void getBooking() {
        int expectedStatusCode = 200;
        RestRequests.getBooking(bookingId, expectedStatusCode);
    }

    @Test
    public void updateBooking() {
        int expectedStatusCode = 200;
        RestRequests.updateBooking(token, bookingId, expectedStatusCode);
    }

    @Test
    public void partialUpdateBooking() {
        int expectedStatusCode = 200;
        RestRequests.partialUpdateBooking(token, bookingId, expectedStatusCode);
    }

    @Test
    public void deleteBooking() {
        int expectedStatusCode = 201;
        RestRequests.deleteBooking(token, bookingId, expectedStatusCode);
    }
}
