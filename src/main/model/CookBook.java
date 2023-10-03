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
        recipes.add(recipe);
    }


    //Requires: A recipe with the given name is in recipes
    //Modifies: This
    //Effects: Removes a recipe from the list of recipes
    public void removeRecipe(String recipeName) {
        Recipe recipe = nameToRecipe(recipeName);
        recipes.remove(recipe);
    }

    //Requires: recipe with given name is in recipes
    //Effects: Returns true if all the ingredients in the recipe with the given name
    //are present in the pantry and have quantities >= specified quantity
    public boolean canCookRecipe(String recipeName) {
        Recipe recipe = nameToRecipe(recipeName);
        Boolean rsf = true;
        for (Ingredient i : recipe.getIngredients()) {
            if (!pantryHasIngredientInQuantity(i)) {
                rsf = false;
            }
        }
        return rsf;
    }

    //Requires: recipe with given name is in recipes
    //Effects: returns a list of all the names of ingredients that are not in
    //or are of too little quantity in the pantry based off of the recipes
    //own ingredient list
    public ArrayList<String> itemsToCook(String recipeName) {
        Recipe recipe = nameToRecipe(recipeName);
        ArrayList<String> rsf = new ArrayList<>();
        for (Ingredient i : recipe.getIngredients()) {
            if (!pantryHasIngredientInQuantity(i)) {
                rsf.add(i.getName());
            }
        }
        return rsf;
    }

    //Modifies: pantry
    //Effects: Adds the ingredient to the pantry
    public void addToPantry(Ingredient ingredient) {
        pantry.addIngredient(ingredient);
    }

    //REQUIRES: an ingrident with the given name is in the pantry
    //Modifies: Pantry
    //EFFECTS: removes ingredient with the same name from pantry
    public void removeFromPantry(String ingredientName) {
        pantry.removeIngredient(ingredientName);
    }


    //Requires: The ingredient with ingredientName is in ingredients
    //Effects: returns the ingredient from ingredients with the given name
    private Recipe nameToRecipe(String recipeName) {
        Recipe recipe = null;
        for (Recipe r : recipes) {
            if (r.getName().equals(recipeName)) {
                recipe = r;
            }
        }
        return recipe;
    }

    //EFFECTS: returns true if the pantry contains an ingredient with the same name
    // and the ingredient in the pantry has quantity >= given ingredient
    private boolean pantryHasIngredientInQuantity(Ingredient ingredient) {
        String iname = ingredient.getName();
        Boolean rsf = false;
        for (Ingredient j : pantry.getIngredients()) {
            if (j.getName().equals(iname)
                    && j.getQuantity() >= ingredient.getQuantity()) {
                rsf = true;
            }
        }
        return rsf;
    }

    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }

    public Pantry getPantry() {
        return pantry;
    }
}
