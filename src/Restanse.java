import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Restanse {
    private static final String FILNAVN = "src/TekstFiler/Medlemmer.txt";

    public static void restance() throws IOException {
        Scanner scanner = new Scanner(System.in);

        // Læs alle linjer i filen
        List<String> linjer = Files.readAllLines(Path.of(FILNAVN));

        // Filtrér linjer med "Ikke betalt"
        List<String> restance = linjer.stream()
                .filter(linje -> linje.contains("Ikke betalt"))
                .collect(Collectors.toList());

        // Hvis der ikke er nogen medlemmer i restance
        if (restance.isEmpty()) {
            System.out.println("Alle medlemmer har betalt deres kontingent.");
            return;
        }

        // Vis medlemmer i restance
        System.out.println("Medlemmer i restance:");
        for (int i = 0; i < restance.size(); i++) {
            System.out.println((i + 1) + ": " + restance.get(i));
        }

        while (true) {
            // Bed brugeren om at vælge et medlem at opdatere
            System.out.println("\nIndtast nummeret på medlemmet, du vil opdatere (eller 0 for at afslutte):");
            int valg;

            try {
                valg = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ugyldigt input. Prøv igen.");
                continue;
            }

            // Hvis brugeren vælger 0, afslut
            if (valg == 0) {
                System.out.println("Afslutter opdatering.");
                break;
            }

            // Kontroller om valget er gyldigt
            if (valg < 1 || valg > restance.size()) {
                System.out.println("Ugyldigt valg. Prøv igen.");
                continue;
            }

            // Hent den valgte linje
            String mangleBetaling = restance.get(valg - 1);

            // Erstat "Ikke betalt" med "Betalt"
            String opdateretR = mangleBetaling.replace("Ikke betalt", "Betalt");

            // Opdater linjen i den originale liste
            linjer.set(linjer.indexOf(mangleBetaling), opdateretR);

            // Opdater listen over restance for visning
            restance.set(valg - 1, opdateretR);

            System.out.println("Status opdateret for medlem:\n" + opdateretR);
        }

        // Skriv de opdaterede linjer tilbage til filen
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILNAVN))) {
            for (String linje : linjer) {
                writer.write(linje);
                writer.newLine();
            }
        }

        System.out.println("Alle ændringer er gemt i tekstfilen.");
        scanner.close();
    }

    public static void main(String[] args) throws IOException {
        restance();
    }
}
