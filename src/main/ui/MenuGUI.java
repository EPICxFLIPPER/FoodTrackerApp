package ui;

import model.CookBook;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import static ui.FoodTrackerApp.JSON_STORE;

//Represents the Menu Screens used by the GUI
//The screens are using a border layout with a grid layout center

public abstract class MenuGUI extends JPanel implements ActionListener {

    protected GIManager gim;



    protected static final int FONT_SIZE = 20;
    protected static final Font COOPER_BLACK = new Font("Cooper Black",Font.PLAIN,FONT_SIZE);

    protected JPanel centerPanel;
    protected JPanel northPanel;
    protected JPanel southPanel;
    protected JPanel eastPanel;
    protected JPanel westPanel;

    protected JLabel titleLabel;

    protected boolean active;


    protected String[] listInit = new String[0];

    //Creates a menu screen with the given title
//    public MenuGUI(String title) {
//        super(title);
//    }



    //Modifies: This
    //Effects: Creates the title label for given menu
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
    //Effects: Changes active to false and sets the visibility to false
    public void deactivate() {
        active = false;
        this.setVisible(false);
    }

    //Modifies: this
    //Effects: Changes active to true and sets the visibility to true
    public void activate() {
        active = true;
        this.setVisible(true);
    }

//    //Effects: Sets the actions for the menu bar
//    protected void menuBarActions(ActionEvent e) {
//        if (e.getSource() == saveItem) {
//            saveCookBook();
//        } else if (e.getSource() == loadItem) {
//            loadCookBook();
//            updateSideList();
//        }
//    }

//    @Override
//    //Modifies: GIManager
//    //Effects: Provides the actions for each individual selection from the menuBar
//    public void menuSelected(MenuEvent e) {
//        if (e.getSource() == mainMenu) {
//            gim.active = gim.MAIN_ACTIVE;
//            gim.activateFrame();
//        } else if (e.getSource() == pantryMenu) {
//            gim.active = gim.PANTRY_ACTIVE;
//            gim.pantryGUI.updateSideList();
//            gim.activateFrame();
//            System.out.println("pantry");
//        } else if (e.getSource() == recipeMenu) {
//            gim.active = gim.RECIPE_ACTIVE;
//            gim.recipeGUI.updateSideList();
//            gim.activateFrame();
//            System.out.println("recipe");
//        }
//    }

//    // EFFECTS: saves the cookBook to JSON file
//    private void saveCookBook() {
//        try {
//            gim.jsonWriter.open();
//            gim.jsonWriter.write(gim.cookBook);
//            gim.jsonWriter.close();
//            System.out.println("Saved CookBook to" + JSON_STORE);
//        } catch (FileNotFoundException e) {
//            System.out.println("Unable to write to file: " + JSON_STORE);
//        }
//    }

//    // MODIFIES: gim.Cookbook
//    // EFFECTS: loads CookBook from file, and apply it to all menus and food tracker app
//    private void loadCookBook() {
//        try {
//            CookBook tempBook = gim.jsonReader.read();
//            gim.setCookBook(tempBook);
//            System.out.println("Loaded CookBook from:" + JSON_STORE);
//
//        } catch (IOException e) {
//            System.out.println("Unable to read from file: " + JSON_STORE);
//        }
//    }


    //Effects: Refreshes the side list(s) of the menu
    protected abstract void updateSideList();

    //Effects: Initializes the menu
    protected abstract void init();

    //Effects: Creates the features for the center main frame.
    protected abstract void createCenterFrame();

    //Effects: Makes the blank filler labels for the center grid frame:
    protected void createBlankLabels(int times) {
        for (int i = 0; i < times; i++) {
            centerPanel.add(new JLabel());
        }
    }

    //Modifies: This
    //Effects: Sets the action listeners for the features on the menu
    protected abstract void setActionListeners();

}
