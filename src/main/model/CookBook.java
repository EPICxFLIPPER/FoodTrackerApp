package model;

import conversions.UnitConverter;
import exception.InvalidConversionException;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.HashMap;

//A cookbook contains a list of recipes and a pantry
public class CookBook implements Writable {
    private HashMap<String,Recipe> recipes;
    private Pantry pantry;

    private UnitConverter theConverter;

    //Effects: Constructs a cookbook with an empty list of recipes,
    // and a pantry
    public CookBook() {
        this.recipes = new HashMap<>();
        this.pantry = new Pantry();
        theConverter = UnitConverter.getInstance();
    }

    //Modifies: This
    //Effects: Adds a new recipe to the list of Recipes
    public void addRecipe(Recipe recipe) {
        recipes.put(recipe.getName(),recipe);
        EventLog.getInstance().logEvent(new Event("Added new Recipe :" + recipe.getName()));
    }

    //Modifies: This
    //Effects: Removes recipe with given name from the list of recipes
    public void removeRecipe(String recipeName) {
        recipes.remove(recipeName);
    }


    //Effects: Returns true if all the ingredients in the recipe with the given name
    //are present in the pantry and have quantities >= specified quantity
    public boolean canCookRecipe(String recipeName) {
        Recipe recipe = recipes.get(recipeName);
        Boolean rsf = true;
        if (!(recipe == null)) {




            for (Ingredient i : recipe) {
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
        Recipe recipe = recipes.get(recipeName);
        ArrayList<String> rsf = new ArrayList<>();
        if (!(recipe == null)) {
            for (Ingredient i : recipe) {
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
        EventLog.getInstance().logEvent(new Event("Added Ingredient to Pantry :" + ingredient.getName()));
    }


    //Modifies: this.pantry
    //EFFECTS: removes ingredient with the same name from pantry
    public void removeFromPantry(String ingredientName) {
        pantry.removeIngredient(ingredientName);
        EventLog.getInstance().logEvent(new Event("Removed Ingredient to Pantry :" + ingredientName));
    }


    //EFFECTS: returns true if the pantry contains an ingredient with the same name
    // and the ingredient in the pantry has quantity >= given ingredient
    private boolean pantryHasIngredientInQuantity(Ingredient ingredient) {

        String name = ingredient.getName();
        int quantity = ingredient.getQuantity();
        String unit = ingredient.getUnits();


        for (Ingredient j : pantry) {
            if (j.getName().equals(name)) {
                if (j.getUnits().toUpperCase() == unit.toUpperCase()) {
                    return j.getQuantity() >= quantity;
                } else {
                    try {
                        double factor = theConverter.convertFactor(unit, j.getUnits());
                        return j.getQuantity() >= quantity * factor;
                    } catch (InvalidConversionException e) {
                        return false;
                    }

                }
            }

        }
        return false;
    }

    public int recipesSize() {
        return recipes.values().size();
    }

    public int pantrySize() {
        return pantry.getIngredients().values().size();
    }

    public Recipe getRecipe(String name) {
        return recipes.get(name);
    }

    public HashMap<String,Recipe> getRecipes() {
        return recipes;
    }

    public Pantry getPantry() {
        return pantry;
    }

    public void setPantry(Pantry pantry) {
        this.pantry = pantry;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("pantry", pantryToJson());
        json.put("recipes", recipesToJson());
        return json;
    }

    //EFFECTS: Returns the pantry as a JSONObject
    private JSONObject pantryToJson() {
        return this.pantry.toJson();
    }

    //EFFECTS: Returns the recipes as a JSONArray
    private JSONArray recipesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Recipe r : recipes.values()) {
            jsonArray.put(r.toJson());
        }

        for (String key : recipes.keySet()) {
            jsonArray.put(recipes.get(key).toJson());
        }

        return jsonArray;
    }




}
