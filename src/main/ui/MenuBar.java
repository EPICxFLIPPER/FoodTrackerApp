package ui;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

//Creates the menu-bar for the application, and handles corresponding events;
public class MenuBar extends JMenuBar implements MenuListener {

    private JMenu pantryMenu = new JMenu("Pantry");
    private JMenu recipeMenu = new JMenu("Recipe");
    private JMenu homeMenu = new JMenu("Home");

    private GIManager gim;

    //Creates the MenuBar
    public MenuBar(GIManager gim) {
        this.gim = gim;
        createMenuBar();
    }

    //Modifies: This
    //Effects: Creates the menu bar for the JFrame
    protected void createMenuBar() {
        addMenus();
        addMenuListeners();
        createMenuMnemonics();
    }

    //Modifies: This
    //Effects: Adds each of the menu tabs to the menu bar
    private void addMenus() {
        this.add(pantryMenu);
        this.add(recipeMenu);
        this.add(homeMenu);
    }

    //Modifies:This
    //Effects: Adds a menu listener to each of the menu Items
    private void addMenuListeners() {
        pantryMenu.addMenuListener(this);
        recipeMenu.addMenuListener(this);
        homeMenu.addMenuListener(this);
    }

    //Modifies: This
    //Effects: Sets a Mnemonic to each of the Menu Items
    private void createMenuMnemonics() {
        pantryMenu.setMnemonic('P');
        recipeMenu.setMnemonic('R');
        homeMenu.setMnemonic('H');
    }

    @Override
    //Modifies: GIManager
    //Effects: Provides the actions for each individual selection from the menuBar
    public void menuSelected(MenuEvent e) {
        if (e.getSource() == homeMenu) {
            gim.changeMenu("home");
        } else if (e.getSource() == pantryMenu) {
            gim.changeMenu("pantry");
        } else if (e.getSource() == recipeMenu) {
            gim.changeMenu("recipe");
        }
    }

    @Override
    //Effects: none intended
    public void menuDeselected(MenuEvent e) {

    }

    @Override
    //Effects: none intended
    public void menuCanceled(MenuEvent e) {

    }



}
