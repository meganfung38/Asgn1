package assignment_01;
import javax.swing.*;
import java.awt.*;


// component: menu bar containing file, action, and help
// location: top


public class FrameMenu extends JMenuBar {

    public FrameMenu(FrameController frameController) {

        // menu item: file
        JMenu file = new JMenu("File");
        JMenuItem openItem = new JMenuItem("Open");  // open files
        openItem.addActionListener(e -> frameController.openFolder());  // call controller on click
        file.add(openItem);  // add as toggle

        // menu item: action
        JMenu action = new JMenu("Action");

        // menu item: help
        JMenu help = new JMenu("Help");
        JMenuItem about = new JMenuItem("About");  // about page
        about.addActionListener(e ->
                JOptionPane.showMessageDialog(
                        null,
                        "Assignment 01\nCreated by Megan Fung\nCSC305",
                        "About",
                        JOptionPane.INFORMATION_MESSAGE
                ));  // create modal for about
        help.add(about);  // add as toggle

        // add all menu items to menu bar
        add(file);
        add(action);
        add(help);

    }
}
