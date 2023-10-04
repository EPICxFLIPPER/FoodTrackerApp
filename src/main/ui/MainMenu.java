package ui;

import model.CookBook;

import java.util.Scanner;

public class MainMenu implements Menu {

    private Scanner input;
    private CookBook cookBook;
    private FoodTrackerApp foodTrackerApp;

    public MainMenu(Scanner input, CookBook cookbook, FoodTrackerApp foodTrackerApp) {
        this.input = input;
        this.cookBook = cookbook;
        this.foodTrackerApp = foodTrackerApp;
    }


    @Override
    public void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tp ->  Pantry");
        System.out.println("\tr -> Recipe");
        System.out.println("\tq -> quit");
    }

    @Override
    public void processCommand(String command) {
        if (command.equals("p")) {
            foodTrackerApp.displayMode = foodTrackerApp.PANTRY_MENU;
        } else if (command.equals("r")) {
            foodTrackerApp.displayMode = foodTrackerApp.RECIPE_MENU;
        } else if (command.equals("q")) {
            System.out.println("Works");
        } else {
            System.out.println("Selection not valid...");
        }
    }
}
