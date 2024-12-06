import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Velkommen til Svømmeklubbben Målev Dolphins");

        Scanner keyboard = new Scanner(System.in);
        boolean keepGoing = true;
        int tast;


            while (keepGoing) {
            System.out.println("1: Se åbningstider:");
            System.out.println("2: Opret medlem:");
            System.out.println("3: Se medlemmer:");
            System.out.println("4: Gå til leaderboard:");
            System.out.println("5: Opret træningstid");
            System.out.println("6: Se om medlemmer er i restanse");
            System.out.println("7: Køb dailyPass til svømmehallen");
            System.out.println("8: Køb vare i SwipShop");
            System.out.println("9: Indtast et konkurrence stævne");
            System.out.println("0: Luk Programmet:");

                try { tast = keyboard.nextInt();
                //clear vores scanner buffer
                keyboard.nextLine();

                 if (tast == 0) {
                    System.out.println("Tak for i dag");
                    keepGoing = false;

                }
                 else if (tast >= 10 || tast<=-1) {
                    System.out.println("ugyldigt");
                    return;
                }
                switch (tast) {

                    case 1:
                        System.out.println("Vores åbningstider er");
                        System.out.println("Mandag-fredag 10-20");
                        System.out.println("lørdag-søndag 10-18");
                        break;
                    case 2:
                        MedlemsRegistering.tilføjMedlem();
                        break;
                    case 3:
                        MedlemsRegistering.visMedlemmer();
                        break;
                    case 4:
                        Leaderboard.main(args);
                        break;
                    case 5:
                         Leaderboard.Træningstider.opretTræningstid();
                        break;
                    case 6:
                        Restanse.restance();
                        break;
                    case 7:
                        SwimShop.startService();
                        break;
                    case 9:
                        MedlemsRegistering.konkurrenceStævne();
                        break;
                 }
            }
                catch (InputMismatchException e) {
                    System.out.println("Forkert input. Indtast et tal mellem 0 og 9");
                    System.out.println();
                    keyboard.nextLine();
            } catch (Exception e){
                    System.out.println("Der skete en fejl "+e.getMessage()+" prøv igen");
                }
        }
    }
}

