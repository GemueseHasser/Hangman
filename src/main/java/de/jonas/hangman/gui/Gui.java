package de.jonas.hangman.gui;

import de.jonas.Hangman;
import de.jonas.hangman.object.Draw;
import org.jetbrains.annotations.NotNull;

import javax.swing.JFrame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Ein {@link Gui} ist das Fenster, auf dem das Spiel angezeigt wird. Das gesamte Spiel wird allerdings mithilfe des
 * {@link Draw} auf dieses Fenster gezeichnet.
 */
@NotNull
public final class Gui extends JFrame implements KeyListener {

    //<editor-fold desc="CONSTANTS">
    /** Der Titel dieses Fensters. */
    @NotNull
    private static final String TITLE = "Galgenmännchen - by Jonas";
    /** Die Breite dieses Fensters. */
    private static final int WIDTH = 600;
    /** Die Höhe dieses Fensters. */
    private static final int HEIGHT = 500;
    //</editor-fold>


    //<editor-fold desc="CONSTRUCTORS">

    /**
     * Erzeugt eine neue und vollständig unabhängige Instanz eines {@link Gui}. Ein {@link Gui} ist das Fenster, auf dem
     * das Spiel angezeigt wird. Das gesamte Spiel wird allerdings mithilfe des {@link Draw} auf dieses Fenster
     * gezeichnet. Jedoch wird das Fenster hier nur instanziiert, aber noch nicht geöffnet.
     */
    public Gui() {
        super.setTitle(TITLE);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setBounds(0, 0, WIDTH, HEIGHT);
        super.setLocationRelativeTo(null);
        super.setResizable(false);
        super.addKeyListener(this);

        final Draw draw = new Draw();
        draw.setBounds(0, 0, WIDTH, HEIGHT);
        draw.setVisible(true);

        super.add(draw);
    }
    //</editor-fold>


    /**
     * Öffnet das Fenster.
     */
    public void open() {
        super.setVisible(true);
    }

    @Override
    public void keyTyped(final KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(final KeyEvent keyEvent) {
        Hangman.getWordHandler().press(keyEvent.getKeyChar());
    }

    @Override
    public void keyReleased(final KeyEvent keyEvent) {

    }
}
