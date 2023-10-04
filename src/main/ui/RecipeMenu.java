package ui;

import model.CookBook;
import model.Ingredient;
import model.Recipe;

import java.util.ArrayList;
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

        String command;
        String name;
        Boolean keepGoing = true;

        System.out.println("What is the name of the Recipe?");
        name = input.next();
        Recipe recipe = new Recipe(name);

        while (keepGoing) {
            System.out.println("Would You like to add an ingredient to the recipe? (y)es , (n)o");
            command = input.next();
            if (command.equals("y")) {
                Ingredient ingredient = createIngredient();
                recipe.addIngredient(ingredient);
            } else if (command.equals("n")) {
                keepGoing = false;
            } else {
                System.out.println("Not a vaild entry");
            }
        }
        cookBook.addRecipe(recipe);

    }

    private Ingredient createIngredient() {
        String name;
        int quantity;
        String units;


        System.out.println("What is the name of the ingredient");
        name = input.next();
        System.out.println("What is the quantity of the ingredient");
        quantity = Integer.parseInt(input.next());
        System.out.println("What are the measurement units of the ingredient?");
        units = input.next();

        Ingredient ingredient = new Ingredient(name,quantity,units);
        return ingredient;
    }


    private void addIngredient() {
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

    }

    private void viewRecipes() {
    }

    private void removeRecipe() {

    }
}
