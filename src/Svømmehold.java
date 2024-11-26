import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Svømmehold {

    private static final String FILNAVN = "srcMedlemmer.txt";
    private static final String ALDER = "Alder: ";

    public static void main(String[] args) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(FILNAVN));

            for (String line : lines) {
                if (line.startsWith(ALDER)) {

                    int alder = Integer.parseInt(line.replace(ALDER, "").trim());
                }
                Scanner keyboard = new Scanner(System.in);
                System.out.println("Indtast din alder");
                int alder = keyboard.nextInt();

                if (alder >= 1 && alder <= 17) {
                    System.out.println("Du er på junior holdet");
                } else if (alder >= 18 && alder <= 59) {
                    System.out.println("Du er på senior holdet");
                } else if (alder >= 60) {
                } else {
                    System.out.println("Ugyldig alder. Skriv igen");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}