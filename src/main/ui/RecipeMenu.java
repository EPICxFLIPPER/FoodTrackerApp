package ui;

import model.CookBook;

import java.util.Scanner;

public class RecipeMenu implements Menu {

    private Scanner input;
    private CookBook cookBook;
    private FoodTrackerApp foodTrackerApp;

    public RecipeMenu(Scanner input, CookBook cookbook, FoodTrackerApp foodTrackerApp) {
        this.input = input;
        this.cookBook = cookbook;
        this.foodTrackerApp = foodTrackerApp;
    }


    @Override
    public void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tvr -> View Recipes");
        System.out.println("\tar -> Add Recipe");
        System.out.println("\trr -> Remove Recipe");
        System.out.println("\tm -> To main");
    }

    @Override
    public void processCommand(String command) {
        if (command.equals("vr")) {
            viewRecipes();
        } else if (command.equals("ar")) {
            addRecipe();
        } else if (command.equals("rr")) {
            removeRecipe();
        } else if (command.equals("m")) {
            foodTrackerApp.displayMode = foodTrackerApp.MAIN_MENU;
        } else {
            System.out.println("Selection not valid...");
        }
    }

    private void addRecipe() {
    }

    private void viewRecipes() {
    }

    private void removeRecipe() {

    }
}
