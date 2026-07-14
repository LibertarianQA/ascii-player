package asciiplayer.ui;

import asciiplayer.ascii.AsciiFrame;

import javax.swing.*;
import java.awt.*;

public class AsciiWindow {

    private final JFrame frame;
    private final AsciiPanel panel;

    public AsciiWindow() {

        frame = new JFrame("ASCII Media Player");

        panel = new AsciiPanel();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.CENTER);

        frame.setSize(1600, 900);
        frame.setLocationRelativeTo(null);

        frame.getContentPane().setBackground(Color.BLACK);

        frame.setVisible(true);
    }

    public void render(AsciiFrame frame) {

        SwingUtilities.invokeLater(() -> panel.render(frame));
    }

    public boolean isOpen() {
        return frame.isDisplayable();
    }

    public void close() {
        SwingUtilities.invokeLater(frame::dispose);
    }
}