import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Velkommen til Svømmeklubbben Målev Dolphins");

        Scanner keyboard = new Scanner(System.in);
        boolean areWeEvenAlive;
        boolean keepggoing= true;
        int tast;

        while (keepggoing)
            System.out.println("0: Se åbningstider");
            System.out.println("1: Opret medlem:");
            System.out.println("2: Se medlemmer");
            System.out.println("3: Se top 5 inden for deciplin");
            System.out.println("4: Opdater pb");
        tast = keyboard.nextInt();
        //clear vores scanner buffer
        keyboard.nextLine();

            switch(tast) {
                case 0:
                    System.out.println("Vores åbningstider:");
                    System.out.println("Mandag-fredag: 10-20");
                    System.out.println("Lørdag-søndag: 10-18");

                case 1:

                case 2:

                case 3:

                case 4:




            }
    }
}
