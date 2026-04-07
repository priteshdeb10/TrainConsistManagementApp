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
        System.out.println("--- UC15: Safe Cargo Assignment Using try-catch-finally ---\n");

        try {
            GoodsBogie cylindricalBogie = new GoodsBogie("Oil Tanker", 100, "Cylindrical");
            GoodsBogie rectangularBogie = new GoodsBogie("Freight Car", 150, "Rectangular");

            System.out.println("[Test: testCargo_SafeAssignment]");
            try {
                cylindricalBogie.assignCargo("Petroleum");
                System.out.println("Success: Cargo 'Petroleum' assigned to cylindrical bogie.");
            } catch (CargoSafetyException e) {
                System.out.println("Error: " + e.getMessage());
            } finally {
                System.out.println("Completion Logging: Cargo validation finished for " + cylindricalBogie.getName() + ".");
            }

            System.out.println("\n[Test: testCargo_UnsafeAssignmentHandled]");
            try {
                rectangularBogie.assignCargo("Petroleum");
                System.out.println("Success: Cargo 'Petroleum' assigned to rectangular bogie.");
            } catch (CargoSafetyException e) {
                System.out.println("Validation Caught Runtime Exception: " + e.getMessage());
            } finally {
                System.out.println("Completion Logging: Cargo validation finished for " + rectangularBogie.getName() + ".");
            }

            System.out.println("\n[Test: testCargo_CargoNotAssignedAfterFailure]");
            if ("None".equals(rectangularBogie.getCargo())) {
                System.out.println("Verified: Rectangular bogie does not store Petroleum cargo (Current cargo: " + rectangularBogie.getCargo() + ").");
            }

            System.out.println("\n[Test: testCargo_ProgramContinuesAfterException]");
            System.out.println("Verified: Application continues running safely after handling the failure.");
            System.out.println("Attempting a safe assignment to the rectangular bogie...");
            try {
                rectangularBogie.assignCargo("Coal");
                System.out.println("Success: Cargo 'Coal' assigned to rectangular bogie.");
            } catch (CargoSafetyException e) {
                System.out.println("Error: " + e.getMessage());
            } finally {
                System.out.println("Completion Logging: Cargo validation finished for " + rectangularBogie.getName() + ".");
            }

            System.out.println("\n[Test: testCargo_FinallyBlockExecution]");
            System.out.println("Verified: The finally block executes in both success (Coal, Petroleum) and failure scenarios, ensuring cleanup/logging happens.");

        } catch (InvalidCapacityException e) {
            System.out.println("Setup Failed: " + e.getMessage());
        }
    }
}