import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Velkommen til Svømmeklubbben Målev Dolphins");

        Scanner keyboard = new Scanner(System.in);
        boolean areWeEvenAlive;
        boolean keepGoing = true;
        int tast;

        while (keepGoing) {
            System.out.println("1: Se åbningstider:");
            System.out.println("2: Opret medlem:");
            System.out.println("3: Se medlemmer:");
            System.out.println("4: Se top 5 inden for deciplin:");
            System.out.println("5: Opdater pb:");
            System.out.println("0: Luk Programmet:");
            tast = keyboard.nextInt();
            //clear vores scanner buffer
            keyboard.nextLine();

            if (tast == 0) {
                System.out.println("Tak for i dag");
                keepGoing = false;

            } else if (tast >= 5) {
                System.out.println("ugyldigt");
                return;
            }

            switch (tast) {

                case 1:
                    System.out.println("Vores åbningstider er");
                    System.out.println("Mandag-fredag 10-20");
                    System.out.println("lørdag-søndag 10-18");

                case 2:

                case 3:

                case 4:

                case 5:

            }
        }
    }
}
