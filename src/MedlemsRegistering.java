import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MedlemsRegistering {
    //Klasse bibliotek
    private static final String FILNAVN = "src/Medlemmer.txt";
    private static final String KONTIGENT= "src/KontigentPriser.txt";
    private static final String NAVN= "Navn: ";
    private static final String ALDER="Alder: ";
    private static final String AKTIV="Medlemsskab: Aktiv";
    private static final String PASSIV="Medlemsskab: Passiv";
    private static final String MEDLEMSID="Medlemsnummer: ";
     static final String KONKURRENCE="src/Konkurrence stævner";
    private static final String BRYST= "Bryst";
    private static final String CRAWL="Crawl";
    private static final String BUTTERFLY= "Butterfly";
    private static final String RYG= "Ryg";


    // Metode til at tilføje medlemmer
    public static void tilføjMedlem() {
        Scanner sc = new Scanner(System.in);
        String svømmekategori;
        String kontigent;
        double pris = 0;

        int medlemsId= findMaxMedlemsId()+1;
        //Indtast medlemmets information
        System.out.println("Indtast Medlemmets navn:");
        String navn = sc.nextLine();

        System.out.println("Indtast Medlemmets  alder:");
        int alder = sc.nextInt();
        sc.nextLine();
        if (alder<=17){
            svømmekategori="Junior";
            // De her linjer bruges til at ændre vores kontigent pris i programmet fra et tekst dokument
            // så det er nemmere for svømmeklubben at ændre prisen uden at skulle ændre src kode
            try (BufferedReader reader = new BufferedReader(new FileReader(KONTIGENT))) {
                String line;
                while ((line = reader.readLine()) != null) {
                        // Søger efter en linje hvor Junior indgår
                    if (line.startsWith("Junior")) {
                        // Extract medlemsID from the line
                        // Hver bid skilles ved mellemrum
                        String[] parts = line.split(" ");
                        try {
                            //Tager bid nummer 2 i linjen og gør "int pris" til den værdi
                            int kontigentPris = Integer.parseInt(parts[1]);
                            pris=kontigentPris;
                        } catch (NumberFormatException e) {
                        }
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (alder<=60) {
            svømmekategori="Senior";
                // læs kommentar fra junior
            try (BufferedReader reader = new BufferedReader(new FileReader(KONTIGENT))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("Senior")) {
                        String[] parts = line.split(" ");
                        try {
                            int kontigentPris = Integer.parseInt(parts[1]);
                            pris=kontigentPris;
                        } catch (NumberFormatException e) {
                        }
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            svømmekategori="60+";
                // læs kommentar fra junior
            try (BufferedReader reader = new BufferedReader(new FileReader(KONTIGENT))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("Senior")) {

                        String[] parts = line.split(" ");
                        try {
                            int kontigentPris = Integer.parseInt(parts[1]);
                            pris=kontigentPris*0.75;
                        } catch (NumberFormatException e) {
                        }
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("Indtast Medlemmets type (Aktiv/Passiv):");
        String type = sc.nextLine();
        if (type.equalsIgnoreCase("Aktiv")){
            type=AKTIV;
        } else {
            type=PASSIV;
            // læs kommentar fra junior
            try (BufferedReader reader = new BufferedReader(new FileReader(KONTIGENT))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("Passiv")) {
                        String[] parts = line.split(" ");
                        try {
                            int kontigentPris = Integer.parseInt(parts[1]);
                            pris=kontigentPris;
                        } catch (NumberFormatException e) {
                        }
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("Årlig kontigent pris er: "+pris+" kr. Vil du med betale kontigent nu eller senere? Nu/Senere");
        String betalt = sc.nextLine();
        if (betalt.equalsIgnoreCase("Nu")){
            kontigent="Betalt";
        } else {
            kontigent="Ikke betalt";
        }

        String medlemData = MEDLEMSID+medlemsId+" "+NAVN+navn + ", " +ALDER+ alder + ", " + type + ", Aldersgruppe: " + svømmekategori + ", " + kontigent;
            //BufferedWriter skriver medlemsData ind i tekst filen Medlemmer
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILNAVN, true))) {
            writer.write(medlemData);
            writer.newLine();
            System.out.println("Medlemmet er registreret med ID: " + medlemsId);
        } catch (IOException e) {
            System.out.println("Fejl ved skrivning til fil:");
        }
    }

    public static void konkurrenceStævne() {
        Scanner keyboard = new Scanner(System.in);
        String stævne;
        String navn;
        String disciplin;
        int placering = 0;
        String speedo = "";
        String stævnePlacering;
        String yapping;
        boolean keepSwimming = true;
        try (BufferedReader reader = new BufferedReader(new FileReader(KONKURRENCE))) {
            System.out.println("Indtast navn på stævne");
            stævne = keyboard.nextLine();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(KONKURRENCE, true))) {
                // Indtaster stævnets navn og gemmer det
                writer.newLine();
                writer.write(stævne);
                writer.newLine();
                }
                catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);}
                // vi starter et while loop for at vi kan registrere flere svømmere til stævnet
                while (keepSwimming) {
                    boolean dicsiplinLoop=true;

                    System.out.println("indtast svømmers navn");
                    navn = keyboard.nextLine();
                    System.out.println("Indtast disciplin. (" + RYG + "," + CRAWL + "," + BRYST + "," + BUTTERFLY);
                    disciplin = keyboard.nextLine();
                    // Vi tjekker om indputtet vi skriver er en valid disciplin
                    if (disciplin.equalsIgnoreCase(RYG) || disciplin.equalsIgnoreCase(CRAWL) || disciplin.equalsIgnoreCase(BRYST) || disciplin.equalsIgnoreCase(BUTTERFLY)) {
                        System.out.println("Indtast placering");

                    }
                    else {
                        System.out.println("Ugyldig disciplin");}

                    while (dicsiplinLoop) {
                       try {
                           placering = keyboard.nextInt();
                           keyboard.nextLine();
                           dicsiplinLoop=false;
                       } catch (InputMismatchException t){
                           System.out.println("Forkert input indtast et tal");
                       }

                    }
                        System.out.println("Hvor hurtigt svømmede personen? I format Minut.Sekund");
                        boolean speedoTid = true;

                        // while loop igen for at man kan indtaste en tid indtil man inputter en korrekt tid
                        while (speedoTid) {
                            speedo = keyboard.nextLine();
                            speedo = speedo.replace(",", ".");
                            if (speedo.matches("\\d+\\.\\d{1,2}")) {
                                speedoTid = false; // Gyldigt format
                            } else {
                                System.out.println("Ugyldigt input. Indtast tid som Minut.Sekund (f.eks. 2.30)");
                            }
                        }

                            double tid;
                            try {
                                tid = Double.parseDouble(speedo);
                            } catch (NumberFormatException p) {
                                System.out.println("Ugyldigt input. Indtast tid som Minut.Sekund");
                            }

                            stævnePlacering = "Svømmer: " + navn + ", Disciplin: " + disciplin + ", Placering: " + placering + ", Tid  " + speedo;
                            try (BufferedWriter writer = new BufferedWriter(new FileWriter(KONKURRENCE, true))) {
                                writer.write(stævnePlacering);
                                writer.newLine();
                                System.out.println("Placeringen er blevet registreret til stævne " + stævne);

                            } catch (IOException t) {
                                throw new RuntimeException(t);
                            }
                            System.out.println("Vil du registrere flere svømme? (Ja/Nej)");
                            yapping = keyboard.nextLine();
                            if (yapping.equalsIgnoreCase("Ja")) {
                                continue;
                            } else keepSwimming = false;

                        }

                    }

                




    private static int findMaxMedlemsId() {
        int maxId = 1;
        try (BufferedReader reader = new BufferedReader(new FileReader(FILNAVN))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(MEDLEMSID)) {
                    // Finder medlemsID fra linjen
                    String[] parts = line.split(" ");
                    try {
                        int currentId = Integer.parseInt(parts[1]);
                        if (currentId > maxId) {
                            maxId = currentId;
                        }
                    } catch (NumberFormatException e) {
                        // Hvis ikke et rigtig id, så spring over
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
        konkurrenceStævne();

        while (true) {
            System.out.println("Vælg en mulighed:");
            System.out.println("1. Tilføj nyt medlem");
            System.out.println("2. Vis alle medlemmer");
            System.out.println("3. Afslut");

            int valg = scanner.nextInt();

            switch (valg) {
                case 1: tilføjMedlem(); break;
                case 2: visMedlemmer();
                    System.out.println();break;

                case 3: {
                    System.out.println("Afslutter programmet.");
                    return;
                }
                default: System.out.println("Ugyldigt valg, prøv igen.");
            }
        }
    }
}