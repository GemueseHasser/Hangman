package de.jonas.hangman.constant;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

/**
 * Ein {@link WordType} spiegelt ein Wort wieder, welches in diesem Spiel nach und nach erraten werden muss.
 */
@NotNull
public enum WordType {

    //<editor-fold desc="VALUES">
    /** Der {@link WordType} für das Wort 'Baum'. */
    TREE(
        "Baum"
    ),
    CONSTRUCTION_ZONE(
        "Baustelle mit"
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
     * Berechnet, ob das angegebene Zeichen sich an einer bestimmten Stelle oder mehreren bestimmten Stellen in dem
     * jeweiligen Wort befindet. Wenn ja, wird die Position (oder die Positionen) des Buchstaben (in Form eines {@link
     * Set}, welches alle Positionen beinhaltet) zurückgegeben und wenn nicht, wird ein leeres Set zurückgegeben, was
     * impliziert, dass dieses Zeichen nicht im jeweiligen Wort vorhanden ist.
     *
     * @param digit Das Zeichen, welches auf Übereinstimmungen mit einem Zeichen aus dem jeweiligen Wort geprüft wird.
     *
     * @return Wenn sich das angegebene Zeichen an einer bestimmten Stelle oder mehreren bestimmten Stellen im
     *     jeweiligen Wort befindet, wird die Position (in Form eines {@link Set}, welches alle Positionen beinhaltet)
     *     zurückgegeben, ansonsten ein leeres Set, was impliziert, dass dieses Zeichen nicht im jeweiligen Wort
     *     vorhanden ist.
     */
    @NotNull
    public Set<Integer> getPositions(final char digit) {
        final Set<Integer> positions = new HashSet<>();

        for (int i = 0; i < this.word.length(); i++) {
            if (Character.toLowerCase(this.word.charAt(i)) != Character.toLowerCase(digit)) continue;

            positions.add(i);
        }

        return positions;
    }

}
