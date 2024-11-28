import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SwimShop {
    // Klassevariabel til at gemme dailyPass
    private static double dailyPass = 0;
    private static class revenue {
        final private String customerId; //Så kan man holde styr mellem flere kunder
        final private double amountPaid; //Hvor meget kunden betaler
        private double change; //tilbagebetalingen


        public revenue(String customerId, double amountPaid) {
            this.customerId = customerId;
            this.amountPaid = amountPaid;
            this.change = 0.0; //bare en default værdi


        }

        public void betalingshistorik(){
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/betalingshistorik.txt", true))){
                //gemmer enkelt betaling
                writer.write(this.toString());
                writer.newLine();
            } catch (IOException e) {
                System.out.println("Fejl ved skrivning: " + e.getMessage());
            }
        }
        //Jeg vil nu lave change med en if og else statement
        // den skal kunne regne ud hvis en kunden betaler med en 500 så skal det tilbagebetales
        // og hvis man betaler for lidt så skal den udskrive ugyldig.
        public void calculateChange(double serviceCost) {
            if (amountPaid >= serviceCost) { //hvis det kunden betaler er større eller lige med servicen
                //så skal change være amountPaid - serviceCost
                change = amountPaid - serviceCost;
            } else {
                change = 0.0; //Eller hvis det betalte ikke er nok skal den ikke beregne noget
                //så bliver harry bare sur.
            }
        }

        //henter kundens id
        public String getCustomerId() {
            return customerId;
        }

        //Hente det betalte beløb
        public double getAmountPaid() {
            return amountPaid;
        }

        //Hente tilbagebetaling hvis der er
        public double getChange() {
            return change;
        }

        @Override
        public String toString() { // Dette er bare en præsentation af vores objekt som er PaymentTwo.
            // Vi kombinere instans variablerne i en String.
            return "Customer ID: " + customerId + ", Amount Paid: " + amountPaid + ", Change: " + change;

        }
    }
    public static void startService() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Velkommen til Delfin");
        System.out.println("Vælg en af de følgende");
        System.out.println("1. Dailypass for Voksne (70 kr.)");
        System.out.println("2. Dailypass for Børn (30 kr.)");
        System.out.println("3. Dailypass for Senior/Studerende (50 kr.)");
        System.out.println("4. Dailypass for Joe Rogan (1 kr.)");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                dailyPass = 70;
                break;
            case 2:
                dailyPass = 30;
                break;
            case 3:
                dailyPass = 50;
                break;
            case 4:
                dailyPass = 1;
                break;
            case 5:
                dailyPass = 0;
                break;
            default:
                System.out.println("Ugyldigt valg");
        }

        System.out.println("Tilføj produkt til kurv");
        System.out.println("1. Badetøj til Mænd (300 kr.)");
        System.out.println("2. Badetøj til Kvinder (800 kr.)");
        System.out.println("3. Badetøj til Børn (1000 kr.)");
        System.out.println("4. Svømmebriller (400 kr.)");
        System.out.println("5. Håndklæde (5 kr.)");
        System.out.println("6. Tast 0 for ingen");

        int addOn = scanner.nextInt();
        double productCost = 0;

        switch (addOn) {
            case 1:
                productCost = 300;
                break;
            case 2:
                productCost = 800;
                break;
            case 3:
                productCost = 1000;
                break;
            case 4:
                productCost = 400;
                break;
            case 5:
                productCost = 5;
                break;
            case 0:
                productCost = 0;
                break;
            default:
                System.out.println("Ugyldigt valg");
                return;

        }
        System.out.println("Indtast Navn: "); //Da det skal være bruge venligt vil deres id være navn
        // vi kan senere hen gøre det til en mail, tele, eller login process, men for nu er det navn
        Scanner scan = new Scanner(System.in);
        String customerId = scan.nextLine();

        System.out.println("Indtast beløb: "); // det vil være i kontant
        double amountPaid = scanner.nextDouble();

        //tid til et payment objekt
        revenue payment = new revenue(customerId, amountPaid);

        //Beregn tilbage betalingen/Kredit. Nu lægger vi service og vare køb sammen.
        payment.calculateChange(dailyPass+productCost);


        System.out.println("Betaling gennemført: ");
        System.out.println(payment);

        if (amountPaid >= (dailyPass+productCost)) {
            System.out.println("Tak for betalingen! Tilbagebetaling: " + payment.getChange() + " kr.");
        } else {
            System.out.println("Beløbet er ikke tilstrækkeligt.");
        }

        payment.betalingshistorik();

        scanner.close();
        }

    public static void main(String[] args) {
        SwimShop swimShop = new SwimShop();

        SwimShop.startService();
    }
    }



