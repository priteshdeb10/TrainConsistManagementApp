import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TrainConsistManagementApp {

    static class GoodsBogie {
        private String type;
        private String cargo;

        public GoodsBogie(String type, String cargo) {
            this.type = type;
            this.cargo = cargo;
        }

        public String getType() {
            return type;
        }

        public String getCargo() {
            return cargo;
        }

        @Override
        public String toString() {
            return "GoodsBogie [type=" + type + ", cargo=" + cargo + "]";
        }
    }

    // Encapsulated safety compliance logic using Streams API and allMatch()
    public static boolean checkSafetyCompliance(List<GoodsBogie> bogies) {
        return bogies.stream()
                .allMatch(b -> !b.getType().equalsIgnoreCase("Cylindrical") 
                            || b.getCargo().equalsIgnoreCase("Petroleum"));
    }

    public static void main(String[] args) {
        System.out.println("=== Train Consist Management App ===");
        System.out.println("--- UC12: Safety Compliance Execution ---\n");

        System.out.println("[Test 1: All Valid Bogies]");
        List<GoodsBogie> safeTrain = Arrays.asList(
                new GoodsBogie("Cylindrical", "Petroleum"),
                new GoodsBogie("Box", "Coal"),
                new GoodsBogie("Open", "Grain")
        );
        safeTrain.forEach(System.out::println);
        System.out.println("-> Safety Compliant? " + checkSafetyCompliance(safeTrain) + "\n");

        System.out.println("[Test 2: Invalid Cylindrical Cargo]");
        List<GoodsBogie> unsafeTrain = Arrays.asList(
                new GoodsBogie("Cylindrical", "Coal"), // Rule violation!
                new GoodsBogie("Box", "Grain")
        );
        unsafeTrain.forEach(System.out::println);
        System.out.println("-> Safety Compliant? " + checkSafetyCompliance(unsafeTrain) + "\n");
        
        System.out.println("[Test 3: Non-Cylindrical with Flexible Cargo]");
        List<GoodsBogie> flexibleTrain = Arrays.asList(
                new GoodsBogie("Box", "Petroleum"),    // Non-cylindrical can carry other loads
                new GoodsBogie("Open", "Coal")
        );
        flexibleTrain.forEach(System.out::println);
        System.out.println("-> Safety Compliant? " + checkSafetyCompliance(flexibleTrain) + "\n");

        System.out.println("[Test 4: Empty Train Edge Case]");
        List<GoodsBogie> emptyTrain = Collections.emptyList();
        System.out.println("-> Empty Train Data Safety Compliant? " + checkSafetyCompliance(emptyTrain) + "\n");
    }
}