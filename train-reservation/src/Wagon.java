/**
 * A wagon is made of seats (composition: seats cannot exist without a wagon).
 */
public class Wagon {

    private final int number;
    private final Seat[] seats;

    public Wagon(int number, int totalSeats) {
        if (number <= 0) {
            throw new IllegalArgumentException("Wagon number must be positive.");
        }
        if (totalSeats <= 0) {
            throw new IllegalArgumentException("A wagon needs at least one seat.");
        }
        this.number = number;
        this.seats = new Seat[totalSeats];
        for (int i = 0; i < totalSeats; i++) {
            seats[i] = new Seat(i + 1);
        }
    }

    public int getNumber() {
        return number;
    }

    public int getCapacity() {
        return seats.length;
    }

    public int countFreeSeats() {
        int count = 0;
        for (Seat seat : seats) {
            if (seat.isFree()) {
                count++;
            }
        }
        return count;
    }

    public boolean isFull() {
        return countFreeSeats() == 0;
    }

    /** True if this client already holds a seat in this wagon. */
    public boolean hasClient(Client client) {
        for (Seat seat : seats) {
            if (!seat.isFree() && seat.getOccupant().equals(client)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Reserves the first free seat.
     *
     * @return the seat number that was reserved
     * @throws ReservationException if the wagon is full
     */
    public int reserveSeat(Client client) throws ReservationException {
        for (Seat seat : seats) {
            if (seat.isFree()) {
                seat.reserve(client);
                return seat.getNumber();
            }
        }
        throw new ReservationException("Wagon " + number + " is full.");
    }

    /**
     * Cancels this client's seat in this wagon.
     *
     * @return true if a seat was released, false if the client had none here
     */
    public boolean cancel(Client client) {
        for (Seat seat : seats) {
            if (!seat.isFree() && seat.getOccupant().equals(client)) {
                seat.release();
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Wagon " + number + " (" + countFreeSeats()
                + "/" + seats.length + " free): ");
        for (Seat seat : seats) {
            sb.append(seat).append(' ');
        }
        return sb.toString().trim();
    }
}
