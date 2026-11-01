/**
 * A single seat. It is either free or held by exactly one client.
 */
public class Seat {

    private final int number;
    private Client occupant; // null means the seat is free

    public Seat(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException("Seat number must be positive.");
        }
        this.number = number;
    }

    public boolean isFree() {
        return occupant == null;
    }

    public void reserve(Client client) {
        if (client == null) {
            throw new IllegalArgumentException("Client is required.");
        }
        if (!isFree()) {
            throw new IllegalStateException("Seat " + number + " is already taken.");
        }
        occupant = client;
    }

    public void release() {
        occupant = null;
    }

    public int getNumber() {
        return number;
    }

    public Client getOccupant() {
        return occupant;
    }

    @Override
    public String toString() {
        return isFree() ? "[" + number + ": free]" : "[" + number + ": " + occupant.getName() + "]";
    }
}
