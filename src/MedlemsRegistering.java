import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MedlemsRegistering {
    //Klasse bibliotek
    static final String FILNAVN = "src/TekstFiler/Medlemmer.txt";
    private static final String KONTIGENT= "src/TekstFiler/betaling.txt";
    private static final String NAVN= "Navn: ";
    private static final String ALDER="Alder: ";
    private static final String AKTIV="Medlemsskab: Aktiv";
    private static final String PASSIV="Medlemsskab: Passiv";
    static final String MEDLEMSID="Medlemsnummer: ";
    static final String KONKURRENCE="src/TekstFiler/Konkurrence stævner";
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
        int alder;
        int konkurrence=0;
        int motion=0;
        String medlemData;
        String konkurrenceDisciplin = "";


        int medlemsId= findMaxMedlemsId()+1;
        //Indtast medlemmets information
        System.out.println("Indtast Medlemmets navn:");
        String navn;
        while (true) {
            konkurrence=0;
            motion=0;
            navn = sc.nextLine();
            if (navn.isBlank() || !navn.matches("[a-zA-Z -æøåÆØÅ]+") || navn.equals("-") || navn.split("-").length>2){
                System.out.println("Ugyldigt navn");
                System.out.println("Indtast venligst et navn");
            }
            else {
                break;
            }
        }
        System.out.println("Indtast Medlemmets alder:");
        while (true) {
            try { alder = sc.nextInt();
            sc.nextLine();

                if (alder <= 17 && alder >= 1) {
                    svømmekategori = "Junior";
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
                                    pris = kontigentPris;
                                } catch (NumberFormatException e) {
                                }
                            }
                        }
                    } catch (IOException e) {
                        System.out.println("Ugyldigt input");
                        System.out.println("Skriv din alder");
                        throw new RuntimeException(e);
                    } catch (InputMismatchException e) {
                        System.out.println("Ugyldigt input");
                        System.out.println("Skrive din alder");
                    }

                    break;
                } else if (alder <= 60 && alder >= 18) {
                    svømmekategori = "Senior";
                    // læs kommentar fra junior
                    try (BufferedReader reader = new BufferedReader(new FileReader(KONTIGENT))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            if (line.startsWith("Senior")) {
                                String[] parts = line.split(" ");
                                try {
                                    int kontigentPris = Integer.parseInt(parts[1]);
                                    pris = kontigentPris;
                                } catch (NumberFormatException e) {
                                }
                            }
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (InputMismatchException e) {
                        System.out.println("Ugyldigt input");
                        System.out.println("Skrive din alder");
                    }
                    break;
                } else if (alder >= 60) {
                    svømmekategori = "60+";
                    // læs kommentar fra junior
                    try (BufferedReader reader = new BufferedReader(new FileReader(KONTIGENT))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            if (line.startsWith("Senior")) {

                                String[] parts = line.split(" ");
                                try {
                                    int kontigentPris = Integer.parseInt(parts[1]);
                                    pris = kontigentPris * 0.75;
                                } catch (NumberFormatException e) {
                                }
                            }
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (InputMismatchException e) {
                        System.out.println("Ugyldigt input");
                        System.out.println("Skrive din alder");
                    }
                    break;
                } else {
                    System.out.println("Indtast venligst en alder der er over 0 ");
                }
            }
            catch (InputMismatchException e){
                System.out.println("Ugyldigt input");
                System.out.println("Indtast din alder");
                sc.nextLine();
            }
        }

        System.out.println("Indtast Medlemmets type (Aktiv)/Passiv):");
        System.out.println("Et aktivt medlemskab koster 1600 for senior, 1000kr for junior og 1200 for 60+.");
        System.out.println("Et passivt medlemskab koster 500kr");
        String type;
        while (true) {

            type = sc.nextLine();
            if (type.equalsIgnoreCase("Aktiv")) {
                type = AKTIV;
                System.out.println("Ønsker du at være konkurrence svømmer eller motions svømmer? Konkurrence/Motion");
                while(true) {
                    String svømmer = sc.nextLine();
                    if (svømmer.equalsIgnoreCase("Konkurrence")) {

                            System.out.println("Hvilken disciplin? " + RYG + " " + BRYST + " " + BUTTERFLY + " " + CRAWL);
                        while(true) {
                            konkurrenceDisciplin = sc.nextLine();
                            if (konkurrenceDisciplin.equalsIgnoreCase(RYG) || konkurrenceDisciplin.equalsIgnoreCase(BRYST) || konkurrenceDisciplin.equalsIgnoreCase(BUTTERFLY) || konkurrenceDisciplin.equalsIgnoreCase(CRAWL)) {
                                System.out.println("Du er nu registeret som " + konkurrenceDisciplin + " svømmer");
                                konkurrence = 1;
                                break;
                            } else {
                                System.out.println("Indtast "+RYG+ " " + BRYST + " " + BUTTERFLY + " " + CRAWL);
                            }
                        } break;
                    }else if (svømmer.equalsIgnoreCase("Motion")) {
                        motion = 1;
                        break;
                    } else {
                        System.out.println("Indtast motion eller konkurrence");
                    }
                }
                break;
            } else if (type.equalsIgnoreCase("Passiv")) {
                type = PASSIV;
                // læs kommentar fra junior
                try (BufferedReader reader = new BufferedReader(new FileReader(KONTIGENT))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (line.startsWith("Passiv")) {
                            String[] parts = line.split(" ");
                            try {
                                int kontigentPris = Integer.parseInt(parts[1]);
                                pris = kontigentPris;
                            } catch (NumberFormatException e) {
                            }
                        }
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            }
            else {
                System.out.println("Indtast passiv eller aktiv");
            }
        }
        System.out.println("Årlig kontigent pris er: "+pris+" kr. Vil du betale kontigent nu eller senere? Nu/Senere");
        String betalt;
        while (true) {
            betalt = sc.nextLine();
            if (betalt.equalsIgnoreCase("Nu")) {
                kontigent = "Betalt";
                break;
            } else if (betalt.equalsIgnoreCase("Senere")) {
                kontigent = "Ikke betalt";
                break;
            }
            else {
                System.out.println("Indtast nu eller senere");
            }
        }
        if (konkurrence == 1){
            medlemData=MEDLEMSID+medlemsId+" "+NAVN+navn + ", " +ALDER+ alder + ", " + type + ", Aldersgruppe: " + svømmekategori + ", " + kontigent+", Konkurrence svømmer i "+konkurrenceDisciplin;
        } else if (motion==1) {
            medlemData=MEDLEMSID+medlemsId+" "+NAVN+navn + ", " +ALDER+ alder + ", " + type + ", Aldersgruppe: " + svømmekategori + ", " + kontigent+", Motionssvømmer";
        } else {
            medlemData = MEDLEMSID + medlemsId + " " + NAVN + navn + ", " + ALDER + alder + ", " + type + ", Aldersgruppe: " + svømmekategori + ", " + kontigent;
        }
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
        LocalDate idag= LocalDate.now();
        DateTimeFormatter pattern= DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);
        String formatdato=idag.format(pattern);

        boolean keepSwimming = true;
        try (BufferedReader reader = new BufferedReader(new FileReader(KONKURRENCE))) {
            System.out.println("Indtast navn på stævne");
            while (true) {
                stævne = keyboard.nextLine();
                if (stævne.isBlank()) {
                    System.out.println("Ugyldigt input");
                    System.out.println("Indtast navnet på stævnet");
                }
                else
                    break;
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(KONKURRENCE, true))) {
                // Indtaster stævnets navn og gemmer det
                //Bruges til at lave første bogstav i et navn stort
                String firstLetter= stævne.substring(0, 1);
                firstLetter=firstLetter.toUpperCase();
                String restOfWord=stævne.substring(1);
                String stortBogstav= firstLetter+restOfWord;

                writer.newLine();
                writer.write(stortBogstav);
                writer.newLine();
                writer.write("Dato: "+formatdato.formatted(pattern));
                writer.newLine();
                }
                catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);}
                // vi starter et while loop for at vi kan registrere flere svømmere til stævnet
                while (keepSwimming) {
                    System.out.println("indtast svømmers navn");
                    while (true) {
                        navn = keyboard.nextLine();
                        if (navn.isBlank() || !navn.matches("[a-zA-Z æøåÆØÅ-]+") || navn.equals("-") || navn.split("-").length>2){
                            System.out.println("fejl");
                            System.out.println("Indtast venligst et navn");
                        }
                        else {
                            break;
                        }
                    }
                    System.out.println("Indtast disciplin. (" + RYG + "," + CRAWL + "," + BRYST + "," + BUTTERFLY);
                    // Vi tjekker om indputtet vi skriver er en valid disciplin
                    while (true) {
                        disciplin = keyboard.nextLine();
                        if (disciplin.equalsIgnoreCase(RYG) || disciplin.equalsIgnoreCase(CRAWL) || disciplin.equalsIgnoreCase(BRYST) || disciplin.equalsIgnoreCase(BUTTERFLY)) {
                            break;
                        } else {
                            System.out.println("Ugyldig disciplin");
                            System.out.println("Venligst indtast: "+RYG+" "+CRAWL+" "+BRYST+" "+BUTTERFLY);
                        }
                    }
                    System.out.println("Indtast placering");
                    while (true) {
                       try {
                           placering = keyboard.nextInt();
                           keyboard.nextLine();
                           if (placering>=1){
                               break;
                           }
                           else {
                               System.out.println("Forkert input indtast et tal større eller ligmed 1 ");
                           }
                       }
                            catch (InputMismatchException t){
                           System.out.println("Forkert input placeringen skal være et tal der er større eller ligmed 1");
                           keyboard.nextLine();
                       }
                            catch (NumberFormatException p){
                           System.out.println("Forkert input placeringen skal være et tal der erstørre eller ligmed 1");
                           keyboard.nextLine();
                       }
                    }
                        System.out.println("Hvor hurtigt svømmede personen? I format Minut.Sekund");


                        // while loop igen for at man kan indtaste en tid indtil man inputter en korrekt tid
                        while (true) {
                            speedo = keyboard.nextLine();
                            speedo = speedo.replace(",", ".");
                            //Bruges til at formatere vores tidsinterval så den retter 1.5
                            //til 1.05 i stedet
                            if (speedo.matches("\\d+\\.\\d{1,2}")) {
                                String[] timeParts= speedo.split("\\.");
                                int minutter = Integer.parseInt(timeParts[0]);
                                int sekunder;
                            try {
                                sekunder=Integer.parseInt(timeParts[1]);
                                // Tjekker if sekunder er 60 eller over
                                if (sekunder>= 60){
                                    System.out.println("Sekunder skal være 60 eller mindre, indtast tiden igen.");
                                } else{
                                    String formatteredeSekunder= String.format("%02d", sekunder);
                                    speedo=minutter+"."+formatteredeSekunder;
                                    break;
                                }
                            }
                            catch (NumberFormatException e){
                                System.out.println("Ugyldigt input. Indtast tid i Minut.Sekunder. (f.eks 2.30");
                            }

                            } else {
                                System.out.println("Ugyldigt input. Indtast tid som Minut.Sekund (f.eks. 2.30)");
                            }
                        }
                            try {
                            } catch (NumberFormatException p) {
                                System.out.println("Ugyldigt input. Indtast tid som Minut.Sekund");
                            }
                            //Bruges til at lave første bogstav i et navn stort
                            String firstLetter= navn.substring(0, 1);
                            firstLetter=firstLetter.toUpperCase();
                            String restOfWord=navn.substring(1);
                            String stortBogstav= firstLetter+restOfWord;

                            stævnePlacering = "Svømmer: " + stortBogstav + ", Disciplin: " + disciplin + ", Placering: " + placering + ", Tid  " + speedo;
                            try (BufferedWriter writer = new BufferedWriter(new FileWriter(KONKURRENCE, true))) {
                                writer.write(stævnePlacering);
                                writer.newLine();
                                System.out.println("Placeringen er blevet registreret til stævne " + stævne);

                            } catch (IOException t) {
                                throw new RuntimeException(t);
                            }
                            System.out.println("Vil du registrere flere svømmere? (Ja/Nej)");
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
        tilføjMedlem();

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