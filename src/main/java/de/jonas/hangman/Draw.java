package de.jonas.hangman;

import org.jetbrains.annotations.NotNull;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/**
 * Mithilfe dieses {@link Draw}, wird das gesamte Spiel auf das {@link Gui} gezeichnet.
 */
@NotNull
public final class Draw extends JLabel {

    //<editor-fold desc="CONSTANTS">
    /** Die Basis X-Koordinate des Galgenmännchens. */
    private static final int HANGMAN_BASE_X = 200;
    /** Die Basis Y-Koordinate des Galgenmännchens. */
    private static final int HANGMAN_BASE_Y = 350;
    //</editor-fold>


    //<editor-fold desc="implementation">
    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);

        final Graphics2D g2d = (Graphics2D) g;

        // define render quality
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // draw background
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, super.getWidth(), super.getHeight());

        // set default color
        g.setColor(Color.BLACK);

        // draw current hangman
        for (@NotNull final HangmanElementType element : HangmanElementType.values()) {
            if (!element.isActive()) continue;

            element.draw(
                HANGMAN_BASE_X,
                HANGMAN_BASE_Y,
                g
            );
        }

        repaint();
    }
    //</editor-fold>
}
