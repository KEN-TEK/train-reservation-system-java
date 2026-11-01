/**
 * Demo of the train reservation system, including the failure cases.
 */
public class Main {

    public static void main(String[] args) {
        Train train = new Train(new Wagon[]{
                new Wagon(1, 2),   // small wagons so "full" is easy to demonstrate
                new Wagon(2, 3)
        });

        Client kenedy = new Client(2026, "Kenedy");
        Client cyrus = new Client(2027, "Cyrus");
        Client tracy = new Client(2028, "Tracy");
        Client john = new Client(2029, "John");
        Client mary = new Client(2030, "Mary");
        Client late = new Client(2031, "Latecomer");

        System.out.println("=== Empty train ===");
        train.printStatus();

        System.out.println("\n=== Bookings ===");
        attempt(train, kenedy, 1);      // ok
        attempt(train, cyrus, 1);       // ok, wagon 1 now full
        attempt(train, tracy, 1);       // fails: wagon full
        attempt(train, john, 9);        // fails: no such wagon
        attempt(train, kenedy, 2);      // fails: Kenedy already booked

        System.out.println("\n=== Book in any wagon ===");
        attemptAnywhere(train, tracy);  // goes to wagon 2
        attemptAnywhere(train, john);
        attemptAnywhere(train, mary);   // wagon 2 now full too
        attemptAnywhere(train, late);   // fails: train full

        System.out.println("\n=== Status ===");
        train.printStatus();

        System.out.println("\n=== Cancellation ===");
        System.out.println("Cancel Cyrus: " + train.cancel(cyrus));
        System.out.println("Cancel Cyrus again: " + train.cancel(cyrus));
        attemptAnywhere(train, late);   // now succeeds, freed seat in wagon 1

        System.out.println("\n=== Final status ===");
        train.printStatus();
    }

    private static void attempt(Train train, Client client, int wagonNumber) {
        try {
            Reservation r = train.reserve(client, wagonNumber);
            System.out.println("OK     " + r);
        } catch (ReservationException e) {
            System.out.println("FAILED " + e.getMessage());
        }
    }

    private static void attemptAnywhere(Train train, Client client) {
        try {
            Reservation r = train.reserveAnywhere(client);
            System.out.println("OK     " + r);
        } catch (ReservationException e) {
            System.out.println("FAILED " + e.getMessage());
        }
    }
}
