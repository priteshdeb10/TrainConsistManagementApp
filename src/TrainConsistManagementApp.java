import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TrainConsistManagementApp {

    static class Bogie {
        private String type;
        private int capacity;

        public Bogie(String type, int capacity) {
            this.type = type;
            this.capacity = capacity;
        }

        public String getType() {
            return type;
        }

        public int getCapacity() {
            return capacity;
        }

        @Override
        public String toString() {
            return "Bogie [type=" + type + ", capacity=" + capacity + "]";
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Train Consist Management App ===");

        // Create a List of bogies with intentional duplicates to test grouping logic
        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("Sleeper", 72)); // Second sleeper
        bogies.add(new Bogie("AC Chair", 56));
        bogies.add(new Bogie("First Class", 24));
        bogies.add(new Bogie("First Class", 24)); // Second first class

        System.out.println("Original Bogie List Size: " + bogies.size());
        
        System.out.println("\n--- Executing Grouping Operation ---");
        // Apply Collectors.groupingBy() based on bogie type
        Map<String, List<Bogie>> groupedBogies = bogies.stream()
                .collect(Collectors.groupingBy(Bogie::getType));

        // Display the grouped structure clearly showing keys and lists
        System.out.println("\nGrouped Bogie Structure:");
        for (Map.Entry<String, List<Bogie>> entry : groupedBogies.entrySet()) {
            System.out.println("Category [" + entry.getKey() + "] contains " + entry.getValue().size() + " bogies:");
            entry.getValue().forEach(b -> System.out.println("   -> " + b));
        }

        // Integrity check
        System.out.println("\n--- Integrity Check ---");
        System.out.println("Collection remains unmodified? " + (bogies.size() == 5));
    }
}