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
            System.out.println("1. Bryst");
            System.out.println("2. Crawl");
            System.out.println("3. Butterfly");
            System.out.println("4. Ryg");
            System.out.println("0. Afslut");
            hold = kb.nextInt();
            kb.nextLine();
            if (hold == 0) {
                break;
            } else if (hold > 4) {
                kg = false;

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
}