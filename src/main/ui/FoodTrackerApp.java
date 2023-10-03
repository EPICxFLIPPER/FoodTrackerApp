package ui;

import model.CookBook;
import model.Ingredient;

import java.util.ArrayList;
import java.util.Scanner;

public class FoodTrackerApp {

    private CookBook cookBook;
    private Scanner input;

    private int displayMode; //an integer representing which menu to show
    private static final int MAIN_MENU = 0;
    private static final int PANTRY_MENU = 1;

    // EFFECTS: runs the teller application
    public FoodTrackerApp() {
        runFoodTracker();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runFoodTracker() {
        boolean keepGoing = true;
        String command = null;

        init();



        while (keepGoing) {
            selectDisplayMenu(displayMode);
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                selectProcessCommand(displayMode,command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: initializes cookBook
    private void init() {
        cookBook = new CookBook();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    private void selectDisplayMenu(int displayMode) {
        if (displayMode == MAIN_MENU) {
            displayMainMenu();
        } else if (displayMode == PANTRY_MENU) {
            displayPantryMenu();
        }
    }

    private void selectProcessCommand(int displayMode,String command) {
        if (displayMode == MAIN_MENU) {
            processCommandMain(command);
        } else if (displayMode == PANTRY_MENU) {
            processCommandPantry(command);
        }
    }

    private void displayMainMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tp ->  Pantry");
        System.out.println("\tap -> Add to Pantry");
        System.out.println("\tr -> View Recipes");
        System.out.println("\tc -> Can Cook?");
        System.out.println("\tn -> Needed to Cook");
        System.out.println("\tq -> quit");
    }

    private void displayPantryMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tv ->  View Pantry");
        System.out.println("\tap -> Add to Pantry");
        System.out.println("\tr -> Remove from Pantry");
        System.out.println("\tm -> To Main Menu");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommandMain(String command) {
        if (command.equals("p")) {
            displayMode = PANTRY_MENU;
        } else if (command.equals("r")) {
            System.out.println("Works");
        } else if (command.equals("ap")) {
            addToPantry();
        } else if (command.equals("c")) {
            System.out.println("Works");
        } else if (command.equals("n")) {
            System.out.println("Works");
        } else {
            System.out.println("Selection not valid...");
        }
    }

    private void processCommandPantry(String command) {
        if (command.equals("v")) {
            viewPantry();
        } else if (command.equals("ap")) {
            addToPantry();
        } else if (command.equals("r")) {
            removeFromPantry();
        } else if (command.equals("m")) {
            displayMode = MAIN_MENU;
        } else {
            System.out.println("Not a valid Command!");
        }
    }


    private void viewPantry() {
        ArrayList<Ingredient> itemsInPantry = cookBook.getPantry().getIngredients();
        for (Ingredient i : itemsInPantry) {
            System.out.println("\n" + i.getName());
        }
    }

    private void addToPantry() {
        String name;
        int quantity;
        String units;

        System.out.println("What is the name of the ingredient?");
        name = input.next();
        System.out.println("What is the quantity of the ingredient");
        quantity = Integer.parseInt(input.next());
        System.out.println("What are the measurement units of the ingredient?");
        units = input.next();

        Ingredient ingredient = new Ingredient(name,quantity,units);
        cookBook.addToPantry(ingredient);

    }

    private void removeFromPantry() {
        System.out.println("What is the name of the ingredient you would like to remove");
        String name = input.next();
        cookBook.removeFromPantry(name);
    }




}
