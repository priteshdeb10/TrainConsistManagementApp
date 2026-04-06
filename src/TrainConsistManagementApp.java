import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TrainConsistManagementApp {

    // Create a Bogie class with fields such as name and capacity
    static class Bogie {
        private String name;
        private int capacity;

        public Bogie(String name, int capacity) {
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

        // Create a List<Bogie> to store passenger bogies
        List<Bogie> bogies = new ArrayList<>();

        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 56));
        bogies.add(new Bogie("First Class", 24));

        System.out.println("Before Sorting:");
        for (Bogie b : bogies) {
            System.out.println(b);
        }

        // Use Comparator.comparingInt() to define sorting based on capacity
        bogies.sort(Comparator.comparingInt(Bogie::getCapacity));

        System.out.println("\nAfter Sorting by Capacity (Ascending):");
        for (Bogie b : bogies) {
            System.out.println(b);
        }
    }
}