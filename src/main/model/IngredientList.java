package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

//Represents a list of ingredients and contains methods that will be
//Commonly Called on a list of ingredients.
public abstract class IngredientList implements Iterable<Ingredient> {

//    protected ArrayList<Ingredient> ingredients;
    protected Map<String,Ingredient> ingredients;

    //Effects: Constructs an empty ingredient list
    public IngredientList() {
        ingredients = new HashMap<>();
    }

    //Modifies: This
    //Effects: Adds the ingredient to ingredients
    public void addIngredient(Ingredient ingredient) {
        ingredients.put(ingredient.getName(),ingredient);
    }

    //Modifies: This
    //Effects: removes nameToIngredient(ingredientName) from ingredients
    public void removeIngredient(String ingredientName) {
        ingredients.remove(ingredientName);
    }

    public void setIngredients(Map<String,Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Map<String,Ingredient> getIngredients() {
        return ingredients;
    }

    //EFFECTS: Returns this as a Json Object
    public abstract JSONObject toJson();

    //EFFECTS: Returns the list of ingredients as a JSONObject.
    protected JSONArray ingredientsToJson() {
        JSONArray jsonArray = new JSONArray();


        for (String key : ingredients.keySet()) {
            jsonArray.put(ingredients.get(key).toJson());
        }

        return jsonArray;
    }

    @Override
    public Iterator<Ingredient> iterator() {
        return ingredients.values().iterator();
    }



}
