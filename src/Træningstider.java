import java.io.*;
import java.nio.file.*;
import java.text.*;
import java.util.*;

public class Træningstider {
    static final String MEDLEMMER_FIL = "src/Medlemmer.txt";
    static final String TRÆNING_FIL = "src/Træningstider.txt";


    public static String findMedlemNavn(int medlemsId) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(MEDLEMMER_FIL));
            String line;
            while ((line = reader.readLine()) != null) {
                // Tjekker om der er medlemsid
                if (line.contains("Medlemsnummer: " + medlemsId)) {
                    // Find navnet i linjen
                    String[] parts = line.split("Navn: ");
                    if (parts.length > 1) {
                        String navn = parts[1].split(",")[0].trim();  // henter navnet fra medlemmer.txt
                        return navn;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Fejl ved læsning af medlemsfil.");
        }
        return null;
    }

    // gør så at man ikke kan skrive en dato ind der ikke findes
    private static boolean erGyldigDato(String dato) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.setLenient(false);  // Gør dato-validering streng
        try {
            sdf.parse(dato);
            return true;
        } catch (ParseException e) {
            return false;  // Ugyldig dato
        }
    }


    public static void opretTræningstid() {
        Scanner sc = new Scanner(System.in);

        // Træningstid for eksisterende medlem
        System.out.println("Indtast medlemsID:");
        int medlemsId = sc.nextInt();
        sc.nextLine(); // Fjern newline karakteren efter nextInt

        // Hent medlem ved medlemsID
        String medlemNavn = findMedlemNavn(medlemsId);
        if (medlemNavn == null) {
            System.out.println("MedlemsID ikke fundet.");
            return;
        }


        String dato;
        while (true) {
            System.out.println("Indtast træningens dato (format: dd-mm-yyyy):");
            dato = sc.nextLine();

            // Tjek om datoen er gyldig
            if (erGyldigDato(dato)) {
                break;
            } else {
                System.out.println("Ugyldig dato, prøv igen.");
            }
        }

        System.out.println("Indtast disciplin (f.eks. Crawl, Ryg, Bryst, Butterfly):");
        String disciplin = sc.nextLine();

        // formateringen på tiden
        System.out.println("Indtast træningstid (minut.sekund):");
        String træningstidInput = sc.nextLine();

        // sørger for at formatet er rigtigt
        double træningstid;
        try {
            træningstid = Double.parseDouble(træningstidInput);
            if (træningstid <= 0) {
                System.out.println("Tiden skal være et positivt tal.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Ugyldigt format for tid. Brug formatet Minut.Sekund (f.eks. 2.30).");
            return;
        }

        // Gemmer træningstider til filen
        String træningData = "MedlemsID: " + medlemsId + ", Navn: " + medlemNavn + ", Dato: " + dato + ", Disciplin: " + disciplin + ", Træningstid: " + træningstidInput;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TRÆNING_FIL, true))) {
            writer.write(træningData);
            writer.newLine();
            System.out.println("Træningstid for " + medlemNavn + " er oprettet.");
        } catch (IOException e) {
            System.out.println("Fejl ved skrivning til træningstidfil.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Vælg en mulighed:");
            System.out.println("1. Opret træningstid");
            System.out.println("2. Afslut");

            int valg = scanner.nextInt();
            scanner.nextLine();

            switch (valg) {
                case 1:
                    opretTræningstid();
                    break;
                case 2:
                    System.out.println("Afslutter programmet.");
                    return;
                default:
                    System.out.println("Ugyldigt valg, prøv igen.");
            }
        }
    }
}