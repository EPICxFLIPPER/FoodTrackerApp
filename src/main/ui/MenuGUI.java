package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public abstract class MenuGUI extends JFrame implements ActionListener {

    protected GIManager gim;

    protected static final int X_DIM = 800;
    protected static final int Y_DIM = 800;

    protected static final int FONT_SIZE = 20;
    protected static final Font COOPER_BLACK = new Font("Cooper Black",Font.PLAIN,FONT_SIZE);

    protected JPanel centerPanel;
    protected JPanel northPanel;
    protected JPanel southPanel;
    protected JPanel eastPanel;
    protected JPanel westPanel;

    protected JMenuBar menuBar;
    protected JLabel titleLabel;

    protected boolean active;


    public MenuGUI(String title) {
        super(title);
    }

    //Modifies: This
    //Effects: Creates the menu bar for the JFrame
    protected void createMenuBar() {
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
    protected void createTitleLabel(String title) {
        northPanel.setBackground(Color.green);

        titleLabel = new JLabel(title);
        titleLabel.setHorizontalTextPosition(JLabel.CENTER);
        titleLabel.setVerticalAlignment(JLabel.TOP);
        titleLabel.setFont(COOPER_BLACK);

        northPanel.add(titleLabel);

        repaint();
    }

    //Modifies: this
    //Effects: Changes active to false and sets the visability to false
    public void deactivate() {
        active = false;
        this.setVisible(active);
    }

    //Modifies: this
    //Effects: Changes active to true and sets the visability to true
    public void activate() {
        active = true;
        this.setVisible(active);
    }

    protected abstract void init();

    protected abstract void createButtons();



}
