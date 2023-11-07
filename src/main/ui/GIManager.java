package ui;

import model.CookBook;
import persistence.JsonReader;
import persistence.JsonWriter;

//Combines all JFrame aspects with cookbook to create GUI
public class GIManager {

    private MenuGUI mainGUI;
    protected MenuGUI pantryGUI;
    protected MenuGUI recipeGUI;

    protected int active = 0;
    protected static final int MAIN_ACTIVE = 0;
    protected static final int PANTRY_ACTIVE = 1;
    protected static final int RECIPE_ACTIVE = 2;

    protected CookBook cookBook;
    protected JsonReader jsonReader;
    protected JsonWriter jsonWriter;
    protected static final String JSON_STORE = "./data/cookbook.json";

    //Effects: Starts the GUI
    public GIManager() {
        linkCookBook();
        linkFrames();
    }

    //Effects: Activates the frame of the given number,
    //         Deactivates all other frames
    public void activateFrame() {
        if (this.active == MAIN_ACTIVE) {
            mainGUI.activate();
            pantryGUI.deactivate();
            recipeGUI.deactivate();
        } else if (this.active == PANTRY_ACTIVE) {
            pantryGUI.activate();
            mainGUI.deactivate();
            recipeGUI.deactivate();
        } else if (this.active == RECIPE_ACTIVE) {
            recipeGUI.activate();
            mainGUI.deactivate();
            pantryGUI.deactivate();
        }
    }

    //Modifies: This
    //Effects: sets the cookbook and save and load features
    private void linkCookBook() {
        this.cookBook = new CookBook();
        this.jsonReader = new JsonReader(JSON_STORE);
        this.jsonWriter = new JsonWriter(JSON_STORE);
    }

    //Modifies: This
    //Effects: Creates the different menu frames, and activates the main menu
    private void linkFrames() {
        active = 0;
        this.mainGUI = new MainGUI(this);
        this.pantryGUI = new PantryGUI(this);
        this.recipeGUI = new RecipeGUI(this);
        activateFrame();
    }

    //Modifies: This
    //Effects: Sets this.cookbook to the given cookbook
    public void setCookBook(CookBook cookBook) {
        this.cookBook = cookBook;
    }

}
