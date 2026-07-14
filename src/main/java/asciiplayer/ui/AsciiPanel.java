package asciiplayer.ui;

import asciiplayer.ascii.AsciiFrame;

import javax.swing.*;
import java.awt.*;

public class AsciiPanel extends JPanel {

    private volatile AsciiFrame frame;

    public AsciiPanel() {

        setBackground(Color.BLACK);
        setForeground(Color.WHITE);
        setDoubleBuffered(true);
    }

    public void render(AsciiFrame frame) {

        this.frame = frame;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        if (frame == null) {
            return;
        }

        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(getForeground());

        int cols = frame.getWidth();
        int rows = frame.getHeight();

        int fontSize = Math.max(
                4,
                Math.min(
                        getWidth() / cols,
                        getHeight() / rows
                )
        );

        Font dynamicFont = new Font(Font.MONOSPACED, Font.PLAIN, fontSize);
        g2.setFont(dynamicFont);

        FontMetrics metrics = g2.getFontMetrics();

        int charWidth = metrics.charWidth('W');
        int lineHeight = metrics.getHeight();

        int imageWidth = cols * charWidth;
        int imageHeight = rows * lineHeight;

        int startX = Math.max(0, (getWidth() - imageWidth) / 2);
        int startY = Math.max(lineHeight, (getHeight() - imageHeight) / 2 + lineHeight);

        char[] chars = frame.getChars();

        for (int row = 0; row < rows; row++) {

            g2.drawChars(
                    chars,
                    row * cols,
                    cols,
                    startX,
                    startY + row * lineHeight
            );
        }
    }
}