import java.util.LinkedHashSet;
import java.util.Set;

public class TrainConsistManagementApp {
    public static void main(String[] args) {
        System.out.println("=== Train Consist Management App ===");

        // Create a LinkedHashSet<String> to represent the train formation
        Set<String> formation = new LinkedHashSet<>();

        // Attach bogies
        System.out.println("Attaching Engine...");
        formation.add("Engine");
        
        System.out.println("Attaching Sleeper...");
        formation.add("Sleeper");
        
        System.out.println("Attaching Cargo...");
        formation.add("Cargo");
        
        System.out.println("Attaching Guard...");
        formation.add("Guard");

        // Attempt to attach a duplicate bogie intentionally
        System.out.println("Attaching Sleeper (Duplicate)...");
        formation.add("Sleeper");

        // Display the final formation order
        System.out.println("Final Train Formation (Ordered & Unique): " + formation);
    }
}