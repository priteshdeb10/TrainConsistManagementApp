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
        
        public String getShape() { return shape; }
        public String getCargo() { return cargo; }
    }

    // UC20: Exception Handling During Search
    public static int searchBogie(String[] arr, String key) {
        // Defensive State Validation
        if (arr == null || arr.length == 0) {
            throw new IllegalStateException("Search operation failed: The bogie collection is empty.");
        }

        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int cmp = arr[mid].compareTo(key);

            if (cmp < 0) {
                low = mid + 1; 
            } else if (cmp > 0) {
                high = mid - 1; 
            } else {
                return mid; // Key found
            }
        }
        return -1; // Not found
    }

    public static void main(String[] args) {
        System.out.println("=== Train Consist Management App ===");
        System.out.println("--- UC20: Exception Handling During Search Operations ---\n");

        System.out.println("[Test: testSearch_ThrowsExceptionWhenEmpty]");
        String[] emptyArr = {};
        try {
            searchBogie(emptyArr, "BG101");
            System.out.println("FAIL: Search incorrectly executed on an empty array!");
        } catch (IllegalStateException e) {
            System.out.println("Success! Validation Caught Exception -> " + e.getMessage());
        }

        System.out.println("\n[Test: testSearch_AllowsSearchWhenDataExists]");
        String[] validArr = {"BG101", "BG205", "BG309", "BG412"};
        try {
            searchBogie(validArr, "BG101");
            System.out.println("Success! Allowed execution without exception on populated array.");
        } catch (IllegalStateException e) {
            System.out.println("FAIL: Should not throw exception on populated data.");
        }

        System.out.println("\n[Test: testSearch_BogieFoundAfterValidation]");
        int result1 = searchBogie(validArr, "BG205");
        System.out.println("Searching for 'BG205' -> Match found? " + (result1 >= 0));

        System.out.println("\n[Test: testSearch_BogieNotFoundAfterValidation]");
        int result2 = searchBogie(validArr, "BG999");
        System.out.println("Searching for 'BG999' -> Match found? " + (result2 >= 0));

        System.out.println("\n[Test: testSearch_SingleElementValidCase]");
        String[] singleArr = {"BG101"};
        int result3 = searchBogie(singleArr, "BG101");
        System.out.println("Searching for 'BG101' in " + Arrays.toString(singleArr) + " -> Match found? " + (result3 >= 0));
    }
}