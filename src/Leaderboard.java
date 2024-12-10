import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Leaderboard {

    private static final String TRÆNING_FIL = "src/TekstFiler/Træningstider.txt";
    private static List<Træning> træninger = new ArrayList<>();


    static class Træning {
        private String navn;
        private String disciplin;
        private String dato;
        private double træningstid;
        private int alder;
        private int medlemsId;

        public Træning(String navn, String disciplin, String dato, double træningstid, int alder, int medlemsId) {
            this.navn = navn;
            this.disciplin = disciplin;
            this.dato = dato;
            this.træningstid = træningstid;
            this.alder = alder;
            this.medlemsId = medlemsId;
        }

        public String getNavn() {
            return navn;
        }

        public String getDisciplin() {
            return disciplin;
        }

        public String getDato() {
            return dato;
        }

        public double getTræningstid() {
            return træningstid;
        }

        public int getAlder() {
            return alder;
        }

        public int getMedlemsId() {
            return medlemsId;  // Hent medlemsId
        }
    }

    // Læs træningstider fra fil
    public static void læsTræningstiderFraFil() {
        try (BufferedReader reader = new BufferedReader(new FileReader(TRÆNING_FIL))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Split dataen op
                String[] parts = line.split(", ");
                if (parts.length == 5) {
                    String navn = parts[1].split(": ")[1];
                    String disciplin = parts[3].split(": ")[1];
                    String dato = parts[2].split(": ")[1];
                    double træningstid = Double.parseDouble(parts[4].split(": ")[1]);
                    int medlemsId = Integer.parseInt(parts[0].split(": ")[1]);

                    // Find medlemmet med medlemsId og alder fra medlemmer.txt
                    int alder = findAlderByMedlemsId(medlemsId);

                    Træning træning = new Træning(navn, disciplin, dato, træningstid, alder, medlemsId);  // Inkluder medlemsId
                    træninger.add(træning);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Find medlemmers alder
    static int findAlderByMedlemsId(int medlemsId) {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/TekstFiler/Medlemmer.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Tjekker for medlemsid/medlemsnummer
                if (line.contains("Medlemsnummer: " + medlemsId)) {
                    // Find alderen i linjen
                    String[] parts = line.split("Alder: ");
                    if (parts.length > 1) {
                        String alderString = parts[1].split(",")[0].trim();
                        return Integer.parseInt(alderString); // her for vi så alderen tilbage
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Fejl ved læsning af medlemmer-fil.");
        } catch (NumberFormatException e) {
            System.out.println("Ugyldig alder for medlemsID: " + medlemsId);
        }
        return -1; // Returnér -1, hvis alderen ikke kunne findes
    }

    // Vis leaderboard opdelt i aldersgrupper
    public static void visLeaderboard(int valg) {
        // Opret aldersgrupper
        List<Træning> under17 = new ArrayList<>();
        List<Træning> over18 = new ArrayList<>();

        // Inddel træninger i aldersgrupper
        for (Træning træning : træninger) {
            if (træning.getAlder() <=17) {
                under17.add(træning);
            } else {
                over18.add(træning);
            }
        }

        // Sorter træninger efter disciplin og tid
        Collections.sort(under17, Comparator.comparing(Træning::getDisciplin).thenComparingDouble(Træning::getTræningstid));
        Collections.sort(over18, Comparator.comparing(Træning::getDisciplin).thenComparingDouble(Træning::getTræningstid));

        // Vis top 5 kommer an på hvilken man vælger

        if (valg == 1) {
            visTop5(under17, "Under 18");
        } else if (valg == 2) {
            visTop5(over18, "Senior");
        } else {
            System.out.println("Ugyldigt valg. Prøv igen.");
        }
    }

    // Vis de 5 bedste træningstider
    private static void visTop5(List<Træning> træninger, String gruppe) {
        Map<String, List<Træning>> discipliner = new TreeMap<>();  // Jeg har brugt TreeMap da den holder ordning

        // sorterer træningerne i discipliner
        for (Træning træning : træninger) {
            discipliner.putIfAbsent(træning.getDisciplin(), new ArrayList<>());
            discipliner.get(træning.getDisciplin()).add(træning);
        }

        // For hver disciplin, få de 5 bedste og vis dem
        System.out.println(gruppe + " leaderboard:");
        for (Map.Entry<String, List<Træning>> entry : discipliner.entrySet()) {
            System.out.println("Disciplin: " + entry.getKey());

            List<Træning> disciplineTræninger = entry.getValue();
            int count = 0;
            for (Træning træning : disciplineTræninger) {
                if (count < 5) {
                    System.out.println("MedlemsID: " + træning.getMedlemsId() + " - " + træning.getNavn()
                            + " - Tid: " + træning.getTræningstid() + " - Dato: " + træning.getDato());
                    count++;
                }
            }
            System.out.println(); // Tilføj en ny linje efter hver disciplin yessir
        }
    }

    public static void main(String[] args) {
        læsTræningstiderFraFil();

        Scanner scanner = new Scanner(System.in);
         while (true) {


            int valg = 0;
            while (true) {
                System.out.println("Vælg en mulighed:");
                System.out.println("1. Se Under 18 leaderboard");
                System.out.println("2. Se Senior leaderboard");
                System.out.println("3. Afslut");
               try {
                   valg = Integer.parseInt(scanner.nextLine());
               }  catch (Exception e){
                   System.out.println("Fejl");
               }
               if (valg==1||valg==2){
                   break;
               }

               if (valg == 3) {
                    return;
                } else if (valg<=0 || valg>=4){

                } else {
                }
            }
            visLeaderboard(valg);
        }
    }

    public static class Træningstider {
        static final String MEDLEMMER_FIL = "src/TekstFiler/Medlemmer.txt";
        static final String TRÆNING_FIL = "src/TekstFiler/Træningstider.txt";


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
        static boolean erGyldigDato(String dato) {
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
            int alder = findAlderByMedlemsId(medlemsId);
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
            while (true) {
                disciplin = sc.nextLine();
                if (disciplin.equalsIgnoreCase("RYG") || disciplin.equalsIgnoreCase("CRAWL") || disciplin.equalsIgnoreCase("BRYST") || disciplin.equalsIgnoreCase("BUTTERFLY")) {
                    break;
                } else {
                    System.out.println("Ugyldig disciplin");
                    System.out.println("Venligst indtast: " + "Ryg," + " " + "Crawl," + " " + "Bryst" + " " + "Butterfly");
                }
            }

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

            String træningData = "MedlemsID: " + medlemsId + ", Navn: " + medlemNavn + ", Alder: " + alder +
                    ", Dato: " + dato + ", Disciplin: " + disciplin + ", Træningstid: " + træningstidInput;
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
}