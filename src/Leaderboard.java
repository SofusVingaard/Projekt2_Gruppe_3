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
