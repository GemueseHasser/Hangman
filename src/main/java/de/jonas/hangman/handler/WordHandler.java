package de.jonas.hangman.handler;

import de.jonas.hangman.constant.HangmanElementType;
import de.jonas.hangman.constant.WordType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Mithilfe eines {@link WordHandler} lässt sich ein bestimmter {@link WordType} verarbeiten. Bei der Instanziierung des
 * {@link WordHandler} wird ein zufälliger {@link WordType} ausgewählt, auf dessen Grundlage dieser Handler dann
 * arbeitet. Der {@link WordType} kann beliebig oft neu generiert werden.
 */
public final class WordHandler {

    //<editor-fold desc="CONSTANTS">
    /** Der {@link Random}, mit dem der zufällige {@link WordType} kalkuliert wird. */
    private static final Random RANDOM = new Random();
    /** Die Schriftart, mit der ein Wort geschrieben wird. */
    private static final Font WORD_FONT = new Font("Arial", Font.BOLD, 20);
    //</editor-fold>


    //<editor-fold desc="LOCAL FIELDS">
    /** Eine Liste aller gefundenen Buchstaben bzw dessen Plätze im Wort. */
    private final List<Integer> founded = new ArrayList<>();
    /** Der aktuelle {@link WordType}, auf welchem dieser {@link WordHandler} basiert. */
    private WordType wordType;
    //</editor-fold>


    //<editor-fold desc="CONSTRUCTORS">

    /**
     * Erzeugt eine neue und vollständig unabhängige Instanz eines {@link WordHandler}. Mithilfe eines {@link
     * WordHandler} lässt sich ein bestimmter {@link WordType} verarbeiten. Bei der Instanziierung des {@link
     * WordHandler} wird ein zufälliger {@link WordType} ausgewählt, auf dessen Grundlage dieser Handler dann arbeitet.
     * Der {@link WordType} kann beliebig oft neu generiert werden.
     */
    public WordHandler() {
        generateWordType();
    }
    //</editor-fold>


    /**
     * Generiert einen neuen {@link WordType} bzw schafft diesem {@link WordHandler} eine neue Grundlage.
     */
    public void generateWordType() {
        this.founded.clear();
        this.wordType = Arrays.asList(WordType.values()).get(RANDOM.nextInt(WordType.values().length));
    }

    /**
     * Drückt einen bestimmten Buchstaben. Wenn dieser Buchstabe in dem Wort vorkommt, wird die Position des Buchstaben
     * den gefundenen Plätzen hinzugefügt und ansonsten wird das Galgenmännchen erweitert um ein weiteres Element.
     *
     * @param digit Der Buchstabe, welcher überprüft bzw. gedrückt wird.
     */
    public void press(final char digit) {
        final Integer position = this.wordType.getPosition(digit);

        // check if char is preset in word
        if (position == null) {
            // add hangman element
            HangmanElementType.activateNextElement();
            return;
        }

        // found
        founded.add(position);
    }

    /**
     * Zeichnet das gesamte Wort, mit allen freien Plätzen und schon erratenen Buchstaben, mithilfe eines {@link
     * Graphics Graphics-Objektes} auf der Grundlage eines Basis-Punktes (X- und Y-Koordinaten).
     *
     * @param baseX Die X-Koordinate des Basis-Punktes.
     * @param baseY Die Y-Koordinate des Basis-Punktes.
     * @param g     Das {@link Graphics Graphics-Objekt}, welches zum Zeichnen genutzt wird.
     */
    public void draw(
        @Range(from = 0, to = Integer.MAX_VALUE) final int baseX,
        @Range(from = 0, to = Integer.MAX_VALUE) final int baseY,
        @NotNull final Graphics g
    ) {
        g.setFont(WORD_FONT);

        for (int i = 0; i < this.wordType.getWord().length(); i++) {
            final int x = baseX + i * 30;

            if (founded.contains(i)) {
                g.drawString(String.valueOf(wordType.getWord().charAt(i)), x, baseY);
                continue;
            }

            g.drawLine(x, baseY, x + 25, baseY);
        }
    }

}
