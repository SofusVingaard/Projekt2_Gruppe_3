import javax.swing.*;
import java.awt.*;
import java.awt.font.LineBreakMeasurer;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        //MåløvDolphins.main(args);
        System.out.println("Velkommen til Svømmeklubbben Målev Dolphins");

        Scanner keyboard = new Scanner(System.in);
        boolean areWeEvenAlive;
        boolean keepGoing = true;
        int tast;

        while (keepGoing) {
            System.out.println("1: Se åbningstider:");
            System.out.println("2: Opret medlem:");
            System.out.println("3: Se medlemmer:");
            System.out.println("4: Gå til leaderboard:");
            System.out.println("5: Registrer svømmers tid til stævne");
            System.out.println("6: Se om medlemmer er i restanse");
            System.out.println("7: Køb dailyPass til svømmehallen");
            System.out.println("8: Køb vare  i SwipShop");
            System.out.println("0: Luk Programmet:");
            tast = keyboard.nextInt();
            //clear vores scanner buffer
            keyboard.nextLine();

            if (tast == 0) {
                System.out.println("Tak for i dag");
                keepGoing = false;

            } else if (tast >= 9) {
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
                    Leaderboard.main();
                    break;
                case 5:
                    MedlemsRegistering.konkurrenceStævne();
                    break;
                case 6:
                    Restanse.restance();
                    break;
                case 7:
                    SwimShop.startService();
                    break;
                case 8:
                    SwimShop.startService2();
                    break;

            }

        }

    }
}




