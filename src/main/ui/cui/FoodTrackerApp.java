package ui.cui;

import model.CookBook;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.gui.Menu;

import java.util.Scanner;

//Handles the changing of menus and the initialization of the console interface
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
        runFoodTracker();
    }

    // MODIFIES: this
    // EFFECTS: processes user input and starts the application
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
    // EFFECTS: initializes cookBook ,menu classes, and JSON readers/ writers
    private void init() {
        cookBook = new CookBook();
        input = new Scanner(System.in);
        input.useDelimiter("\n");

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        mainMenu = new MainMenu(input,this);
        pantryMenu = new PantryMenu(input,this);
        recipeMenu = new RecipeMenu(input,this);

        setCookBookAll(cookBook);
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

    //EFFECTS: returns this.cookBook
    public CookBook getCookBook() {
        return cookBook;
    }

    //MODIFIES: this, pantryMenu, RecipeMenu, MainMenu
    //EFFECTS: sets the cookbook of this and all the menus to the given cookbook
    public void setCookBookAll(CookBook cookBook) {
        this.cookBook = cookBook;
        pantryMenu.setCookBook(cookBook);
        mainMenu.setCookBook(cookBook);
        recipeMenu.setCookBook(cookBook);
    }



}
