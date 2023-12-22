package ui.GUI;

import model.CookBook;
import ui.CUI.FoodTrackerApp;

import java.util.Scanner;

//A menu is a display with a set of commands that leads to specific results
public abstract class Menu {

    protected Scanner input;
    protected FoodTrackerApp foodTrackerApp;
    protected CookBook cookBook;

    //EFFECTS: Creates the console interface of a specific menu.
    public abstract void displayMenu();

    //EFFECTS: runs the result of user commands.
    public abstract void processCommand(String command);

    //MODIFIES: this
    //EFFECTS: Sets the cookbook to the given cookbook
    public void setCookBook(CookBook cookBook) {
        this.cookBook = cookBook;
    }

}
