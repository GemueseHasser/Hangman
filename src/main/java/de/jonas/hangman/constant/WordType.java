package de.jonas.hangman.constant;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

/**
 * Ein {@link WordType} spiegelt ein Wort wieder, welches in diesem Spiel nach und nach erraten werden muss.
 */
@NotNull
public enum WordType {

    //<editor-fold desc="VALUES">
    /** Der {@link WordType} für das Wort 'Baum'. */
    TREE(
        "Baum"
    );
    //</editor-fold>


    //<editor-fold desc="LOCAL FIELDS">
    /** Das Wort des jeweiligen {@link WordType}. */
    @Getter
    private final String word;
    //</editor-fold>


    //<editor-fold desc="CONSTRUCTORS">

    /**
     * Erzeugt mithilfe eines Wortes, einen neuen und vollständig unabhängigen {@link WordType}. Ein {@link WordType}
     * spiegelt ein Wort wieder, welches in diesem Spiel nach und nach erraten werden muss.
     *
     * @param word Das Wort des jeweiligen {@link WordType}.
     */
    WordType(
        @NotNull final String word
    ) {
        this.word = word;
    }
    //</editor-fold>


    /**
     * Berechnet, ob das angegebene Zeichen sich an einer bestimmten Stelle in dem jeweiligen Wort befindet. Wenn ja,
     * wird die Position des Buchstaben (in Form einer Zahl zurückgegeben) und wenn nicht, wird einfach {@code null}
     * zurückgegeben, was impliziert, dass dieses Zeichen nicht im jeweiligen Wort vorhanden ist.
     *
     * @param digit Das Zeichen, welches auf Übereinstimmung mit einem Zeichen aus dem jeweiligen Wort geprüft wird.
     *
     * @return Wenn sich das angegebene Zeichen an einer bestimmten Stelle im jeweiligen Wort befindet, wird die
     *     Position (in Form einer Zahl) zurückgegeben, ansonsten {@code null}, was impliziert, dass dieses Zeichen
     *     nicht im jeweiligen Wort vorhanden ist.
     */
    public Integer getPosition(final char digit) {
        for (int i = 0; i < this.word.length(); i++) {
            if (Character.toLowerCase(this.word.charAt(i)) != Character.toLowerCase(digit)) continue;

            return i;
        }

        return null;
    }

}
