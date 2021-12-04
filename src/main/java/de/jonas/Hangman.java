package de.jonas;

import de.jonas.hangman.constant.HangmanElementType;
import de.jonas.hangman.gui.Gui;
import de.jonas.hangman.handler.WordHandler;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

/**
 * <p>Die Haupt- und Main-Klasse der Anwendung. In dieser Klasse befindet sich die Main-Methode, die als aller erstes
 * von der JRE aufgerufen wird.</p>
 * <p>Von dieser Klasse aus wird die gesamte Anwendung initialisiert.</p>
 */
@NotNull
public final class Hangman {

    //<editor-fold desc="STATIC FIELDS">
    /** Der {@link WordHandler}, womit ein Wort generiert wird und angezeigt werden kann. */
    @Getter
    private static WordHandler wordHandler;
    //</editor-fold>


    //<editor-fold desc="main">

    /**
     * Die Main-Methode der Anwendung, die als aller erstes, vor allen anderen Methoden, von der JRE aufgerufen wird.
     * Mithilfe dieser Methode wird die gesamte Anwendung initialisiert bzw. gestartet.
     *
     * @param args Die Argumente, die von der JRE übergeben wurden.
     */
    public static void main(@NotNull final String[] args) {
        // declare word handler
        wordHandler = new WordHandler();

        // initialize and open gui
        final Gui gui = new Gui();
        gui.open();
    }
    //</editor-fold>


    /**
     * Setzt das Spiel zurück.
     */
    public static void resetGame() {
        // regenerate word type
        wordHandler.generateWordType();

        // reset hangman
        HangmanElementType.deactivateAll();
    }

}
