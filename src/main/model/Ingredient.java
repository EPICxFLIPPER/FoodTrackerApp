package model;

//An ingredient consists of a name of a food item, the quantity of that item, and its units.
//All units are plural, even if there is 1 or 0 of the item
//ex. 1 garlic cloves
//All units must be the same to compare ingredients
//Ex, Cheese, 100, grams is not comparable to Cheese, 200 , kilograms

import org.json.JSONObject;

public class Ingredient {
    private String name;
    private int quantity;
    private final String units;

    //EFFECTS: Creates a new ingredient with a name and quantity, and units
    public Ingredient(String name, int quantity, String units) {
        this.name = name;
        this.quantity = quantity;
        this.units = units;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getUnits() {
        return units;
    }

    //EFFECTS: returns this as a JSONObject
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("quantity", quantity);
        json.put("units", units);
        return json;
    }


}
