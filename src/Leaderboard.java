import java.io.*;
import java.util.*;

public class Leaderboard {

    // info om stævne
    private static final String KONKURRENCE = "src/Konkurrence stævner";
    private static List<Svømmer> svømmere = new ArrayList<>();

//svømmer klasse
    static class Svømmer implements Comparable<Svømmer> {
        private String navn;
        private String disciplin;
        private int placering;
        private double tid;

        public Svømmer(String navn, String disciplin, int placering, double tid) {
            this.navn = navn;
            this.disciplin = disciplin;
            this.placering = placering;
            this.tid = tid;
        }

        public String getNavn() {
            return navn;
        }

        public String getDisciplin() {
            return disciplin;
        }

        public int getPlacering() {
            return placering;
        }

        public double getTid() {
            return tid;
        }

        // for at sortere svømmere
        @Override
        public int compareTo(Svømmer andenSvømmer) {
            // sammenligner tider
            int tidComparison = Double.compare(this.tid, andenSvømmer.getTid());

            // Hvis tiderne er ens, så sorteres efter disciplin
            if (tidComparison == 0) {
                return this.disciplin.compareTo(andenSvømmer.getDisciplin());
            }

            return tidComparison; // Returnerer forskellen i tid (stigende)
        }
    }

    // læs fra fil
    public static void læsSvømmereFraFil() {
        try (BufferedReader reader = new BufferedReader(new FileReader(KONKURRENCE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Stævne:")) {
                    continue; // Ignorer linjer der starter med "Stævne:"
                }

                // split dataen op
                String[] parts = line.split(" ");
                if (parts.length == 4) {  // Hvis vi har nok data
                    String navn = parts[0];
                    String disciplin = parts[1];
                    int placering = Integer.parseInt(parts[2]);
                    double tid = Double.parseDouble(parts[3]);

                    Svømmer svømmer = new Svømmer(navn, disciplin, placering, tid);
                    svømmere.add(svømmer);
                }
            }

            // Sorter svømmerne efter tid og disciplin
            Collections.sort(svømmere);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // kun de 5 bedste per disciplin
    public static void visLeaderboard() {
        System.out.println("Leaderboard:");

        // Opret en liste af alle de forskellige discipliner
        Set<String> disciplinerSet = new HashSet<>();
        for (Svømmer svømmer : svømmere) {
            disciplinerSet.add(svømmer.getDisciplin());
        }

        // For hver disciplin, find de 5 bedste svømmere
        for (String disciplin : disciplinerSet) {
            System.out.println("\nDisciplin: " + disciplin);
            int count = 0;

            // Gå igennem svømmere og vis kun de 5 bedste per disciplin
            for (Svømmer svømmer : svømmere) {
                if (svømmer.getDisciplin().equals(disciplin) && count < 5) {
                    System.out.println(svømmer.getNavn() + " - Tid: " + svømmer.getTid() + " - Placering: " + svømmer.getPlacering());
                    count++;
                }
            }
        }
    }

    // Main metode for at køre programmet
    public static void main(String[] args) {
        // Læs svømmere fra fil og vis leaderboard
        læsSvømmereFraFil();
        visLeaderboard();
    }
}
/*import java.io.*;
import java.util.*;

public class Leaderboard {
    private List<Svømmer> leaderboard; // Liste til at opbevare svømmerne

    // Indre klasse Svømmer
    private class Svømmer {
        private String navn;
        private String disciplin;
        private double tid; // Tid i formatet minut.sekund

        public Svømmer(String navn, String disciplin, double tid) {
            this.navn = navn;
            this.disciplin = disciplin;
            this.tid = tid;
        }

        public String getNavn() {
            return navn;
        }

        public String getDisciplin() {
            return disciplin;
        }

        public double getTid() {
            return tid;
        }

        @Override
        public String toString() {
            return navn + " - " + disciplin + ": " + tid + " sek.";
        }
    }

    public Leaderboard() {
        leaderboard = new ArrayList<>();
    }

    // Læs svømmere og deres tider fra konkurrence filen
    public void læsSvømmereFraFil() {
        try (BufferedReader reader = new BufferedReader(new FileReader(MedlemsRegistering.KONKURRENCE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Eksempel på format: Svømmer: John, Disciplin: Crawl, Placering: 1, Tid: 2.45
                if (line.startsWith("Svømmer")) {
                    String[] parts = line.split(",");
                    String navn = parts[0].split(":")[1].trim();
                    String disciplin = parts[1].split(":")[1].trim();
                    String tidStr = parts[3].split(":")[1].trim();
                    double tid = Double.parseDouble(tidStr);

                    // Opret Svømmer objekt og tilføj til leaderboard
                    Svømmer svømmer = new Svømmer(navn, disciplin, tid);
                    leaderboard.add(svømmer);
                }
            }
        } catch (IOException e) {
            System.out.println("Fejl ved læsning af fil: " + e.getMessage());
        }
    }

    // Sorter svømmerne efter tid i stigende rækkefølge (bedste tid først)
    public void sorterLeaderboard() {
        leaderboard.sort(Comparator.comparingDouble(Svømmer::getTid)); // Sorter efter tid
    }

    // Vis leaderboardet
    public void visLeaderboard() {
        if (leaderboard.isEmpty()) {
            System.out.println("Ingen svømmere i leaderboardet.");
            return;
        }

        System.out.println("Leaderboard:");
        for (Svømmer svømmer : leaderboard) {
            System.out.println(svømmer); // Svømmerens navn og tid
        }
    }

    public static void main(String[] args) {
        Leaderboard leaderboard = new Leaderboard();

        // Læs svømmere fra fil
        leaderboard.læsSvømmereFraFil();

        // Sorter leaderboardet efter tid
        leaderboard.sorterLeaderboard();

        // Vis det opdaterede leaderboard
        leaderboard.visLeaderboard();
    }
}
*/

/*import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Leaderboard {

    private static final String FILNAVN = "src/Leaderboard.txt";
    private static final String BRYST= "Bryst";
    private static final String CRAWL="Crawl";
    private static final String BUTTERFLY= "Butterfly";
    private static final String RYG= "Ryg";

    public static void tilføjEllerOpdaterTid(String navn, String disciplin, double tid) {
        Map<String, Map<String, Double>> leaderboard = læsLeaderboard();
        leaderboard.putIfAbsent(navn, new HashMap<>());

        // Tjek, om den nye tid er bedre end den eksisterende
        Map<String, Double> discipliner = leaderboard.get(navn);
        if (discipliner.containsKey(disciplin) && discipliner.get(disciplin) <= tid) {
            System.out.println("Den nye tid er ikke bedre end den nuværende.");
            return;
        }

        discipliner.put(disciplin, tid);
        leaderboard.put(navn, discipliner);
        skrivLeaderboard(leaderboard);
        System.out.println("Tid tilføjet for " + navn + " i disciplinen " + disciplin + ": " + tid);
    }


    public static void visLeaderboard() {
        Map<String, Map<String, Double>> leaderboard = læsLeaderboard();
        if (leaderboard.isEmpty()) {
            System.out.println("Der er ingen registrerede tider.");
            return;
        }

        System.out.println("Leaderboard:");
        for (var entry : leaderboard.entrySet()) {
            System.out.print("Navn: " + entry.getKey() + " - ");
            Map<String, Double> discipliner = entry.getValue();
            discipliner.forEach((disciplin, tid) -> System.out.print(disciplin + ":" + tid + ", "));
            System.out.println();
        }
    }


    private static Map<String, Map<String, Double>> læsLeaderboard() {
        Map<String, Map<String, Double>> leaderboard = new HashMap<>();
        try {
            List<String> linjer = Files.readAllLines(Paths.get(FILNAVN));
            for (String linje : linjer) {
                String[] dele = linje.split(";");
                String navn = dele[0];
                Map<String, Double> discipliner = new HashMap<>();

                String[] disciplinerData = dele[1].split(", ");
                for (String disciplinTid : disciplinerData) {
                    String[] data = disciplinTid.split(": ");
                    String disciplin = data[0];
                    double tid = Double.parseDouble(data[1]);
                    discipliner.put(disciplin, tid);
                }
                leaderboard.put(navn, discipliner);
            }
        } catch (IOException e) {
            System.out.println("Fejl ved læsning af fil: " + e.getMessage());
        }
        return leaderboard;
    }


    private static void skrivLeaderboard(Map<String, Map<String, Double>> leaderboard) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILNAVN))) {
            for (var entry : leaderboard.entrySet()) {
                String navn = entry.getKey();
                Map<String, Double> discipliner = entry.getValue();

                StringBuilder linje = new StringBuilder(navn + ": ");
                discipliner.forEach((disciplin, tid) -> linje.append(disciplin).append(":").append(tid).append(", "));

                linje.setLength(linje.length() - 2);

                writer.write(linje.toString());
                writer.newLine();
            }
            System.out.println("Leaderboard er gemt i filen: " + FILNAVN);
        } catch (IOException e) {
            System.out.println("Kunne ikke skrive til fil: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Vælg en mulighed:");
            System.out.println("1. Tilføj eller opdater tid");
            System.out.println("2. Vis leaderboard");
            System.out.println("3. Afslut");

            int valg = scanner.nextInt();
            scanner.nextLine();

            switch (valg) {
                case 1 -> {
                    System.out.println("Indtast navn:");
                    String navn = scanner.nextLine();
                    System.out.println("Indtast disciplin ("+CRAWL+","+RYG+","+BUTTERFLY+","+BRYST+"):");
                    String disciplin = scanner.nextLine();
                    if (disciplin.equalsIgnoreCase(BRYST) || disciplin.equalsIgnoreCase(RYG)|| disciplin.equalsIgnoreCase(BUTTERFLY)|| disciplin.equalsIgnoreCase(CRAWL)){
                        System.out.println("korrekt");
                    }
                    else {
                        System.out.println("Ugyldigt input, du sendes tilbage til start menuen");
                        System.out.println();
                        continue;
                    }
                    System.out.println("Indtast tid:");
                    String tidInput = scanner.nextLine();
                    tidInput = tidInput.replace(",", ".");

                    double tid;
                    try {
                        tid = Double.parseDouble(tidInput);
                    } catch (NumberFormatException e) {
                        System.out.println("Ugyldigt input. Sørg for at indtaste et tal i formatet 12.34 eller 12,34.");
                        continue; // Gå tilbage til menuen
                    }
                    tilføjEllerOpdaterTid(navn, disciplin, tid);
                }
                case 2 -> visLeaderboard();
                case 3 -> {
                    System.out.println("Afslutter programmet.");
                    return;
                }
                default -> System.out.println("Ugyldigt valg, prøv igen.");
            }
        }
    }
}
*/