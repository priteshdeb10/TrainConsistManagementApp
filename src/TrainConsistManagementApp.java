import java.util.Arrays;

public class TrainConsistManagementApp {

    // Custom Exception representing business validation failure
    static class InvalidCapacityException extends Exception {
        public InvalidCapacityException(String message) {
            super(message);
        }
    }

    // Custom Runtime Exception for UC15
    static class CargoSafetyException extends RuntimeException {
        public CargoSafetyException(String message) {
            super(message);
        }
    }

    // Bogie class with Fail-Fast Configuration
    static class Bogie {
        private String name;
        private int capacity;

        public Bogie(String name, int capacity) throws InvalidCapacityException {
            if (capacity <= 0) {
                throw new InvalidCapacityException("Capacity must be greater than zero");
            }
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

    // GoodsBogie class
    static class GoodsBogie extends Bogie {
        private String shape;
        private String cargo;

        public GoodsBogie(String name, int capacity, String shape) throws InvalidCapacityException {
            super(name, capacity);
            this.shape = shape;
            this.cargo = "None";
        }

        public void assignCargo(String cargo) {
            if ("Rectangular".equalsIgnoreCase(this.shape) && "Petroleum".equalsIgnoreCase(cargo)) {
                throw new CargoSafetyException("Unsafe Cargo Assignment: Cannot assign Petroleum to a Rectangular bogie.");
            }
            this.cargo = cargo;
        }
        
        public String getShape() { return shape; }
        public String getCargo() { return cargo; }
    }

    // UC18: Linear Search for Bogie ID
    public static boolean linearSearch(String[] arr, String key) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(key)) {
                return true; // Match found, terminate early
            }
        }
        return false; // Not found
    }

    public static void main(String[] args) {
        System.out.println("=== Train Consist Management App ===");
        System.out.println("--- UC18: Linear Search for Bogie ID ---\n");

        String[] bogieIds = {"BG101", "BG205", "BG309", "BG412", "BG550"};
        System.out.println("Array of Bogie IDs: " + Arrays.toString(bogieIds) + "\n");

        System.out.println("[Test: testSearch_BogieFound]");
        String key1 = "BG309";
        boolean result1 = linearSearch(bogieIds, key1);
        System.out.println("Searching for: " + key1 + " -> Match found? " + result1);

        System.out.println("\n[Test: testSearch_BogieNotFound]");
        String key2 = "BG999";
        boolean result2 = linearSearch(bogieIds, key2);
        System.out.println("Searching for: " + key2 + " -> Match found? " + result2);

        System.out.println("\n[Test: testSearch_FirstElementMatch]");
        String key3 = "BG101";
        boolean result3 = linearSearch(bogieIds, key3);
        System.out.println("Searching for: " + key3 + " -> Match found? " + result3);

        System.out.println("\n[Test: testSearch_LastElementMatch]");
        String key4 = "BG550";
        boolean result4 = linearSearch(bogieIds, key4);
        System.out.println("Searching for: " + key4 + " -> Match found? " + result4);

        System.out.println("\n[Test: testSearch_SingleElementArray]");
        String[] singleBogie = {"BG101"};
        String key5 = "BG101";
        boolean result5 = linearSearch(singleBogie, key5);
        System.out.println("Searching for: " + key5 + " in " + Arrays.toString(singleBogie) + " -> Match found? " + result5);
    }
}