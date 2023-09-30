package model;

//Consists of a name of thing to be cooked, and
//list of ingredients that will be used to cook said thing.

import java.util.ArrayList;

public class Recipe {

    private String name;
    private ArrayList<Ingredient> ingredients;


    //Constructs a new Recipe with a name and empty list of Ingredients
    public Recipe(String name) {
        this.name = name;
        this.ingredients = new ArrayList<>();
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
        ingredients.remove(ingredients.indexOf(i));
    }

    //Requires: a ingredient with ingredient name is in ingredients;
    //Modifies: This, ingredient with given name
    //Effects: Changes the quantity of the ingredient in ingredients
    public void editIngredientQuantity(String ingredientName, int quantity) {
        Ingredient i = nameToIngredient(ingredientName);
        i.setQuantity(quantity);
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






    public String getName() {
        return name;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }
}
