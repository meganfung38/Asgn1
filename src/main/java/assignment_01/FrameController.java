package assignment_01;
import javax.swing.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;


// component: controls all user interaction


public class FrameController {

    // logger
    private static final Logger logger = Logger.getLogger(FrameController.class.getName());

    // interactive components
    private final JFrame mainFrame;
    private final FileAnalyzer fileAnalyzer;
    private final FilePanel filePanel;
    private final CanvasPanel canvasPanel;
    private final StatusBar statusBar;

    // current folder
    private File currFolder;

    public FrameController(
            JFrame mainFrame,
            FileAnalyzer fileAnalyzer,
            FilePanel filePanel,
            CanvasPanel canvasPanel,
            StatusBar statusBar
    ) {

        // save components to fields
        this.mainFrame = mainFrame;
        this.fileAnalyzer = fileAnalyzer;
        this.filePanel = filePanel;
        this.canvasPanel = canvasPanel;
        this.statusBar = statusBar;

        this.filePanel.addFileSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {  // add listener for file selection
                handleFileSelection();
            }
        });

    }

    public void openFolder() {

        // config
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);  // only folders can be selected
        int result = chooser.showOpenDialog(mainFrame);  // open or cancel

        if (result == JFileChooser.APPROVE_OPTION) {  // open

            currFolder = chooser.getSelectedFile();  // selected folder
            statusBar.setStatus("Opened Folder: " +  currFolder.getName());  // update status bar
            logger.info("Opened Folder: " +  currFolder.getAbsolutePath());

            // collect java files
            File[] files = currFolder.listFiles((dir, name) -> name.endsWith(".java"));

            if (files == null || files.length == 0) {  // empty or no java files
                statusBar.setStatus("No .java files found");  // update status bar
                logger.info("No .java files found in " + currFolder.getAbsolutePath());
                filePanel.updateFileList(new String[0]);  // no files to show in scrollable file list
            } else {  // java files exist
                String[] fileNames = new String[files.length];  // array for file names
                for (int i = 0; i < files.length; i++) {  // store file names in array
                    fileNames[i] = files[i].getName();
                }
                filePanel.updateFileList(fileNames);  // add file names to scrollable file list
                analyzeFile(files[0]);  // analyze first file by default
            }

        } else {  // cancel
            statusBar.setStatus("Open Folder Cancelled");  // update status bar
            logger.info("Open Folder Cancelled");
        }

    }

    private void analyzeFile(File file) {

        try {
            statusBar.setStatus("Analyzing " + file.getName());  // update status bar
            logger.info("Analyzing " + file.getName());

            // get metrics to display on CanvasPanel
            int complexity = fileAnalyzer.complexityMetric(file);  // number of line
            int size = fileAnalyzer.sizeMetric(file);  // number of control statements
            int overall = fileAnalyzer.overallMetric(file);  // check for @author/ @version

            // display metrics on CanvasPanel
            canvasPanel.updateMetrics(complexity, size, overall);

            statusBar.setStatus("Analyzed " + file.getName());  // update status bar
            logger.info("Analyzed " + file.getName() + " line count: " + complexity + " control statements: " + size + " overall: " + overall);
        } catch (IOException e) {  // update status if something goes wrong
            statusBar.setStatus("Error analyzing " + file.getName() + ". Error Message: " + e.getMessage());
            logger.log(Level.SEVERE, "Error analyzing " + file.getName(), e);
        }

    }

    public void handleFileSelection() {

        if (currFolder == null) {  // no folder open, cannot analyze
            JOptionPane.showMessageDialog(
                    mainFrame,
                    "Select a folder first.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        } else {  // folder open
            String selectedFileName = filePanel.getSelectedFile();  // get file name
            if (selectedFileName == null) return;  // no file selection
            File selectedFile =  new File(currFolder, selectedFileName);  // get file contents
            analyzeFile(selectedFile);  // analyze file
        }

    }

}
