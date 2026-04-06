import java.util.ArrayList;
import java.util.List;

public class TrainConsistManagementApp {

    static class Bogie {
        private String name;
        private int capacity;

        public Bogie(String name, int capacity) {
            this.name = name;
            this.capacity = capacity;
        }

        public String getName() {
            return name;
        }

        public int getCapacity() {
            return capacity;
        }

        @Override
        public String toString() {
            return "Bogie [name=" + name + ", capacity=" + capacity + "]";
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Train Consist Management App ===");

        // Create a List of bogies
        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 56));
        bogies.add(new Bogie("First Class", 24));
        bogies.add(new Bogie("Second Class", 90));

        System.out.println("Original Bogie List:");
        bogies.forEach(System.out::println);

        System.out.println("\n--- Calculating Total Seating Capacity ---");
        
        // 1. Transform each bogie to numeric capacity via map()
        // 2. Aggregate capacities into single sum via reduce()
        int totalSeats = bogies.stream()
                .map(Bogie::getCapacity)
                .reduce(0, Integer::sum);

        System.out.println("Total Seating Capacity: " + totalSeats + " seats");

        System.out.println("\n--- Edge Case Testing (Based on UC Specs) ---");
        // Test case verification: empty collection handling
        List<Bogie> emptyBogies = new ArrayList<>();
        int emptyTotal = emptyBogies.stream().map(Bogie::getCapacity).reduce(0, Integer::sum);
        System.out.println("Empty List Capacity (Expected: 0): " + emptyTotal);

        // Integrity verification
        System.out.println("Original List Unchanged Check (Expected Size=4): " + bogies.size());
    }
}