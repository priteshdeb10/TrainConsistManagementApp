import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

        // Create a List<Bogie> to store passenger bogies (simulating UC7 creation)
        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 56));
        bogies.add(new Bogie("Second Class", 90));
        bogies.add(new Bogie("First Class", 24));

        System.out.println("Original Bogie List:");
        bogies.forEach(System.out::println);

        System.out.println("\n--- Filtering Logic Execution ---");
        
        // Apply filter(b -> b.capacity > 60)
        int threshold = 60;
        List<Bogie> highCapacityBogies = bogies.stream()
                .filter(b -> b.getCapacity() > threshold)
                .collect(Collectors.toList());

        System.out.println("\nBogies with capacity > " + threshold + ":");
        highCapacityBogies.forEach(System.out::println);

        System.out.println("\n--- Original Collection Integrity ---");
        System.out.println("Original List Size: " + bogies.size() + " (Unchanged)");
    }
}