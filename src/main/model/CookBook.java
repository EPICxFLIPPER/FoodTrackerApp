package model;

import java.util.ArrayList;

//A cookbook contains a list of recipes and a pantry
public class CookBook {
    private ArrayList<Recipe> recipes;
    private Pantry pantry;

    //Effects: Constructs a cookbook with a empty list of recipies,
    // and a pantry
    public CookBook() {
        this.recipes = new ArrayList<>();
        this.pantry = new Pantry();
    }

    //Modifies: This
    //Effects: Adds a new recipe to the list of Recipes
    public void addRecipe(Recipe recipe) {
        //STUB
    }


    //Requires: A recipie with the given name is in recipies
    //Modifies: This
    //Effects: Removes a recipe from the list of recipes
    public void removeRecipe(String recipeName) {
        //STUB
    }

    //Requires: recipe with given name is in recipes
    //Effects: Returns true if all the ingredients in the recipe with the given name
    //are present in the pantry and have quantities >= specified quantity
    public boolean canCookRecipe(String recipeName) {
        //STUB
        return true;
    }

    //Requires: recipe with given name is in recipes
    //Effects: returns a list of all the names of ingredients that are not in
    //or are of too little quantity in the pantry based off of the recipes
    //own ingredient list
    public ArrayList<String> itemsToCook(String recipeName) {
        return null;
    }

    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }

    public Pantry getPantry() {
        return pantry;
    }
}
