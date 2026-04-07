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

    // GoodsBogie for UC15
    static class GoodsBogie extends Bogie {
        private String shape;  // "Rectangular" or "Cylindrical"
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
        
        public String getShape() {
            return shape;
        }

        public String getCargo() {
            return cargo;
        }
    }

    // UC19: Binary Search for String IDs using low/high/mid
    public static int binarySearch(String[] arr, String key) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int cmp = arr[mid].compareTo(key);

            if (cmp < 0) {
                low = mid + 1; // Key is on the right half
            } else if (cmp > 0) {
                high = mid - 1; // Key is on the left half
            } else {
                return mid; // Key found
            }
        }
        return -1; // Not found
    }

    public static void main(String[] args) {
        System.out.println("=== Train Consist Management App ===");
        System.out.println("--- UC19: Find a Bogie ID (Binary Search) ---\n");

        String[] preSortedBogieIds = {"AC_2Tier_101", "AC_3Tier_202", "FirstClass_001", "General_505", "Sleeper_999"};
        System.out.println("Pre-sorted Bogie IDs: " + Arrays.toString(preSortedBogieIds) + "\n");

        System.out.println("[Test: Basic Successful Search]");
        String key1 = "General_505";
        int result1 = binarySearch(preSortedBogieIds, key1);
        System.out.println("Key: " + key1 + " -> Found at index: " + result1);

        System.out.println("\n[Test: Search at Bounds (First Element)]");
        String key2 = "AC_2Tier_101";
        int result2 = binarySearch(preSortedBogieIds, key2);
        System.out.println("Key: " + key2 + " -> Found at index: " + result2);

        System.out.println("\n[Test: Search at Bounds (Last Element)]");
        String key3 = "Sleeper_999";
        int result3 = binarySearch(preSortedBogieIds, key3);
        System.out.println("Key: " + key3 + " -> Found at index: " + result3);

        System.out.println("\n[Test: Value Not Found]");
        String key4 = "Luxury_100";
        int result4 = binarySearch(preSortedBogieIds, key4);
        System.out.println("Key: " + key4 + " -> Found at index: " + result4 + " (-1 represents not found)");

        System.out.println("\n[Test: Single Element Array Search]");
        String[] singleArr = {"Cargo_001"};
        int result5 = binarySearch(singleArr, "Cargo_001");
        System.out.println("Searching Single Element 'Cargo_001' in " + Arrays.toString(singleArr) + " -> Found at index: " + result5);
    }
}