package de.jonas.hangman.handler;

import de.jonas.Hangman;
import de.jonas.hangman.constant.HangmanElementType;
import de.jonas.hangman.constant.WordType;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Mithilfe eines {@link WordHandler} lässt sich ein bestimmter {@link WordType} verarbeiten. Bei der Instanziierung des
 * {@link WordHandler} wird ein zufälliger {@link WordType} ausgewählt, auf dessen Grundlage dieser Handler dann
 * arbeitet. Der {@link WordType} kann beliebig oft neu generiert werden.
 */
@NotNull
public final class WordHandler {

    //<editor-fold desc="CONSTANTS">
    /** Der {@link Random}, mit dem der zufällige {@link WordType} kalkuliert wird. */
    @NotNull
    private static final Random RANDOM = new Random();
    /** Die Schriftart, mit der ein Wort geschrieben wird. */
    @NotNull
    private static final Font WORD_FONT = new Font("Arial", Font.BOLD, 20);
    /** Die Größe (Breite) einer Linie, die bei einem noch nicht erratenen Buchstaben angezeigt wird. */
    private static final int LETTER_LINE_SIZE = 25;
    //</editor-fold>


    //<editor-fold desc="LOCAL FIELDS">
    /** Eine Liste aller gefundenen Buchstaben bzw dessen Plätze im Wort. */
    @NotNull
    private final List<Integer> founded = new ArrayList<>();
    /** Eine Liste, die alle falschen (schon erratenen) Buchstaben enthält. */
    @Getter
    @NotNull
    private final List<Character> falseLetters = new ArrayList<>();
    /** Eine Liste, die alle {@link WordType Typen} beinhaltet, die schon genutzt wurden. */
    @NotNull
    private final List<WordType> usedWordTypes = new ArrayList<>();
    /** Der aktuelle {@link WordType}, auf welchem dieser {@link WordHandler} basiert. */
    @Getter
    @Nullable
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
        // clear lists
        this.founded.clear();
        this.falseLetters.clear();

        // get available word types
        final List<WordType> availableWordTypes = Arrays
            .stream(WordType.values())
            .filter(type -> !usedWordTypes.contains(type))
            .collect(Collectors.toList());

        // check if all word types are used
        if (availableWordTypes.isEmpty()) {
            // mark all types as unused
            this.usedWordTypes.clear();

            // regenerate word type
            generateWordType();
            return;
        }

        // generate word type
        final WordType generatedWordType = availableWordTypes.get(RANDOM.nextInt(availableWordTypes.size()));

        // mark new word type as used
        this.usedWordTypes.add(generatedWordType);

        // set word type
        this.wordType = generatedWordType;
    }

    /**
     * Überprüft, ob das aktuelle Wort vollständig erraten wurde.
     *
     * @return Wenn das aktuelle Wort vollständig erraten wurde, {@code true}, ansonsten {@code false}.
     */
    public boolean isWordFull() {
        assert this.wordType != null;
        return this.founded.size() == this.wordType.getWord().replaceAll("\\s", "").length();
    }

    /**
     * Drückt einen bestimmten Buchstaben. Wenn dieser Buchstabe in dem Wort vorkommt, wird die Position des Buchstaben
     * den gefundenen Plätzen hinzugefügt und ansonsten wird das Galgenmännchen erweitert um ein weiteres Element.
     *
     * @param digit Der Buchstabe, welcher überprüft bzw. gedrückt wird.
     */
    public void press(final char digit) {
        // check if the current char is a letter or if the current char is already clicked
        if (!Character.isLetter(digit) || this.falseLetters.contains(digit)) return;

        assert this.wordType != null;
        final Set<Integer> positions = this.wordType.getPositions(digit);

        // check if letter is already found
        for (@Range(from = 0, to = Integer.MAX_VALUE) final int position : positions) {
            if (this.founded.contains(position)) return;
        }

        // check if current word is full
        if (isWordFull()) return;

        // check if char is preset in word
        if (positions.isEmpty()) {
            // add current digit to false letters
            this.falseLetters.add(digit);

            // append hangman element
            HangmanElementType.activateNextElement();

            // check if player has lost
            if (HangmanElementType.allActive()) {
                final int playOption = JOptionPane.showConfirmDialog(
                    null,
                    "Du hast leider verloren! \nMöchtest du erneut spielen?",
                    "Schade!",
                    JOptionPane.YES_NO_OPTION
                );

                // check if player wants to play again
                if (playOption == JOptionPane.YES_OPTION) {
                    Hangman.resetGame();
                    return;
                }

                // close game
                System.exit(0);
            }
            return;
        }

        // found
        founded.addAll(positions);

        // check if player has won
        if (isWordFull()) {
            final int playOption = JOptionPane.showConfirmDialog(
                null,
                "Du has gewonnen! \nMöchtest du erneut spielen?",
                "Glückwunsch!",
                JOptionPane.YES_NO_OPTION
            );

            // check if player wants to play again
            if (playOption == JOptionPane.YES_OPTION) {
                Hangman.resetGame();
                return;
            }

            // close game
            System.exit(0);
        }
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

        for (int i = 0; i < Objects.requireNonNull(this.wordType).getWord().length(); i++) {
            final int x = baseX + i * 30;

            if (founded.contains(i)) {
                g.drawString(String.valueOf(wordType.getWord().charAt(i)), x, baseY);
                continue;
            }

            if (wordType.getWord().charAt(i) == ' ') continue;

            g.drawLine(x, baseY, x + LETTER_LINE_SIZE, baseY);
        }
    }

    /**
     * Berechnet die Breite des aktuellen Wortes.
     *
     * @return Die Breite des aktuellen Wortes.
     */
    public int getWidth() {
        assert this.wordType != null;
        return this.wordType.getWord().length() * LETTER_LINE_SIZE;
    }

}
