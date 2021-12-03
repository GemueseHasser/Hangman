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

    //<editor-fold desc="implementation">
    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);

        final Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(Color.LIGHT_GRAY);

        g.fillRect(0, 0, super.getWidth(), super.getHeight());
    }
    //</editor-fold>
}
