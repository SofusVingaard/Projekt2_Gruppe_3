import java.util.Scanner;

public class SwimShop {
    public void startDaily() {
        Scanner scanner = new Scanner(System.in);
//Priser for dailypass
        System.out.println("Velkommen til Delfin");
        System.out.println("Vælg en af de følgende");
        System.out.println("1. Dailypass for Voksne (70 kr. ) ");
        System.out.println("2. Dailypass for Børn (30 kr.)");
        System.out.println("3. Dailypass for Senior/Studerende (50 kr. )");
        System.out.println("4. Dailypass for Joe Rogan (1 kr. )");

        int choice = scanner.nextInt();
        double dailyPass = 0;

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
            default:
                System.out.println("Ugyldigt");
                return;
        }
        public static void startAddService() {
            Scanner scanner2 = new Scanner(System.in);
//
//addon produkter

            System.out.println("Tilføj produkt til kurv");
            System.out.println("1. Badetøj til Mænd (300 kr. )");
            System.out.println("2. Badetøj til Kvinder (800 kr. )");
            System.out.println("3. Badetøj til Børn (1000 kr. )");
            System.out.println("4. Svømmebriller (400 kr. )");
            System.out.println("5. Håndklæde (5 kr. )");
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
                    System.out.println("Ugyldigt");
            }
//sammenlæg priser for både dailypass og addon
            double totalCost = dailyPass + productCost;
            System.out.println("Totalprisen " + totalCost + "kr.");
            scanner.close();
            scanner2.close();

        }




        }
    }



