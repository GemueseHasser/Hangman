package de.jonas;

import de.jonas.hangman.Gui;
import org.jetbrains.annotations.NotNull;

@NotNull
public final class Hangman {

    public static void main(@NotNull final String[] args) {
        final Gui gui = new Gui();
        gui.open();
    }

}
