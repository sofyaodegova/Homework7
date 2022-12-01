import org.junit.Test;

public class NegativeTests {
    public static void main(String[] args) {
    }

    String token = RestRequests.getToken().jsonPath().get("token");
    String reason = RestRequests.getToken().jsonPath().get("reason");
    String bookingId = "psd45";

    @Test
    public void getTokenNegative() {
        RestRequests.getToken();
        if (reason != null) {
            System.out.println("Не получили токен. Причина: " + reason);
        } else if (token != null) {
            System.out.println("Получили токен: " + token);
        }
    }

    @Test
    public void createBookingNegative() {
        RestRequests.createBookingNegative();
    }

    @Test
    public void getBookingNegative() {
        int expectedStatusCode = 404;
        RestRequests.getBooking(bookingId, expectedStatusCode);
    }

    @Test
    public void deleteBookingNegative() {
        int expectedStatusCode = 405;
        RestRequests.deleteBooking(token, bookingId, expectedStatusCode);
    }
}
