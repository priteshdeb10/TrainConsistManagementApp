import java.util.HashMap;
import java.util.Map;

public class TrainConsistManagementApp {
    public static void main(String[] args) {
        System.out.println("=== Train Consist Management App ===");

        // Create a HashMap<String, Integer> to store bogie capacity
        Map<String, Integer> bogieCapacityMap = new HashMap<>();

        // Insert capacity values using put() method
        bogieCapacityMap.put("Sleeper", 72);
        bogieCapacityMap.put("AC Chair", 73);
        bogieCapacityMap.put("First Class", 24);

        System.out.println("Bogie Capacities Loaded...");
        System.out.println("-----------------------------------");

        // Iterate over the map using entrySet()
        for (Map.Entry<String, Integer> entry : bogieCapacityMap.entrySet()) {
            System.out.println("Bogie: " + entry.getKey() + " | Capacity: " + entry.getValue() + " seats");
        }
    }
}