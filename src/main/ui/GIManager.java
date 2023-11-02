package ui;

//Manages all the different Menu Panels for the GUI
public class GIManager {

    private FoodTrackerGUI foodTrackerGUI;
    private PantryGUI pantryGUI;

    protected int active = 0;
    protected static final int MAIN_ACTIVE = 0;
    protected static final int PANTRY_ACTIVE = 1;

    public GIManager() {
        this.foodTrackerGUI = new FoodTrackerGUI(this);
        this.pantryGUI = new PantryGUI(this);
        activateFrame();
    }

    //Effects: Activates the frame of the given nubmer,
    //         Decativates the rest
    public void activateFrame() {
        if (this.active == MAIN_ACTIVE) {
            foodTrackerGUI.activate();
            pantryGUI.deactivate();
        } else if (this.active == PANTRY_ACTIVE) {
            pantryGUI.activate();
            foodTrackerGUI.deactivate();
        }
    }




}
