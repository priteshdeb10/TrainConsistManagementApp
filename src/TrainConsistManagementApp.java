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

    // Bubble sort algorithm implementation for UC16
    public static void bubbleSort(int[] capacities) {
        int n = capacities.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                // Swap values when left element is greater than right element
                if (capacities[j] > capacities[j + 1]) {
                    int temp = capacities[j];
                    capacities[j] = capacities[j + 1];
                    capacities[j + 1] = temp;
                }
            }
        }
    }

    public static void printArray(int[] array) {
        System.out.print("{");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i < array.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.print("}");
    }

    public static void main(String[] args) {
        System.out.println("=== Train Consist Management App ===");
        System.out.println("--- UC16: Sort Passenger Bogies by Capacity (Bubble Sort) ---\n");

        System.out.println("[Test: testSort_BasicSorting]");
        int[] arr1 = {72, 56, 24, 70, 60};
        System.out.print("Original: ");
        printArray(arr1);
        bubbleSort(arr1);
        System.out.print("\nSorted:   ");
        printArray(arr1);
        System.out.println("\n");

        System.out.println("[Test: testSort_AlreadySortedArray]");
        int[] arr2 = {24, 56, 60, 70, 72};
        System.out.print("Original: ");
        printArray(arr2);
        bubbleSort(arr2);
        System.out.print("\nSorted:   ");
        printArray(arr2);
        System.out.println("\n");

        System.out.println("[Test: testSort_DuplicateValues]");
        int[] arr3 = {72, 56, 56, 24};
        System.out.print("Original: ");
        printArray(arr3);
        bubbleSort(arr3);
        System.out.print("\nSorted:   ");
        printArray(arr3);
        System.out.println("\n");

        System.out.println("[Test: testSort_SingleElementArray]");
        int[] arr4 = {50};
        System.out.print("Original: ");
        printArray(arr4);
        bubbleSort(arr4);
        System.out.print("\nSorted:   ");
        printArray(arr4);
        System.out.println("\n");

        System.out.println("[Test: testSort_AllEqualValues]");
        int[] arr5 = {40, 40, 40};
        System.out.print("Original: ");
        printArray(arr5);
        bubbleSort(arr5);
        System.out.print("\nSorted:   ");
        printArray(arr5);
        System.out.println();
    }
}