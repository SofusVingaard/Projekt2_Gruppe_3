import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class MedlemsRegistering {

    private static final String FILNAVN = "src/Medlemmer.txt";
    private static final String NAVN= "Navn: ";
    private static final String ALDER="Alder: ";
    private static final String AKTIV="Medlemsskab: Aktiv";
    private static final String PASSIV="Medlemsskab: Passiv";
    private static final String MEDLEMSID="Medlemsnummer: ";



    public static void tilføjMedlem() {
        Scanner sc = new Scanner(System.in);
        String svømmekategori;
        String kontigent;

        int medlemsId= findMaxMedlemsId()+1;

        System.out.println("Indtast Medlemmets navn:");
        String navn = sc.nextLine();

        System.out.println("Indtast Medlemmets  alder:");
        int alder = sc.nextInt();
        sc.nextLine();
        if (alder<=17){
            svømmekategori="Junior";
        } else if (alder<=60) {
            svømmekategori="Senior";
        } else {
            svømmekategori="60+";
        }

        System.out.println("Indtast Medlemmets type (Aktiv/Passiv):");
        String type = sc.nextLine();
        if (type.equalsIgnoreCase("Aktiv")){
            type=AKTIV;
        } else {
            type=PASSIV;
        }

        System.out.println("Vil du med betale kontigent nu eller senere? Nu/Senere");
        String betalt = sc.nextLine();
        if (betalt.equalsIgnoreCase("Nu")){
            kontigent="Betalt";
        } else {
            kontigent="Ikke betalt";
        }


        String medlemdata = MEDLEMSID+medlemsId+" "+NAVN+navn + ", " +ALDER+ alder + ", " + type + ", Aldersgruppe: " + svømmekategori + ", " + kontigent;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILNAVN, true))) {
            writer.write(medlemdata);
            writer.newLine();
            System.out.println("Medlemmet er registreret med ID: " + medlemsId);
        } catch (IOException e) {
            System.out.println("Fejl ved skrivning til fil:");
        }
    }

    private static int findMaxMedlemsId() {
        int maxId = 1;
        try (BufferedReader reader = new BufferedReader(new FileReader(FILNAVN))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(MEDLEMSID)) {
                    // Extract medlemsID from the line
                    String[] parts = line.split(" ");
                    try {
                        int currentId = Integer.parseInt(parts[1]);
                        if (currentId > maxId) {
                            maxId = currentId;
                        }
                    } catch (NumberFormatException e) {
                        // If the ID is not a valid number, skip it
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Fejl ved læsning af fil:");
        }
        return maxId;
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