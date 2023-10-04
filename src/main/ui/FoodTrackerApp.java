package ui;

import model.CookBook;
import java.util.Scanner;

public class FoodTrackerApp {

    private CookBook cookBook;
    private Scanner input;
    private Menu mainMenu;
    private Menu pantryMenu;
    private Menu recipeMenu;

    protected int displayMode; //an integer representing which menu to show
    protected static final int MAIN_MENU = 0;
    protected static final int PANTRY_MENU = 1;
    protected static final int RECIPE_MENU = 2;

    // EFFECTS: runs the teller application
    public FoodTrackerApp() {
        runFoodTracker();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runFoodTracker() {
        boolean keepGoing = true;
        String command;

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
    // EFFECTS: initializes cookBook and menu classes
    private void init() {
        cookBook = new CookBook();
        input = new Scanner(System.in);
        input.useDelimiter("\n");

        mainMenu = new MainMenu(input,cookBook,this);
        pantryMenu = new PantryMenu(input,cookBook,this);
        recipeMenu = new RecipeMenu(input,cookBook,this);
    }

    private void selectDisplayMenu(int displayMode) {
        if (displayMode == MAIN_MENU) {
            mainMenu.displayMenu();
        } else if (displayMode == PANTRY_MENU) {
            pantryMenu.displayMenu();
        } else if (displayMode == RECIPE_MENU) {
            recipeMenu.displayMenu();
        }
    }

    private void selectProcessCommand(int displayMode,String command) {
        if (displayMode == MAIN_MENU) {
            mainMenu.processCommand(command);
        } else if (displayMode == PANTRY_MENU) {
            pantryMenu.processCommand(command);
        } else if (displayMode == RECIPE_MENU) {
            recipeMenu.processCommand(command);
        }
    }



}
