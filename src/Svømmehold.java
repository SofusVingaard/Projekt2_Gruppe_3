import java.util.Scanner;

public class Svømmehold {
    private static final String FILNAVN = "srcMedlem,mer.txt";
    private static final String ALDER = "Alder: ";

    public static void main(String[] args) {
        disciplin();

    }

        public static void disciplin() {
        boolean kg = true;
        Scanner kb = new Scanner(System.in);
        int hold;
        while (kg) {
            System.out.println("Her er fem forskellige discipliner");
            System.out.println("1. Bryst: 100m");
            System.out.println("2. Bryst: 200m");
            System.out.println("3. Bryst: 400m");
            System.out.println("4. Crawl: 100m");
            System.out.println("5. Crawl: 200m");
            System.out.println("6. Crawl: 400m");
            System.out.println("7. Butterfly: 100m");
            System.out.println("8. Butterfly: 200m");
            System.out.println("9. Butterfly: 400m");
            System.out.println("10. Ryg: 100m");
            System.out.println("11. Ryg: 200m");
            System.out.println("12. Ryg: 400m");
            System.out.println("0. Afslut");
            hold = kb.nextInt();
            kb.nextLine();
            if (hold == 0) {
                break;
            } else if (hold > 12) {
                kg = false;

            }

            switch (hold) {
                case 1:
                System.out.println("Du har valgt Bryst 100m");
                System.out.println("Phillip Mortensen er din træner, der er hold tider Mandag 18:00");
                break;

                case 2:
                System.out.println("Du har valgt Bryst 200m");
                System.out.println("Phillip Mortensen er din træner, der er hold tider Mandag 19:00");
                break;

                case 3:
                System.out.println("Du har valgt Bryst 400m");
                System.out.println("Phillip Mortensen er din træner, der er hold tider Mandag 20:00");
                break;

                case 4:
                System.out.println("Du har valgt crawl 100m");
                System.out.println("Du har Mads Mortensen er din træner, der er hold tider tirsdag 18:00");
                break;

                case 5:
                System.out.println("Du har valgt crawl 200m");
                System.out.println("Du har Mads Mortensen er din træner, der er hold tider tirsdag 19:00");
                break;

                case 6:
                System.out.println("Du har valgt crawl 400m");
                System.out.println("Du har Mads Mortensen er din træner, der er hold tider tirsdag 20:00");
                break;

                case 7:
                System.out.println("Du har valgt butterfly 100m");
                System.out.println("Du har Mahad Mortensen er din træner, der er hold tider tirsdag 18:00");
                break;

                case 8:
                System.out.println("Du har valgt butterfly 200m");
                System.out.println("Du har Mahad Mortensen er din træner, der er hold tider tirsdag 19:00");
                break;

                case 9:
                System.out.println("Du har valgt butterfly 400m");
                System.out.println("Du har Mahad Mortensen er din træner, der er hold tider tirsdag 20:00");
                break;

                case 10:
                System.out.println("Du har valgt Ryg 100m");
                System.out.println("Du har Ronaldo Mortensen er din træner, der er hold tider torsdag 18:00");
                break;

                case 11:
                System.out.println("Du har valgt Ryg 200m");
                System.out.println("Du har Ronaldo Mortensen er din træner, der er hold tider torsdag 19:00");
                break;

                case 12:
                System.out.println("Du har valgt ryg 400m");
                System.out.println("Du har Ronaldo Mortensen er din træner, der er hold tider torsdag 20:00");
                break;
            }
        }
    }
}