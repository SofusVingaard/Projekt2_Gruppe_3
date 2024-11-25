import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class Restanse {
    private static final String FILNAVN = "src/Medlemmer.txt";
        public static void restance() throws Exception {

            Scanner scanner = new Scanner(new File(FILNAVN));
            //læse alle linjer i vores fil
            List<String> linjer = Files.readAllLines(Path.of(FILNAVN));
//nu skal jeg filtre alle de linjer som indeholder "Ikke betalt"
            List<String> restance = linjer.stream()
                    .filter(linje -> linje.contains("Ikke betalt"))
                    .collect(Collectors.toList());
            // Hvis der ikke er nogen "Ikke betalt"
            //(Jeg prøvede at bruge null for at se om listen var tom for ikke betalt, men da collect(Collectors.toList()) altid returnere en liste ville den ikke compile)
            //Så i stedet for null bruger jeg isEmpty for at tjekke om der nogen i restance
            if (restance.isEmpty()) {
                System.out.println("Fuld indtjening");
            } else {
                // Vis de ledige tider på den ønskede dato
                System.out.println("Restance på følgende brugere ");
                for (int i = 0; i < restance.size(); i++) {
                    System.out.println((i + 1) + ": " + restance.get(i));  // Vis linje nummer og indhold

                }

            }
        }
    public static void main(String[] args) throws Exception {
    restance();

    }
}
