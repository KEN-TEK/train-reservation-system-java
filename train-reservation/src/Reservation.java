/**
 * Immutable receipt returned after a successful booking.
 */
public record Reservation(Client client, int wagonNumber, int seatNumber) {

    @Override
    public String toString() {
        return client + " -> wagon " + wagonNumber + ", seat " + seatNumber;
    }
}
