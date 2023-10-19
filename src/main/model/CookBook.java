package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

//A cookbook contains a list of recipes and a pantry
public class CookBook implements Writable {
    private ArrayList<Recipe> recipes;
    private Pantry pantry;

    //Effects: Constructs a cookbook with an empty list of recipes,
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



    //Modifies: This
    //Effects: Removes recipe with given name from the list of recipes
    public void removeRecipe(String recipeName) {
        Recipe recipe = nameToRecipe(recipeName);
        if (!(recipe == null)) {
            recipes.remove(recipe);
        }
    }


    //Effects: Returns true if all the ingredients in the recipe with the given name
    //are present in the pantry and have quantities >= specified quantity
    public boolean canCookRecipe(String recipeName) {
        Recipe recipe = nameToRecipe(recipeName);
        Boolean rsf = true;
        if (!(recipe == null)) {
            for (Ingredient i : recipe.getIngredients()) {
                if (!pantryHasIngredientInQuantity(i)) {
                    rsf = false;
                }
            }
        }
        return rsf;
    }


    //Effects: returns a list of all the names of ingredients that are not in
    //or are of too little quantity in the pantry based off of the recipes
    //own ingredient list
    public ArrayList<String> itemsToCook(String recipeName) {
        Recipe recipe = nameToRecipe(recipeName);
        ArrayList<String> rsf = new ArrayList<>();
        if (!(recipe == null)) {
            for (Ingredient i : recipe.getIngredients()) {
                if (!pantryHasIngredientInQuantity(i)) {
                    rsf.add(i.getName());
                }
            }
        }

        return rsf;
    }

    //Modifies: this.pantry
    //Effects: Adds the ingredient to the pantry's list of ingredients.
    public void addToPantry(Ingredient ingredient) {
        pantry.addIngredient(ingredient);
    }


    //Modifies: this.pantry
    //EFFECTS: removes ingredient with the same name from pantry
    public void removeFromPantry(String ingredientName) {
        pantry.removeIngredient(ingredientName);
    }



    //Effects: returns the recipe from recipes with the given name,
    //         Null if a recipe with that name is not in recipes.
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
        String name = ingredient.getName();
        Boolean rsf = false;
        for (Ingredient j : pantry.getIngredients()) {
            if (j.getName().equals(name)
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


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("pantry", pantryToJson());
        json.put("recipes", recipesToJson());
        return json;
    }

    private JSONObject pantryToJson() {
        return this.pantry.toJson();
    }

    private JSONArray recipesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Recipe r : recipes) {
            jsonArray.put(r.toJson());
        }

        return jsonArray;
    }



}
