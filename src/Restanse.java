import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class Restanse {
    private static final String FILNAVN = "src/Medlemmer.txt";
        public static void restance() throws Exception {
            int valg = 0;

            Scanner scanner = new Scanner(System.in);
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
                    System.out.println((i + 1) + ": " + restance.get(i));  // linje nummer og indhold
                        //vælg et medlem at ændre

                    System.out.println("Indtast nummeret på medlemmet, du vil ændre:");

                       try {
                           valg = Integer.parseInt(scanner.nextLine());
                       }
                       catch (InputMismatchException p){
                      }
                       catch (NumberFormatException x){
                           
                       }



                    // her kontroller man om valget er gyldigt
                    if (valg < 1 || valg > restance.size()) {
                        System.out.println("Ugyldigt valg. Afslutter programmet.");
                        //return;
                    }
                    // Henter den valgte linje fra de medlemmer, der er i restance
                    String mangleBetaling = restance.get(valg - 1);

                    // Erstatter "Ikke betalt" med "Betalt" i den valgte linje
                    String opdateretR = mangleBetaling.replace("Ikke betalt", "Betalt");

                    // Opdater linjen i den oprindelige liste
                    linjer.set(linjer.indexOf(mangleBetaling), opdateretR);

                    // Gem de opdaterede linjer tilbage i filen
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILNAVN))) {
                        for (String linje : linjer) {
                            writer.write(linje);
                            writer.newLine();
                        }
                    }
                    System.out.println("Status for medlemmet er opdateret fra 'Ikke betalt' til 'Betalt'.");
                }
                scanner.close();
            }

        }
    public static void main(String[] args) throws Exception {
        restance();
        }
    }

