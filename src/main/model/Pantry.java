package model;

import org.json.JSONObject;

import java.util.ArrayList;

//Consists of a list of ingredients that the user has in their pantry
public class Pantry extends IngredientList {


    //EFFECTS: Creates a new Pantry with an empty list of ingredients
    public Pantry() {
        super();
    }

    @Override
    //Effects: Returns this as a JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("ingredients", ingredientsToJson());

        return json;
    }


}
