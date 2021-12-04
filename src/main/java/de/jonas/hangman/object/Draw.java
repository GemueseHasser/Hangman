package de.jonas.hangman.object;

import de.jonas.Hangman;
import de.jonas.hangman.gui.Gui;
import de.jonas.hangman.constant.HangmanElementType;
import org.jetbrains.annotations.NotNull;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/**
 * Mithilfe dieses {@link Draw}, wird das gesamte Spiel auf das {@link Gui} gezeichnet.
 */
@NotNull
public final class Draw extends JLabel {

    //<editor-fold desc="CONSTANTS">

    //<editor-fold desc="hangman rect">
    /** Die X-Koordinate des Vierecks, welches um das Galgenmännchen gezeichnet wird. */
    private static final int HANGMAN_RECT_X = 80;
    /** Die Y-Koordinate des Vierecks, welches um das Galgenmännchen gezeichnet wird. */
    private static final int HANGMAN_RECT_Y = 80;
    /** Die Breite des Vierecks, welches um das Galgenmännchen gezeichnet wird. */
    private static final int HANGMAN_RECT_WIDTH = 200;
    /** Die Höhe des Vierecks, welches um das Galgenmännchen gezeichnet wird. */
    private static final int HANGMAN_RECT_HEIGHT = 250;
    //</editor-fold>

    //<editor-fold desc="false letters">
    /** Die X-Koordinate, ab wo angefangen wird, die falsch erratenen Buchstaben anzuzeigen. */
    private static final int FALSE_LETTERS_X = 350;
    /** Die Y-Koordinate, ab wo angefangen wird, die falsch erratenen Buchstaben anzuzeigen. */
    private static final int FALSE_LETTERS_Y = 100;
    /** Der Abstand zwischen den einzelnen falsch erratenen Buchstaben. */
    private static final int FALSE_LETTER_MARGIN = 30;
    /** DIe Anzahl an falsch erratenen Buchstaben, die in einer Zeile angezeigt werden. */
    private static final int FALSE_LETTERS_PER_LINE = 5;
    //</editor-fold>

    //<editor-fold desc="hangman">
    /** Die Basis X-Koordinate des Galgenmännchens. */
    private static final int HANGMAN_BASE_X = 100;
    /** Die Basis Y-Koordinate des Galgenmännchens. */
    private static final int HANGMAN_BASE_Y = 300;
    //</editor-fold>

    //<editor-fold desc="word">
    /** Die Y-Koordinate des Wortes. */
    private static final int WORD_BASE_Y = 400;
    //</editor-fold>

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
            (super.getWidth() / 2) - (Hangman.getWordHandler().getWidth() / 2),
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

        // draw false letters
        for (int i = 0; i < Hangman.getWordHandler().getFalseLetters().size(); i++) {
            // get current letter
            final char letter = Hangman.getWordHandler().getFalseLetters().get(i);

            // calculate x coordinate of current letter
            final int x = (FALSE_LETTERS_X + i * FALSE_LETTER_MARGIN)
                - ((i / FALSE_LETTERS_PER_LINE) * (FALSE_LETTERS_PER_LINE * FALSE_LETTER_MARGIN));

            // calculate y coordinate of current letter
            final int y = FALSE_LETTERS_Y + ((i / FALSE_LETTERS_PER_LINE) * FALSE_LETTER_MARGIN);

            // draw current letter
            g.drawString(String.valueOf(letter), x, y);
        }

        repaint();
    }
    //</editor-fold>
}
