package assignment_01;
import javax.swing.*;
import java.awt.*;


// component: displays status messages
// location: bottom


public class StatusBar extends JPanel {

    private final JLabel statusLabel;  // declare globally

    public StatusBar() {

        // config
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEtchedBorder());

        // status label has default ready message
        statusLabel = new JLabel("Ready");

        // add status label to status bar
        add(statusLabel, BorderLayout.WEST);

    }

    public void setStatus(String message) {

        statusLabel.setText(message);  // set to specified message

    }

}
