package ui;

import model.CookBook;
import model.Ingredient;

import java.util.ArrayList;
import java.util.Scanner;


//Handles the creation and functionality of the Pantry Menu screen;
public class PantryMenu extends Menu {

    //EFFECTS: Creates a MainMenu with Scanner and a foodTracker app.
    public PantryMenu(Scanner input, FoodTrackerApp foodTrackerApp) {
        this.input = input;
        this.foodTrackerApp = foodTrackerApp;
    }


    @Override
    //Effects: Prints the menue screen to the console
    public void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tv ->  View Pantry");
        System.out.println("\tap -> Add to Pantry");
        System.out.println("\tr -> Remove from Pantry");
        System.out.println("\tm -> To Main Menu");
    }

    @Override
    //Effects: Calls the apropriate process command method based on user input
    public void processCommand(String command) {
        if (command.equals("v")) {
            viewPantry();
        } else if (command.equals("ap")) {
            addToPantry();
        } else if (command.equals("r")) {
            removeFromPantry();
        } else if (command.equals("m")) {
            foodTrackerApp.displayMode = foodTrackerApp.MAIN_MENU;
        } else if (command.equals("n")) {
            System.out.println(cookBook.getNumIngredientsPantry());
        } else {
            System.out.println("Not a valid Command!");
        }
    }

    //EFFECTS: prints a list of ingredient names that are in the pantry.
    private void viewPantry() {
        ArrayList<Ingredient> itemsInPantry = cookBook.getPantry().getIngredients();
        for (Ingredient i : itemsInPantry) {
            System.out.println("\n" + i.getName());
        }
    }

    //MODIFIES: cookbook.pantry
    //EFFECTS: Adds the ingredient to the pantry based on user input.
    private void addToPantry() {
        String name;
        int quantity;
        String units;

        System.out.println("What is the name of the ingredient? (string)");
        name = input.next();
        System.out.println("What is the quantity of the ingredient (int)");
        quantity = Integer.parseInt(input.next());
        System.out.println("What are the measurement units of the ingredient? (lowerCase String)");
        units = input.next();

        Ingredient ingredient = new Ingredient(name,quantity,units);
        cookBook.addToPantry(ingredient);

    }

    //MODIFIES: Cookbook.pantry
    //EFFECTS: Removes the specified ingredient from the pantry
    private void removeFromPantry() {
        System.out.println("What is the name of the ingredient you would like to remove");
        String name = input.next();
        cookBook.removeFromPantry(name);
    }

}
