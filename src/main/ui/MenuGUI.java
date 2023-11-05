package ui;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class MenuGUI extends JFrame implements ActionListener, MenuListener {

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

    protected JLabel titleLabel;

    protected boolean active;

    protected JMenuBar menuBar = new JMenuBar();
    protected JMenu fileMenu = new JMenu("File v");
    protected JMenu pantryMenu = new JMenu("Pantry");
    protected JMenu recipeMenu = new JMenu("Recipe");
    protected JMenu mainMenu = new JMenu("Main");

    protected JMenuItem saveItem = new JMenuItem("Save");
    protected JMenuItem loadItem = new JMenuItem("Load");

    protected String[] listInit = new String[0];


    public MenuGUI(String title) {
        super(title);
    }

    //Modifies: This
    //Effects: Creates the menu bar for the JFrame
    protected void createMenuBar() {


        fileMenu.add(saveItem);
        fileMenu.add(loadItem);

        menuBar.add(fileMenu);
        menuBar.add(pantryMenu);
        menuBar.add(recipeMenu);
        menuBar.add(mainMenu);

        setJMenuBar(menuBar);

        saveItem.addActionListener(this);
        loadItem.addActionListener(this);
        pantryMenu.addMenuListener(this);
        recipeMenu.addMenuListener(this);
        mainMenu.addMenuListener(this);
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

    //Effects: Sets the actions for the menu bar
    protected void menuBarActions(ActionEvent e) {
        if (e.getSource() == saveItem) {
            //STUB
            System.out.println("save");
        } else if (e.getSource() == loadItem) {
            //STUB
            System.out.println("load");
        }
    }

    @Override
    public void menuSelected(MenuEvent e) {
        if (e.getSource() == mainMenu) {
            gim.active = gim.MAIN_ACTIVE;
            gim.activateFrame();
        } else if (e.getSource() == pantryMenu) {
            gim.active = gim.PANTRY_ACTIVE;
            gim.activateFrame();
            System.out.println("pantry");
        } else if (e.getSource() == recipeMenu) {
            gim.active = gim.RECIPE_ACTIVE;
            gim.activateFrame();
            System.out.println("recipe");
        }
    }

    @Override
    public void menuDeselected(MenuEvent e) {

    }

    @Override
    public void menuCanceled(MenuEvent e) {

    }


    protected abstract void init();

    protected abstract void createCenterFrame();



}
