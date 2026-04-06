public class TrainConsistManagementApp {

    // Custom Exception representing business validation failure
    static class InvalidCapacityException extends Exception {
        public InvalidCapacityException(String message) {
            super(message);
        }
    }

    // Bogie class with Fail-Fast Configuration
    static class Bogie {
        private String name;
        private int capacity;

        public Bogie(String name, int capacity) throws InvalidCapacityException {
            // Enforcement of Railway Rules directly in constructor
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

    public static void main(String[] args) {
        System.out.println("=== Train Consist Management App ===");
        System.out.println("--- UC14: Handling Invalid Bogie Capacity ---\n");

        System.out.println("[Test 1: Valid Capacity Execution]");
        try {
            Bogie validBogie = new Bogie("Sleeper", 72);
            System.out.println("Success! Created -> " + validBogie);
        } catch (InvalidCapacityException e) {
            System.out.println("FAILED: Validation Caught Exception: " + e.getMessage());
        }

        System.out.println("\n[Test 2: Negative Capacity Evaluation]");
        try {
            System.out.println("Attempting to create AC Chair with -10 seating...");
            new Bogie("AC Chair", -10);
            System.out.println("FAIL: System incorrectly allowed creation!");
        } catch (InvalidCapacityException e) {
            System.out.println("Success! Validation actively Caught Exception -> " + e.getMessage());
        }

        System.out.println("\n[Test 3: Zero Capacity Evaluation]");
        try {
            System.out.println("Attempting to create First Class with 0 seating...");
            new Bogie("First Class", 0);
            System.out.println("FAIL: System incorrectly allowed creation!");
        } catch (InvalidCapacityException e) {
            System.out.println("Success! Validation actively Caught Exception -> " + e.getMessage());
        }
    }
}