package Graph.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RoundedButton extends JButton {
    private Color hoverBackgroundColor;
    private Color normalBackgroundColor;

    public RoundedButton(String text) {
        super(text);
        normalBackgroundColor = new Color(39, 168, 154);
        hoverBackgroundColor = new Color(255, 255, 255, 230);
        setContentAreaFilled(false);
        setOpaque(false);
        setFocusPainted(false);
        setFont(new Font("Aharoni", Font.BOLD, 25));
        setForeground(Color.BLACK);

        setPreferredSize(new Dimension(100, 30));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(hoverBackgroundColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(normalBackgroundColor);
            }
        });
        setBackground(normalBackgroundColor);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30); // Bo góc
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 30, 30);
        g2.dispose();
    }
}
