/**
 * Checked exception thrown when a reservation cannot be made
 * (unknown wagon, full wagon, or the client already has a seat).
 */
public class ReservationException extends Exception {

    public ReservationException(String message) {
        super(message);
    }
}
