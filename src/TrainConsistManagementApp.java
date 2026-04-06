import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TrainConsistManagementApp {

    // Regex explicitly defining format TRN-XXXX (4 digits)
    private static final Pattern TRAIN_ID_PATTERN = Pattern.compile("^TRN-\\d{4}$");
    
    // Regex explicitly defining format PET-XX (2 uppercase letters)
    private static final Pattern CARGO_CODE_PATTERN = Pattern.compile("^PET-[A-Z]{2}$");

    public static boolean validateTrainId(String trainId) {
        if (trainId == null) return false;
        Matcher matcher = TRAIN_ID_PATTERN.matcher(trainId);
        return matcher.matches();
    }

    public static boolean validateCargoCode(String cargoCode) {
        if (cargoCode == null) return false;
        Matcher matcher = CARGO_CODE_PATTERN.matcher(cargoCode);
        return matcher.matches();
    }

    public static void main(String[] args) {
        System.out.println("=== Train Consist Management App ===");
        System.out.println("--- Validation Execution ---");

        System.out.println("\n[1] Train ID Validation Testing");
        List<String> trainIds = Arrays.asList(
                "TRN-1234",   // Valid
                "TRAIN12",    // Invalid: Wrong prefix
                "TRN12A",     // Invalid: Missing dash and letters present
                "1234-TRN",   // Invalid: Reverse order
                "TRN-123",    // Invalid: Too few digits
                "TRN-12345",  // Invalid: Too many digits
                ""            // Invalid: Empty string
        );
        
        for (String id : trainIds) {
            System.out.println(String.format(" %-12s -> Valid? %b", "'" + id + "'", validateTrainId(id)));
        }

        System.out.println("\n[2] Cargo Code Validation Testing");
        List<String> cargoCodes = Arrays.asList(
                "PET-AB",     // Valid
                "PET-ab",     // Invalid: Lowercase
                "PET123",     // Invalid: Digits
                "AB-PET",     // Invalid: Reverse
                "PET-A",      // Invalid: Too few characters
                "PET-ABC",    // Invalid: Too many characters
                ""            // Invalid: Empty string
        );
        
        for (String code : cargoCodes) {
            System.out.println(String.format(" %-12s -> Valid? %b", "'" + code + "'", validateCargoCode(code)));
        }
    }
}