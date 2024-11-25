import java.util.Scanner;

public class Svømmehold {

    private static final String FILNAVN = "srcMedlem,mer.txt";
    boolean svømmehold = true;

    public static void hold() {
        Scanner keyboard = new Scanner(System.in);
        String ALDER = keyboard.nextLine();

        System.out.println("");

        if (ALDER.equals()){
            System.out.println("Du er på junior holdet");
        }else if (ALDER.equals(18-59)){
            System.out.println("Du er på Senior holdet");
        }else {
            System.out.println("Du er på 60+ holdet");
        }


    }



        }
    }


