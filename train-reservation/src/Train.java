/**
 * A train is made of wagons and handles all booking rules.
 */
public class Train {

    private final Wagon[] wagons;

    public Train(Wagon[] wagons) {
        if (wagons == null || wagons.length == 0) {
            throw new IllegalArgumentException("A train needs at least one wagon.");
        }
        // wagon numbers must be unique, otherwise findWagon would be ambiguous
        for (int i = 0; i < wagons.length; i++) {
            for (int j = i + 1; j < wagons.length; j++) {
                if (wagons[i].getNumber() == wagons[j].getNumber()) {
                    throw new IllegalArgumentException(
                            "Duplicate wagon number: " + wagons[i].getNumber());
                }
            }
        }
        this.wagons = wagons.clone(); // defensive copy: outside code can't change our array
    }

    /** @return the wagon with this number, or null if none exists */
    public Wagon findWagon(int wagonNumber) {
        for (Wagon wagon : wagons) {
            if (wagon.getNumber() == wagonNumber) {
                return wagon;
            }
        }
        return null;
    }

    public boolean hasReservation(Client client) {
        for (Wagon wagon : wagons) {
            if (wagon.hasClient(client)) {
                return true;
            }
        }
        return false;
    }

    /** Reserve a seat in a specific wagon. */
    public Reservation reserve(Client client, int wagonNumber) throws ReservationException {
        if (hasReservation(client)) {
            throw new ReservationException(client + " already has a seat on this train.");
        }
        Wagon wagon = findWagon(wagonNumber);
        if (wagon == null) {
            throw new ReservationException("Wagon " + wagonNumber + " does not exist.");
        }
        int seatNumber = wagon.reserveSeat(client);
        return new Reservation(client, wagonNumber, seatNumber);
    }

    /** Reserve a seat in the first wagon that still has room. */
    public Reservation reserveAnywhere(Client client) throws ReservationException {
        if (hasReservation(client)) {
            throw new ReservationException(client + " already has a seat on this train.");
        }
        for (Wagon wagon : wagons) {
            if (!wagon.isFull()) {
                int seatNumber = wagon.reserveSeat(client);
                return new Reservation(client, wagon.getNumber(), seatNumber);
            }
        }
        throw new ReservationException("The train is full.");
    }

    /** @return true if the client had a seat and it was released */
    public boolean cancel(Client client) {
        for (Wagon wagon : wagons) {
            if (wagon.cancel(client)) {
                return true;
            }
        }
        return false;
    }

    public int countFreeSeats() {
        int total = 0;
        for (Wagon wagon : wagons) {
            total += wagon.countFreeSeats();
        }
        return total;
    }

    public void printStatus() {
        for (Wagon wagon : wagons) {
            System.out.println(wagon);
        }
        System.out.println("Total free seats: " + countFreeSeats());
    }
}
