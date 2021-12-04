package de.jonas.hangman.object;

import de.jonas.Hangman;
import de.jonas.hangman.gui.Gui;
import de.jonas.hangman.constant.HangmanElementType;
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
    private static final int HANGMAN_RECT_X = 80;
    private static final int HANGMAN_RECT_Y = 80;
    private static final int HANGMAN_RECT_WIDTH = 200;
    private static final int HANGMAN_RECT_HEIGHT = 250;
    /** Die Basis X-Koordinate des Galgenmännchens. */
    private static final int HANGMAN_BASE_X = 100;
    /** Die Basis Y-Koordinate des Galgenmännchens. */
    private static final int HANGMAN_BASE_Y = 300;
    /** Die X-Koordinate des Wortes. */
    private static final int WORD_BASE_X = 240;
    /** Die Y-Koordinate des Wortes. */
    private static final int WORD_BASE_Y = 400;
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

        // draw hangman surrounding rect
        g.drawRect(
            HANGMAN_RECT_X,
            HANGMAN_RECT_Y,
            HANGMAN_RECT_WIDTH,
            HANGMAN_RECT_HEIGHT
        );

        // draw current word
        Hangman.getWordHandler().draw(
            WORD_BASE_X,
            WORD_BASE_Y,
            g
        );

        // set hangman color
        g.setColor(Color.RED);

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
