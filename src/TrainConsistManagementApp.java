import java.util.HashSet;
import java.util.Set;

public class TrainConsistManagementApp {
    public static void main(String[] args) {
        System.out.println("=== Train Consist Management App ===");

        // Create a HashSet<String> for bogie IDs
        Set<String> bogieIds = new HashSet<>();

        // Add duplicate values intentionally
        System.out.println("Adding Bogie: BG101");
        bogieIds.add("BG101");

        System.out.println("Adding Bogie: BG102");
        bogieIds.add("BG102");

        System.out.println("Adding Bogie: BG101 (Duplicate)");
        bogieIds.add("BG101");

        System.out.println("Adding Bogie: BG103");
        bogieIds.add("BG103");

        // Print the final set - duplicates are removed automatically
        System.out.println("Final Unique Bogie IDs: " + bogieIds);
    }
}