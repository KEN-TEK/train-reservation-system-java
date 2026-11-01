import java.util.Objects;

/**
 * A passenger. Two clients are the same person if they share the same id.
 */
public class Client {

    private final int id;
    private final String name;

    public Client(int id, String name) {
        if (id <= 0) {
            throw new IllegalArgumentException("Client id must be positive.");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Client name is required.");
        }
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client other)) return false;
        return id == other.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return name + " (#" + id + ")";
    }
}
