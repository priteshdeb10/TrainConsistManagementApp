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

    public static void main(String[] args) {
        System.out.println("=== Train Consist Management App ===");
        System.out.println("--- UC17: Sort Bogie Names Using Arrays.sort() ---\n");

        System.out.println("[Test: testSort_BasicAlphabeticalSorting]");
        String[] arr1 = {"Sleeper", "AC Chair", "First Class", "General", "Luxury"};
        System.out.println("Original: " + Arrays.toString(arr1));
        Arrays.sort(arr1);
        System.out.println("Sorted:   " + Arrays.toString(arr1) + "\n");

        System.out.println("[Test: testSort_UnsortedInput]");
        String[] arr2 = {"Luxury", "General", "Sleeper", "AC Chair"};
        System.out.println("Original: " + Arrays.toString(arr2));
        Arrays.sort(arr2);
        System.out.println("Sorted:   " + Arrays.toString(arr2) + "\n");

        System.out.println("[Test: testSort_AlreadySortedArray]");
        String[] arr3 = {"AC Chair", "First Class", "General"};
        System.out.println("Original: " + Arrays.toString(arr3));
        Arrays.sort(arr3);
        System.out.println("Sorted:   " + Arrays.toString(arr3) + "\n");

        System.out.println("[Test: testSort_DuplicateBogieNames]");
        String[] arr4 = {"Sleeper", "AC Chair", "Sleeper", "General"};
        System.out.println("Original: " + Arrays.toString(arr4));
        Arrays.sort(arr4);
        System.out.println("Sorted:   " + Arrays.toString(arr4) + "\n");

        System.out.println("[Test: testSort_SingleElementArray]");
        String[] arr5 = {"Sleeper"};
        System.out.println("Original: " + Arrays.toString(arr5));
        Arrays.sort(arr5);
        System.out.println("Sorted:   " + Arrays.toString(arr5) + "\n");
    }
}