import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TrainConsistManagementApp {

    static class Bogie {
        private String name;
        private int capacity;

        public Bogie(String name, int capacity) {
            this.name = name;
            this.capacity = capacity;
        }

        public int getCapacity() {
            return capacity;
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Train Consist Management App ===");
        System.out.println("--- UC13: Performance Comparison (Loops vs Streams) ---\n");

        System.out.println("Generating Large Dataset (1,000,000 Bogies)...");
        List<Bogie> dataset = new ArrayList<>();
        // Mix of capacities to provide a realistic filtering load
        for (int i = 0; i < 1_000_000; i++) {
            dataset.add(new Bogie("Passenger Bogie", (i % 2 == 0) ? 72 : 50));
        }
        System.out.println("Dataset ready.\n");

        // --- LOOP-BASED FILTERING ---
        System.out.println("[1] Initiating Loop-Based Filtering...");
        long loopStartTime = System.nanoTime();

        List<Bogie> loopFilteredBogies = new ArrayList<>();
        for (Bogie bogie : dataset) {
            if (bogie.getCapacity() > 60) {
                loopFilteredBogies.add(bogie);
            }
        }

        long loopEndTime = System.nanoTime();
        long loopDurationNs = loopEndTime - loopStartTime;
        System.out.println("Loop Filtered Size: " + loopFilteredBogies.size());
        System.out.println("Loop Execution Time: " + loopDurationNs + " ns (" + (loopDurationNs / 1_000_000.0) + " ms)\n");

        // --- STREAM-BASED FILTERING ---
        System.out.println("[2] Initiating Stream-Based Filtering...");
        long streamStartTime = System.nanoTime();

        List<Bogie> streamFilteredBogies = dataset.stream()
                .filter(b -> b.getCapacity() > 60)
                .collect(Collectors.toList());

        long streamEndTime = System.nanoTime();
        long streamDurationNs = streamEndTime - streamStartTime;
        System.out.println("Stream Filtered Size: " + streamFilteredBogies.size());
        System.out.println("Stream Execution Time: " + streamDurationNs + " ns (" + (streamDurationNs / 1_000_000.0) + " ms)\n");

        // --- VALIDATION AND RESULT CHECKS ---
        System.out.println("[3] Validation Checks");
        boolean sizesMatch = (loopFilteredBogies.size() == streamFilteredBogies.size());
        System.out.println("Match Confirmed? " + sizesMatch);
        System.out.println("Are timestamps logically positive? " + (loopDurationNs > 0 && streamDurationNs > 0));
        System.out.println("Filtering excludes items <= 60? " + (loopFilteredBogies.size() < dataset.size()));
    }
}