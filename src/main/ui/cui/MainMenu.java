package ui.cui;

import model.CookBook;
import ui.gui.Menu;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import static ui.cui.FoodTrackerApp.JSON_STORE;

//Handles the creation and functionality of the Main Menu screen;
public class MainMenu extends Menu {

    //EFFECTS: Creates a MainMenu with Scanner, and a foodTracker app.
    public MainMenu(Scanner input, FoodTrackerApp foodTrackerApp) {
        this.input = input;
        this.foodTrackerApp = foodTrackerApp;
    }

    @Override
    //Effects: Prints the menue screen to the console
    public void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tp ->  Pantry");
        System.out.println("\tr -> Recipe");
        System.out.println("\ts -> Save cookbook to file");
        System.out.println("\tl -> Load cookbook from file");
        System.out.println("\tq -> quit");
    }

    @Override
    //Effects: Calls the apropriate process command method based on user input
    public void processCommand(String command) {
        if (command.equals("p")) {
            foodTrackerApp.displayMode = foodTrackerApp.PANTRY_MENU;
        } else if (command.equals("r")) {
            foodTrackerApp.displayMode = foodTrackerApp.RECIPE_MENU;
        } else if (command.equals("s")) {
            saveCookBook();
        } else if (command.equals("l")) {
            loadCookBook();
        }  else {
            System.out.println("Selection not valid...");
        }
    }

    // EFFECTS: saves the cookBook to file
    private void saveCookBook() {
        try {
            foodTrackerApp.jsonWriter.open();
            foodTrackerApp.jsonWriter.write(cookBook);
            foodTrackerApp.jsonWriter.close();
            System.out.println("Saved CookBook to" + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: FoodTrackerApp, any menu that extends menu
    // EFFECTS: loads CookBook from file, and apply it to all menus and food tracker app
    private void loadCookBook() {
        try {
            CookBook tempBook = foodTrackerApp.jsonReader.read();
            foodTrackerApp.setCookBookAll(tempBook);
            System.out.println("Loaded CookBook from:" + JSON_STORE);

        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }



}
