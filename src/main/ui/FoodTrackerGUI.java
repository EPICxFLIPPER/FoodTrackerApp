package ui;

import javax.swing.*;
import java.awt.*;

public class FoodTrackerGUI extends JFrame {

    private static final String TITLE = "Food Tracker";
    private static final int X_DIM = 800;
    private static final int Y_DIM = 800;
    private static final int FONT_SIZE = 20;
    private static final Font COOPER_BLACK = new Font("Cooper Black",Font.PLAIN,FONT_SIZE);


    private JMenuBar menuBar;
    private JLabel titleLabel;

    public FoodTrackerGUI() {
        super(TITLE);

        init();
        createMenuBar();
        createTitleLabel();

        pack();
        repaint();


    }


    //Modifies: This
    //Effects: Sets the initial settings of the JFrame
    private void init() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(X_DIM,Y_DIM);
        setResizable(false);
        setTitle(TITLE);

        setMinimumSize(new Dimension(X_DIM,Y_DIM));
    }

    //Modifies: This
    //Effects: Creates the menu bar for the JFrame
    private void createMenuBar() {
        menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File v");
        JMenu pantryMenu = new JMenu("Pantry");
        JMenu recipeMenu = new JMenu("Recipe");
        JMenu mainMenu = new JMenu("Main");

        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem loadItem = new JMenuItem("Load");

        fileMenu.add(saveItem);
        fileMenu.add(loadItem);

        menuBar.add(fileMenu);
        menuBar.add(pantryMenu);
        menuBar.add(recipeMenu);
        menuBar.add(mainMenu);

        setJMenuBar(menuBar);
    }

    //Modifies: This
    //Effects: Creates the title label
    private void createTitleLabel() {
        titleLabel = new JLabel(TITLE);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setVerticalAlignment(JLabel.TOP);
        titleLabel.setFont(COOPER_BLACK);


        this.add(titleLabel);
    }

}
