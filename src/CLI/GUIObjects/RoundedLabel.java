package CLI.GUIObjects;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class RoundedLabel extends JLabel implements Serializable {
    private static final int ARC_WIDTH = 30;
    private static final int ARC_HEIGHT = 30;
    private static final float ALPHA = 0.7f;  // Opacità del fondo (70% opaco)

    /**
     * costruttore del roundedLabel
     * @param text
     */
    public RoundedLabel(String text) {
        super(text);
        setOpaque(false);  // Render the label non-opaque for custom painting
    }

    /**
     * paint del rettangolo
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, ALPHA));
        g2.setColor(Color.WHITE);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), ARC_WIDTH, ARC_HEIGHT);
        g2.dispose();
        super.paintComponent(g);
    }

    /**
     * paint del border
     * @param g  the <code>Graphics</code> context in which to paint
     */
    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(getForeground());
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, ARC_WIDTH, ARC_HEIGHT);
        g2.dispose();
    }
}
