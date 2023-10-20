package ui;

import model.CookBook;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import static ui.FoodTrackerApp.JSON_STORE;

//Handles the creation and functionality of the Main Menu screen;
public class MainMenu implements Menu {

    private Scanner input;
    private CookBook cookBook;
    private FoodTrackerApp foodTrackerApp;

    //EFFECTS: Creates a MainMenu with Scanner, cookbook and a foodTracker app.
    public MainMenu(Scanner input, FoodTrackerApp foodTrackerApp) {
        this.input = input;
        this.foodTrackerApp = foodTrackerApp;
        cookBook = foodTrackerApp.getCookBook();
    }

    @Override
    public void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tp ->  Pantry");
        System.out.println("\tr -> Recipe");
        System.out.println("\ts -> Save cookbook to file");
        System.out.println("\tl -> Load cookbook from file");
        System.out.println("\tq -> quit");
    }

    @Override
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

    @Override
    public void setCookBook(CookBook cookBook) {
        this.cookBook = cookBook;
    }

    // EFFECTS: saves the workroom to file
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

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadCookBook() {
        try {
            CookBook tempBook = foodTrackerApp.jsonReader.read();
            //assert (1 == tempBook.getNumIngredientsPantry());

            foodTrackerApp.setCookBookAll(tempBook);
            //assert (1 == foodTrackerApp.cookBook.getNumIngredientsPantry());


            System.out.println("Loaded CookBook from:" + JSON_STORE);

        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }



}
