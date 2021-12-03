package de.jonas.hangman;

import javax.swing.JFrame;

public final class Gui extends JFrame {

    private static final String TITLE = "Galgenmännchen - by Jonas";
    private static final int WIDTH = 600;
    private static final int HEIGHT = 500;


    public Gui() {
        super.setTitle(TITLE);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setBounds(0, 0, WIDTH, HEIGHT);
        super.setLocationRelativeTo(null);
        super.setResizable(false);

        final Draw draw = new Draw();
        draw.setBounds(0, 0, WIDTH, HEIGHT);
        draw.setVisible(true);

        super.add(draw);
    }

    public void open() {
        super.setVisible(true);
    }

}
