import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;


public class Restance {
    private static final String FILNAVN = "src/Medlemmer.txt";
        public static void restance() throws Exception {
            //læse alle linjer i vores fil
            List<String> linjer = Files.readAllLines(Path.of(FILNAVN));
//nu skal jeg filtre alle de linjer som indeholder "Ikke betalt"
            List<String> restance = linjer.stream()
                    .filter(linje -> linje.contains("Ikke betalt"))
                    .collect(Collectors.toList());
            // Hvis der ikke er nogen "Ikke betalt"
            if (restance.isEmpty()) {
                System.out.println("Fuld indtjening");
            } else {
                System.out.println("Der er restance: " + restance);
            }
        }
    public static void main(String[] args) throws Exception {
    restance();

    }
}
