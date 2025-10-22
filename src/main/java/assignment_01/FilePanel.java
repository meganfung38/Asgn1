package assignment_01;
import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;


// component: scrollable file list for file names
// location: left


public class FilePanel extends JPanel {

    private final JList<String> fileList;  // declare globally

    public FilePanel() {

        // configs
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Files List"));

        // scrollable file list
        JScrollPane scrollFileList = new JScrollPane(
                fileList = new JList<>(new DefaultListModel<>())
        );

        // add file list to panel
        add(scrollFileList, BorderLayout.CENTER);

    }

    public void updateFileList(String[] filenames) {

        // configs
        DefaultListModel<String> model = (DefaultListModel<String>) fileList.getModel();
        model.clear();

        // add filenames for selected folder
        for (String filename : filenames) {
            model.addElement(filename);
        }

    }

    public void addFileSelectionListener(ListSelectionListener l) {

        fileList.addListSelectionListener(l);  // give listener to controller for file selection

    }

    public String getSelectedFile() {

        return fileList.getSelectedValue();  // get selected file name for controller

    }

}
