import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SwimShop {
    //private static final double dailyPass = 0;
    private static class Vare {
        private String navn;
        private double pris;

        public Vare(String navn, double pris) {
            this.navn = navn;
            this.pris = pris;
        }

        public String getNavn() {
            return navn;
        }

        public double getPris() {
            return pris;
        }
    }
    private static class Revenue {
        final private String customerId;
        final private double amountPaid;
        private double change;
        private List<Vare> kvittering;

        public Revenue(String customerId, double amountPaid, List<Vare> kvittering) {
            this.customerId = customerId;
            this.amountPaid = amountPaid;
            this.change = 0.0;
            this.kvittering = kvittering;
        }

        public void betalingshistorik() {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/TekstFiler/betaling.txt", true))) {
                writer.write(this.toString());
                writer.newLine();
            } catch (IOException e) {
                System.out.println("Fejl ved skrivning: " + e.getMessage());
            }
        }
        public void calculateChange(double serviceCost) {
            if (amountPaid >= serviceCost) {
                change = amountPaid - serviceCost;
            } else {
                change = 0.0;
            }
        }
        public String getCustomerId() {
            return customerId;
        }

        public double getAmountPaid() {
            return amountPaid;
        }

        public double getChange() {
            return change;
        }

        @Override
        public String toString() {
            StringBuilder itemsString = new StringBuilder();
            for (Vare item : kvittering){
                itemsString.append(item.getNavn()).append(" (").append(item.getPris()).append("kr.), ");
            }
            if (itemsString.length() > 0) {
                itemsString.setLength(itemsString.length() - 2);
            }
            return "Customer ID: " + customerId +
                    ", Amount Paid: " + amountPaid +
                    ", Change: " + change +
                    ", Purchased items: " + itemsString.toString();
        }
    }
    public static void startService() {
        Scanner scanner = new Scanner(System.in);
        List<Double> selectedDailyPasses = new ArrayList<>();
        List<Vare> selectedProducts = new ArrayList<>();
        while (true) {
            System.out.println("Velkommen til Delfin");
            System.out.println("Vælg en af de følgende (0 for at stoppe):");
            System.out.println("1. Dailypass for Voksne (70 kr.)");
            System.out.println("2. Dailypass for Børn (30 kr.)");
            System.out.println("3. Dailypass for Senior/Studerende (50 kr.)");
            System.out.println("4. Dailypass for Joe Rogan (1 kr.)");
            System.out.println("0. Færdig med at vælge dailypasses");

            try {

                int choice = scanner.nextInt();

                if (choice == 0) {
                    break;
                }

                double dailyPassPrice = 0;
                switch (choice) {
                    case 1:
                        dailyPassPrice = 70;
                        break;
                    case 2:
                        dailyPassPrice = 30;
                        break;
                    case 3:
                        dailyPassPrice = 50;
                        break;
                    case 4:
                        dailyPassPrice = 1;
                        break;
                    default:
                        System.out.println("Ugyldigt valg");
                        continue;
                }
                selectedDailyPasses.add(dailyPassPrice);
            } catch (Exception e) {
                System.out.println("Der opstod en fejl: " + e.getMessage());
                scanner.nextLine(); //rydder buffer så den ikke bliver ved mere
            }

        }

        // Produkt valg
        while (true) {
            System.out.println("Tilføj produkt til kurv (0 for at stoppe):");
            System.out.println("1. Badetøj til Mænd (300 kr.)");
            System.out.println("2. Badetøj til Kvinder (800 kr.)");
            System.out.println("3. Badetøj til Børn (1000 kr.)");
            System.out.println("4. Svømmebriller (400 kr.)");
            System.out.println("5. Håndklæde (5 kr.)");
            System.out.println("0. Færdig med at vælge produkter");

            try {


                int addOn = scanner.nextInt();

                if (addOn == 0) {
                    break;
                }

                Vare selectedProduct = null;
                switch (addOn) {
                    case 1:
                        selectedProduct = new Vare("Badetøj til Mænd", 300);
                        break;
                    case 2:
                        selectedProduct = new Vare("Badetøj til Kvinder", 800);
                        break;
                    case 3:
                        selectedProduct = new Vare("Badetøj til Børn", 1000);
                        break;
                    case 4:
                        selectedProduct = new Vare("Svømmebriller", 400);
                        break;
                    case 5:
                        selectedProduct = new Vare("Håndklæde", 5);
                        break;
                    default:
                        System.out.println("Ugyldigt valg");
                        continue;
                }
                selectedProducts.add(selectedProduct);
            } catch (Exception e) {
                System.out.println("Der opstod en fejl: " + e.getMessage());
                scanner.next();
            }
        }


        // Kvitteringen
        double totalDailyPassPrice = selectedDailyPasses.stream().mapToDouble(Double::doubleValue).sum();
        double totalProductPrice = selectedProducts.stream().mapToDouble(Vare::getPris).sum();

        System.out.println("Valgte dailypasses:");
        selectedDailyPasses.forEach(pass -> System.out.println(pass + " kr."));

        System.out.println("Valgte produkter:");
        selectedProducts.forEach(product -> System.out.println(product.getNavn() + " (" + product.getPris() + " kr.)"));

        System.out.println("Samlet pris for dailypasses: " + totalDailyPassPrice + " kr.");
        System.out.println("Samlet pris for produkter: " + totalProductPrice + " kr.");
        double totalPrice = totalDailyPassPrice + totalProductPrice;
        System.out.println("Total pris: " + totalPrice + " kr.");

        // Navn input
        scanner.nextLine(); //  Rydder buffer
        String customerId;
        while (true) {
            System.out.println("Indtast Navn:");
            customerId = scanner.nextLine();
            if (customerId.isBlank() || !customerId.matches("[a-zA-ZæøåÆØÅ\\- ]+") || customerId.equals("-") || customerId.split("-").length>2) {
                System.out.println("Ugyldigt navn");
                System.out.println("Indtast venligst et navn");
            } else {
                break;
            }
        }

        // Betaling
        System.out.println("Indtast beløb som bliver betalt nu:");
        double amountPaid = scanner.nextDouble();

        // Opret payment objekt
        Revenue payment = new Revenue(customerId, amountPaid, selectedProducts);
        payment.calculateChange(totalPrice);

        System.out.println("Betaling gennemført: ");
        System.out.println(payment);

        if (amountPaid >= totalPrice) {
            System.out.println("Tak for betalingen " + customerId + "!" + " Tilbagebetaling: " + payment.getChange() + " kr.");
        } else {
            System.out.println("AFVIST");
        }

        payment.betalingshistorik();
    }

    public static void main(String[] args) {
        startService();
    }
}