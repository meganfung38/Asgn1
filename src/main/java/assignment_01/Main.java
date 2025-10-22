package assignment_01;
import javax.swing.*;


// full program for GUI


public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                // ignore + continue with default look
            }

            // main frame
            MainFrame frame = new MainFrame();

            // frame configs
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setTitle("Assignment 01");
            frame.setSize(1000, 600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

        });

    }

}