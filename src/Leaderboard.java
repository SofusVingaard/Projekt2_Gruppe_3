import java.io.*;
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
