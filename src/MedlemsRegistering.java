import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class MedlemsRegistering {

    private static final String FILNAVN = "src/Medlemmer.txt";

    public static void tilføjMedlem() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Indtast Medlemmets navn:");
        String navn = sc.nextLine();

        System.out.println("Indtast Medlemmets  alder:");
        String alder = sc.nextLine();

        System.out.println("Indtast Medlemmets type (Aktiv/Passiv):");
        String type = sc.nextLine();

        System.out.println("Indtast Medlemmets svømmekategori(Junior,Senior,Motionist,Konkurrence):");
        String svømmekategori = sc.nextLine();

        System.out.println("Har medlemmet betalt kontigent?");
        String betalt = sc.nextLine();

        String medlemdata = navn + ", " + alder + ", " + type + ", " + svømmekategori + ", " + betalt;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILNAVN, true))) {
            writer.write(medlemdata);
            writer.newLine();
            System.out.println("Medlemmet er registreret");
        } catch (IOException e) {
            System.out.println("Fejl ved skrivning til fil:");
        }
    }

    public static void visMedlemmer() {
        try {
            List<String> linjer = Files.readAllLines(Paths.get(FILNAVN));
            if (linjer.isEmpty()) {
                System.out.println("Der er ikke registeret nogen medlemmer endnu.");
                return;
            }
            System.out.println("Registerede medlemmer");
            for (String linje : linjer) {
                System.out.println(linje);
            }
        } catch (IOException e) {
            System.out.println("Fejl ved læsning af fil: " + e.getMessage());
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Vælg en mulighed:");
            System.out.println("1. Tilføj nyt medlem");
            System.out.println("2. Vis alle medlemmer");
            System.out.println("3. Afslut");

            int valg = scanner.nextInt();

            switch (valg) {
                case 1 -> tilføjMedlem();
                case 2 -> visMedlemmer();
                case 3 -> {
                    System.out.println("Afslutter programmet.");
                    return;
                }
                default -> System.out.println("Ugyldigt valg, prøv igen.");
            }
        }
    }
}