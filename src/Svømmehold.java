import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Svømmehold {
    private static final String FILNAVN = "src/Medlemmer.txt";

    public static void main(String[] args) {
        disciplin();

    }

    public static void disciplin() {
        boolean kg = true;
        Scanner kb = new Scanner(System.in);
        int hold;
        while (kg) {
            System.out.println("Her er fem forskellige discipliner");
            System.out.println("1. Bryst");
            System.out.println("2. Crawl");
            System.out.println("3. Butterfly");
            System.out.println("4. Ryg");
            System.out.println("0. Afslut");
            hold = kb.nextInt();
            kb.nextLine();
            if (hold == 0) {
                System.out.println("Afslutter programmet");
                break;
            } else if (hold > 4 || hold < 0) {
                System.out.println("Ugyldigt valg, proev igen");
                continue;

            }

            switch (hold) {
                case 1:
                    System.out.println("Du har valgt Bryst");
                    System.out.println("Phillip Mortensen er din træner, der er hold tider Mandag");
                    break;
                case 2:
                    System.out.println("Du har valgt Crawl");
                    System.out.println("Phillip Mortensen er din træner, der er hold tider Tirsdag");
                    break;
                case 3:
                    System.out.println("Du har valgt Butterfly");
                    System.out.println("Mads Mortensen er din træner, der er hold tider Onsdag");
                    break;
                case 4:
                    System.out.println("Du har valgt Ryg");
                    System.out.println("Du har Mads Mortensen er din træner, der er hold tider Torsdag");
                    break;
                case 5:
                    System.out.println("Afsluttet");
                    break;

            }
        }
    }

    public static void skrivTilFil(String data) {
        try (FileWriter writer = new FileWriter(FILNAVN, true)) { // "true" for at tilføje til filen
            writer.write(data + "\n");
        } catch (IOException e) {
            System.out.println("Fejl: Kunne ikke skrive til filen " + FILNAVN);
        }
    }
}