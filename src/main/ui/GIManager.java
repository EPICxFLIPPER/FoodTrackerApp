package ui;

//Manages all the different Menu Panels for the GUI
public class GIManager {

    private MenuGUI mainGUI;
    private MenuGUI pantryGUI;
    private MenuGUI recipeGUI;

    protected int active = 0;
    protected static final int MAIN_ACTIVE = 0;
    protected static final int PANTRY_ACTIVE = 1;
    protected static final int RECIPE_ACTIVE = 2;

    public GIManager() {
        this.mainGUI = new MainGUI(this);
        this.pantryGUI = new PantryGUI(this);
        this.recipeGUI = new RecipeGUI(this);
        activateFrame();
    }

    //Effects: Activates the frame of the given nubmer,
    //         Decativates the rest
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




}
