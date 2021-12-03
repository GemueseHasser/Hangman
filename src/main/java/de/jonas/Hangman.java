package de.jonas;

import de.jonas.hangman.Gui;
import org.jetbrains.annotations.NotNull;

/**
 * <p>Die Haupt- und Main-Klasse der Anwendung. In dieser Klasse befindet sich die Main-Methode, die als aller erstes
 * von der JRE aufgerufen wird.</p>
 * <p>Von dieser Klasse aus wird die gesamte Anwendung initialisiert.</p>
 */
@NotNull
public final class Hangman {

    //<editor-fold desc="main">

    /**
     * Die Main-Methode der Anwendung, die als aller erstes, vor allen anderen Methoden, von der JRE aufgerufen wird.
     * Mithilfe dieser Methode wird die gesamte Anwendung initialisiert bzw. gestartet.
     *
     * @param args Die Argumente, die von der JRE übergeben wurden.
     */
    public static void main(@NotNull final String[] args) {
        final Gui gui = new Gui();
        gui.open();
    }
    //</editor-fold>

}
