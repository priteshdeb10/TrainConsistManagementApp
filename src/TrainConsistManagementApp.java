import java.util.LinkedList;

public class TrainConsistManagementApp {
    public static void main(String[] args) {
        System.out.println("=== Train Consist Management App ===");

        // Create a LinkedList<String> for the consist
        LinkedList<String> consist = new LinkedList<>();

        // Add bogies: Engine, Sleeper, AC, Cargo, Guard
        consist.add("Engine");
        consist.add("Sleeper");
        consist.add("AC");
        consist.add("Cargo");
        consist.add("Guard");

        System.out.println("Initial set of Bogies attached: " + consist);

        // Insert a Pantry Car at position 2
        consist.add(2, "Pantry Car");
        System.out.println("Inserted Pantry Car at index 2: " + consist);

        // Remove the first and last bogie
        consist.removeFirst();
        consist.removeLast();
        System.out.println("Removed First (Engine) and Last (Guard) bogies...");

        // Display the final ordered train consist
        System.out.println("Final Ordered Train Consist: " + consist);
    }
}