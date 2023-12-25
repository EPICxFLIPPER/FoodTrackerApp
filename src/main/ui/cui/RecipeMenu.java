package ui.cui;

import model.Ingredient;
import model.Recipe;
import ui.gui.Menu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

//Handles the creation and functionality of the Recipe Menu screen;
public class RecipeMenu extends Menu {

    //EFFECTS: Creates a MainMenu with Scanner, and a foodTracker app.
    public RecipeMenu(Scanner input, FoodTrackerApp foodTrackerApp) {
        this.input = input;
        this.foodTrackerApp = foodTrackerApp;
    }


    @Override
    //Effects: Prints the menue screen to the console
    public void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tvr -> View Recipes");
        System.out.println("\tar -> Add Recipe");
        System.out.println("\trr -> Remove Recipe");
        System.out.println("\tcc -> Find out if you can cook a recipe!");
        System.out.println("\tnc -> Find out what you need to cook a recipe!");
        System.out.println("\tm -> To main");
    }

    @Override
    //Effects: Calls the apropriate process command method based on user input
    public void processCommand(String command) {
        if (command.equals("vr")) {
            viewRecipes();
        } else if (command.equals("ar")) {
            addRecipe();
        } else if (command.equals("rr")) {
            removeRecipe();
        }  else if (command.equals("cc")) {
            canCook();
        } else if (command.equals("nc")) {
            jesseWeNeedToCook();
        } else if (command.equals("m")) {
            foodTrackerApp.displayMode = foodTrackerApp.MAIN_MENU;
        } else {
            System.out.println("Selection not valid...");
        }
    }

    //MODIFIES: cookbook
    //EFFECTS: allows the user to create a new recipe with arbitrary amount of ingredients
    //         then adds that recipe to the cookbooks list of recipes
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

    //EFFECTS: Creates a new ingredient based off of the user input.
    private Ingredient createIngredient() {
        String name;
        int quantity;
        String units;


        System.out.println("What is the name of the ingredient (String)");
        name = input.next();
        System.out.println("What is the quantity of the ingredient? (int)");
        quantity = Integer.parseInt(input.next());
        System.out.println("What are the measurement units of the ingredient? (lowercase String)");
        units = input.next();

        Ingredient ingredient = new Ingredient(name,quantity,units);
        return ingredient;
    }

    //EFFECTS: Prints a list of all recipe names in cookbook.recipes to console
    private void viewRecipes() {
        Collection<Recipe> recipesInCookBook = cookBook.getRecipes().values();
        for (Recipe r : recipesInCookBook) {
            System.out.println("\n" + r.getName());
        }

    }

    //MODIFIES: cookbook
    //EFFECTS: removes the recipe with name from the cookbook based on user input.
    private void removeRecipe() {
        System.out.println("What is the name of the Recipe you would like to remove");
        String name = input.next();
        cookBook.removeRecipe(name);
    }

    //EFFECTS: Tells the user if they can cook the provided recipe with the ingredients
               //they have in cookbook.pantry
    private void canCook() {
        System.out.println("What recipe would you like to cook?");
        String recipeName = input.next();
        if (cookBook.canCookRecipe(recipeName)) {
            System.out.println("You can cook this recipe!");
        } else {
            System.out.println("You are missing some ingredients to cook this!");
        }
    }

    //EFFECTS: prints a list of ingredients that the recipe calls for that are not in the pantry
    private void jesseWeNeedToCook() {
        System.out.println("What recipe would ou like to cook?");
        String recipeName = input.next();
        ArrayList<String> items = cookBook.itemsToCook(recipeName);

        if (items.size() == 0) {
            System.out.println("You can cook this recipe!");
        } else {
            System.out.println("You still need to shop for these:");
            for (String s : items) {
                System.out.println(s);
            }
        }
    }


}
