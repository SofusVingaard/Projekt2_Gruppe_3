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
            System.out.println("1. Bryst - 100 m");
            System.out.println("2. Bryst - 200 m");
            System.out.println("3. Bryst - 400 m");
            System.out.println("4. Crawl - 100 m");
            System.out.println("5. Crawl - 200 m");
            System.out.println("6. Crawl - 400 m");
            System.out.println("7. Butterfly - 100 m");
            System.out.println("8. Butterfly - 200 m");
            System.out.println("9. Butterfly - 400 m");
            System.out.println("10. Ryg - 100 m");
            System.out.println("11. Ryg - 200 m");
            System.out.println("12. Ryg - 400 m");
            System.out.println("0. Afslut");
            hold = kb.nextInt();
            kb.nextLine();
            if (hold == 0) {
                System.out.println("Afslutter programmet");
                break;
            } else if (hold > 12 || hold < 0) {
                System.out.println("Ugyldigt valg, proev igen");
                continue;

            }
            switch (hold) {
                case 1:
                    System.out.println("Du har valgt Bryst - 100 m");
                    System.out.println("Phillip Mortensen er din træner, der er hold tider Mandag");
                    break;
                case 2:
                    System.out.println("Du har valgt Bryst - 200 m");
                    System.out.println("Phillip Mortensen er din træner, der er hold tider Mandag");
                    break;
                case 3:
                    System.out.println("Du har valgt Bryst - 400 m");
                    System.out.println("Phillip Mortensen er din træner, der er hold tider Mandag");
                    break;
                case 4:
                    System.out.println("Du har valgt Crawl - 100 m");
                    System.out.println("Phillip Mortensen er din træner, der er hold tider Tirsdag");
                    break;
                case 5:
                    System.out.println("Du har valgt Crawl - 200 m");
                    System.out.println("Phillip Mortensen er din træner, der er hold tider Tirsdag");
                    break;
                case 6:
                    System.out.println("Du har valgt Crawl - 400 m");
                    System.out.println("Phillip Mortensen er din træner, der er hold tider Tirsdag");
                    break;
                case 7:
                    System.out.println("Du har valgt Butterfly - 100 m");
                    System.out.println("Mads Mortensen er din træner, der er hold tider Onsdag");
                    break;
                case 8:
                    System.out.println("Du har valgt Butterfly - 200 m");
                    System.out.println("Mads Mortensen er din træner, der er hold tider Onsdag");
                    break;
                case 9:
                    System.out.println("Du har valgt Butterfly - 400 m");
                    System.out.println("Mads Mortensen er din træner, der er hold tider Onsdag");
                    break;
                case 10:
                    System.out.println("Du har valgt Ryg - 100 m");
                    System.out.println("Du har Mads Mortensen er din træner, der er hold tider Torsdag");
                    break;
                case 11:
                    System.out.println("Du har valgt Ryg - 200 m");
                    System.out.println("Du har Mads Mortensen er din træner, der er hold tider Torsdag");
                    break;
                case 12:
                    System.out.println("Du har valgt Ryg - 400 m");
                    System.out.println("Du har Mads Mortensen er din træner, der er hold tider Torsdag");
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