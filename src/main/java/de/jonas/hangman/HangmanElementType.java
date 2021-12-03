package de.jonas.hangman;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import java.awt.Graphics;
import java.util.Arrays;

/**
 * Ein {@link HangmanElementType} ist ein bestimmtes Element (ein Bestandteil) des gesamten visuellen Galgenmännchens.
 */
public enum HangmanElementType {

    //<editor-fold desc="VALUES">
    /** Das erste {@link HangmanElementType Element} des Galgens. */
    GALLOWS_FIRST(
        0,
        0,
        30,
        -50
    ),
    /** Das zweite {@link HangmanElementType Element} des Galgens. */
    GALLOWS_SECOND(
        40,
        0,
        30,
        -50
    ),
    /** Das dritte {@link HangmanElementType Element} des Galgens. */
    GALLOWS_THIRD(
        30,
        -50,
        30,
        -200
    ),
    /** Das vierte {@link HangmanElementType Element} des Galgens. */
    GALLOWS_FOURTH(
        30,
        -200,
        110,
        -200
    ),
    /** Das fünfte {@link HangmanElementType Element} des Galgens. */
    GALLOWS_FIVES(
        110,
        -200,
        110,
        -170
    ),
    /** Der {@link HangmanElementType Kopf} des Galgenmännchens. */
    HANGMAN_HAT(
        95,
        -170,
        30,
        Integer.MIN_VALUE
    ),
    /** Der {@link HangmanElementType Körper} des Galgenmännchens. */
    HANGMAN_BODY(
        110,
        -140,
        110,
        -90
    ),
    /** Der {@link HangmanElementType linke Arm} des Galgenmännchens. */
    HANGMAN_LEFT_ARM(
        110,
        -130,
        90,
        -110
    ),
    /** Der {@link HangmanElementType rechte Arm} des Galgenmännchens. */
    HANGMAN_RIGHT_ARM(
        110,
        -130,
        130,
        -110
    ),
    /** Das {@link HangmanElementType linke Bein} des Galgenmännchens. */
    HANGMAN_LEFT_LEG(
        110,
        -90,
        90,
        -40
    ),
    /** Das {@link HangmanElementType rechte Bein} des Galgenmännchens. */
    HANGMAN_RIGHT_LEG(
        110,
        -90,
        130,
        -40
    );
    //</editor-fold>


    //<editor-fold desc="LOCAL FIELDS">
    /** Die X-Koordinate, des Anfangspunktes dieses {@link HangmanElementType}. */
    private final int startX;
    /** Die Y-Koordinate, des Anfangspunktes dieses {@link HangmanElementType}. */
    private final int startY;
    /** Die X-Koordinate, des Endpunktes dieses {@link HangmanElementType}. */
    private final int endX;
    /** Die Y-Koordinate, des Endpunktes dieses {@link HangmanElementType}. */
    private final int endY;
    /** Der Aktivitätsstatus dieses {@link HangmanElementType}. */
    @Getter
    @Setter
    private boolean active;
    //</editor-fold>


    //<editor-fold desc="CONSTRUCTORS">

    /**
     * Erzeugt mithilfe einer X- und Y-Koordinate für sowohl den Anfangspunkt, als auch für den Endpunkt einen neuen und
     * vollständig unabhängigen {@link HangmanElementType}. Ein {@link HangmanElementType} ist ein bestimmtes Element
     * (ein Bestandteil) des gesamten visuellen Galgenmännchens.
     *
     * @param startX Die X-Koordinate, des Anfangspunktes dieses {@link HangmanElementType}.
     * @param startY Die Y-Koordinate, des Anfangspunktes dieses {@link HangmanElementType}.
     * @param endX   Die X-Koordinate, des Endpunktes dieses {@link HangmanElementType}.
     * @param endY   Die Y-Koordinate, des Endpunktes dieses {@link HangmanElementType}.
     */
    HangmanElementType(
        @Range(from = 0, to = Integer.MAX_VALUE) final int startX,
        @Range(from = 0, to = Integer.MAX_VALUE) final int startY,
        @Range(from = 0, to = Integer.MAX_VALUE) final int endX,
        @Range(from = 0, to = Integer.MAX_VALUE) final int endY
    ) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.active = false;
    }
    //</editor-fold>


    /**
     * Zeichnet dieses bestimmte {@link HangmanElementType} mithilfe von einem Basis-Punkt, auf den diese Zeichnung
     * aufbauen soll und einem {@link Graphics Graphics-Objekt}.
     *
     * @param baseX Die X-Koordinate des Basis-Punktes auf den diese Zeichnung aufbauen soll.
     * @param baseY Die Y-Koordinate des Basis-Punktes auf den diese Zeichnung aufbauen soll.
     * @param g     Das {@link Graphics Graphics-Objekt}, mit dessen Hilfe dieses {@link HangmanElementType} gezeichnet
     *              wird.
     */
    public void draw(
        @Range(from = 0, to = Integer.MAX_VALUE) final int baseX,
        @Range(from = 0, to = Integer.MAX_VALUE) final int baseY,
        @NotNull final Graphics g
    ) {
        if (this.equals(HANGMAN_HAT)) {
            g.drawOval(baseX + this.startX, baseY + this.startY, this.endX, this.endX);
            return;
        }

        g.drawLine(baseX + this.startX, baseY + this.startY, baseX + this.endX, baseY + this.endY);
    }


    //<editor-fold desc="utility">
    /**
     * Aktiviert das nächste {@link HangmanElementType}, sodass es gezeichnet wird.
     */
    public static void activeNextElement() {
        for (@NotNull final HangmanElementType element : HangmanElementType.values()) {
            if (element.isActive()) continue;

            element.setActive(true);
            return;
        }
    }

    /**
     * Prüft, ob alle einzelnen {@link HangmanElementType Typen} aktiv sind.
     *
     * @return Wenn alle einzelnen {@link HangmanElementType Typen} aktiv sind, {@code true}, ansonsten {@code false}.
     */
    public static boolean allActive() {
        return Arrays
            .stream(HangmanElementType.values())
            .allMatch(HangmanElementType::isActive);
    }
    //</editor-fold>

}
