package model;

//Consists of a name of thing to be cooked, and
//list of ingredients that will be used to cook said thing.

import org.json.JSONObject;

import java.util.ArrayList;

public class Recipe extends IngredientList {

    private String name;

    //Constructs a new Recipe with a name and empty Ingredient List
    public Recipe(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    //Effects: Returns this as a JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("ingredients", ingredientsToJson());
        return json;
    }
}
