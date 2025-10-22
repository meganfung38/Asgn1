package assignment_01;
import javax.swing.*;
import java.awt.*;


// main JFrame interface


public class MainFrame extends JFrame {

    public MainFrame() {

        // JFrame config
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(800, 600));

        // components
        FilePanel filePanel = new FilePanel();
        CanvasPanel canvasPanel = new CanvasPanel();
        FileAnalyzer fileAnalyzer = new FileAnalyzer();  // calculates complexity, size, overall metrics
        StatusBar statusBar = new StatusBar();
        FrameController frameController = new FrameController(
                this,
                fileAnalyzer,
                filePanel,
                canvasPanel,
                statusBar
        );  // controls all user interaction with JFrame interface


        // add components to JFrame interface
        setJMenuBar(new FrameMenu(frameController)); // top: file, action, help options
        add(filePanel, BorderLayout.WEST); // left: scrollable file list
        add(canvasPanel, BorderLayout.CENTER); // center: visualizes complexity, size, overall
        add(statusBar, BorderLayout.SOUTH); // bottom: short messages

    }
}