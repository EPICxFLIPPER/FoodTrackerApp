package ui;

import javax.swing.*;
import java.awt.*;

public class FoodTrackerGUI extends JFrame {

    private static final String TITLE = "Food Tracker";
    private static final int X_DIM = 800;
    private static final int Y_DIM = 800;

    public FoodTrackerGUI() {
        super(TITLE);

        init();
        createMenuBar();
        createTitleLabel();

        pack();
        repaint();


    }

    private void init() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(X_DIM,Y_DIM);
        setResizable(false);
        setTitle(TITLE);

        setMinimumSize(new Dimension(X_DIM,Y_DIM));
    }

    private void createMenuBar() {

    }

    private void createTitleLabel() {

    }

}
