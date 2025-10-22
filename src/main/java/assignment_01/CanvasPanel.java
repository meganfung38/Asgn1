package assignment_01;
import javax.swing.*;
import java.awt.*;


// component: displays complexity, size, and overall
// location: center


public class CanvasPanel extends JPanel {

    // metrics to display
    private int complexity = 0;  // number of control statements
    private int size = 0;  // number of lines
    private int overall = 0; // 1 = happy, 0 = neutral, -1 = sad

    public CanvasPanel() {

        // configs
        setBackground(Color.white);
        setPreferredSize(new Dimension(700, 450));
        setBorder(BorderFactory.createTitledBorder("File Metrics"));

    }

    public void updateMetrics(int complexity, int size, int overall) {

        // save metrics to fields
        this.complexity = complexity;
        this.size = size;
        this.overall = overall;

        repaint();  // call paintComponent() for redraw

    }

    @Override
    public void paintComponent(Graphics g) {

        // config
        super.paintComponent(g);  // clear old drawings and prepare background
        Graphics2D g2 = (Graphics2D) g;  // draw functionality
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);  // smooth lines
        int height = getHeight() - 80;  // get available height of center canvas
        int maxBarHeight = 350;  // max bar height for metrics
        int pxScale = 3;  // number of px per unit

        // complexity
        g2.setColor(Color.pink);  // pink bar
        int complexityHeight = Math.min(complexity * pxScale, maxBarHeight);  // calculate bar height
        g2.fillRect(150, height - complexityHeight, 100, complexityHeight);  // fill
        g2.setColor(Color.black);  // black text
        g2.drawString("Complexity: " + complexity, 150, height + 20);  // label

        // size
        g2.setColor(Color.green);  // green bar
        int sizeHeight = Math.min(size * pxScale, maxBarHeight);  // calculate bar height
        g2.fillRect(300, height - sizeHeight, 100, sizeHeight);  // fill
        g2.setColor(Color.black);  // black text
        g2.drawString("Size: " + size, 300, height + 20);  // label

        // overall
        // 1 = happy -> contains @author and @version
        // 0 = neutral -> contains either @author or @version
        // -1 = sad -> contains none
        drawMoodFace(g2, height - 150, overall);

    }

    private void drawMoodFace(Graphics2D g2, int height, int overall) {

        // face outline
        g2.setColor(Color.black);
        g2.drawOval(550, height, 100, 100);

        // eyes
        g2.fillOval(575, height + 30, 10, 10);
        g2.fillOval(615, height + 30, 10, 10);

        // mouth using mood
        if (overall > 0) {  // happy
            g2.drawArc(575, height + 50, 50, 25, 180, 180);
        } else if (overall == 0) {  // neutral
            g2.drawLine(580, height + 70, 620, height + 70);
        } else {  // sad
            g2.drawArc(575, height + 60, 50, 25, 0, 180);
        }

        g2.drawString("Overall", 575, height + 125);  // label

    }
}
