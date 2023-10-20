package ui;

import model.CookBook;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

//Handles the changing of menues and the initialization of the console interface
public class FoodTrackerApp {

    protected CookBook cookBook;
    private Scanner input;
    private Menu mainMenu;
    protected Menu pantryMenu;
    protected Menu recipeMenu;

    protected JsonWriter jsonWriter;
    protected JsonReader jsonReader;
    protected static final String JSON_STORE = "./data/cookbook.json";


    protected int displayMode; //an integer representing which menu to show
    protected static final int MAIN_MENU = 0;
    protected static final int PANTRY_MENU = 1;
    protected static final int RECIPE_MENU = 2;

    // EFFECTS: runs the FoodTracker application
    public FoodTrackerApp() {
        cookBook = new CookBook();
        input = new Scanner(System.in);
        input.useDelimiter("\n");

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        mainMenu = new MainMenu(input,this);
        pantryMenu = new PantryMenu(input,this);
        recipeMenu = new RecipeMenu(input,this);
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

    }

    //EFFECTS: Sets the correct display menu based on displayMode;
    private void selectDisplayMenu(int displayMode) {
        if (displayMode == MAIN_MENU) {
            mainMenu.displayMenu();
        } else if (displayMode == PANTRY_MENU) {
            pantryMenu.displayMenu();
        } else if (displayMode == RECIPE_MENU) {
            recipeMenu.displayMenu();
        }
    }

    //EFFECTS: Sets the correct command processor based on display mode.
    private void selectProcessCommand(int displayMode,String command) {
        if (displayMode == MAIN_MENU) {
            mainMenu.processCommand(command);
        } else if (displayMode == PANTRY_MENU) {
            pantryMenu.processCommand(command);
        } else if (displayMode == RECIPE_MENU) {
            recipeMenu.processCommand(command);
        }
    }

    public CookBook getCookBook() {
        return cookBook;
    }

    public void setCookBook(CookBook cookBook) {
        this.cookBook = cookBook;
        pantryMenu.setCookBook(cookBook);
        mainMenu.setCookBook(cookBook);
        recipeMenu.setCookBook(cookBook);
    }



}
