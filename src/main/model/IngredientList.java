package model;

//Represents a list of ingredients and contains methods that will be
//Commonly Called on a list of ingredients.

import java.util.ArrayList;

public abstract class IngredientList {
    protected ArrayList<Ingredient> ingredients;

    //Effects: Constructs an empty ingredient list
    public IngredientList() {
        ingredients = new ArrayList<>();
    }

    //Modifies: This
    //Effects: adds the ingredient to the list of ingredient
    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }


    //Requires: an ingredient with ingredient name is in ingredients
    //Modifies: This
    //Effects: removes the ingredient with the given name from ingredients
    public void removeIngredient(String ingredientName) {
        Ingredient i = nameToIngredient(ingredientName);
        if (!(i == null)) {
            ingredients.remove(i);
        }

    }

    //Requires: a ingredient with ingredient name is in ingredients;
    //Modifies: This, ingredient with given name
    //Effects: Changes the quantity of the ingredient in ingredients
    public void editIngredientQuantity(String ingredientName, int quantity) {
        Ingredient i = nameToIngredient(ingredientName);
        if (!(i == null)) {
            i.setQuantity(quantity);
        }
    }


    //Requires: The ingredient with ingredientName is in ingredients
    //Effects: returns the ingredient from ingredients with the given name
    private Ingredient nameToIngredient(String ingredientName) {
        Ingredient ingredient = null;
        for (Ingredient i : ingredients) {
            if (i.getName().equals(ingredientName)) {
                ingredient = i;
            }
        }
        return ingredient;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }
}
