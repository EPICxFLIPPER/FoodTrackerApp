package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

//Represents a list of ingredients and contains methods that will be
//Commonly Called on a list of ingredients.
public abstract class IngredientList {

    protected ArrayList<Ingredient> ingredients;

    //Effects: Constructs an empty ingredient list
    public IngredientList() {
        ingredients = new ArrayList<>();
    }

    //Modifies: This
    //Effects: Adds the ingredient to ingredients
    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
        EventLog.getInstance().logEvent(new Event("Added Ingredient :" + ingredient.getName()));
    }

    //Modifies: This
    //Effects: removes nameToIngredient(ingredientName) from ingredients
    public void removeIngredient(String ingredientName) {
        Ingredient i = nameToIngredient(ingredientName);
        if (!(i == null)) {
            ingredients.remove(i);
            EventLog.getInstance().logEvent(new Event("removed Ingredient :" + ingredientName));
        }

    }


    //Effects: returns the ingredient from ingredients with name = ingredientName.
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

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    //EFFECTS: Returns this as a Json Object
    public abstract JSONObject toJson();

    //EFFECTS: Returns the list of ingredients as a JSONObject.
    protected JSONArray ingredientsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Ingredient i : ingredients) {
            jsonArray.put(i.toJson());
        }

        return jsonArray;
    }

}
