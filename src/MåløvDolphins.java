import javax.swing.*;
import java.awt.*;

public class MåløvDolphins {
    public static void main(String[] args) {
        // Oprette JFrame vindue
        JFrame frame = new JFrame("Billede Display");

        // Læs billede fra filsystemet
        ImageIcon imageIcon = new ImageIcon("src/Måløv dolphins.jpg"); // Sørg for at angive den korrekte sti

        // Oprette JLabel og tilføje billedet
        JLabel label = new JLabel(imageIcon);

        // Indstil layout for JFrame
        frame.setLayout(new BorderLayout());
        frame.add(label, BorderLayout.CENTER);

        // Indstil vindue egenskaber
        frame.setSize(1800, 4000); // Sæt størrelse på vinduet
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); // Luk applikationen når vinduet lukkes
        frame.setVisible(true); // Gør vinduet synligt
    }
}
