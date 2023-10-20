package persistence;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

import model.CookBook;
import model.Ingredient;
import model.Pantry;
import model.Recipe;
import org.json.*;

// Represents a reader that reads cookBook from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads cookBook from file and returns it;
    // throws IOException if an error occurs reading data from file
    public CookBook read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseCookBook(jsonObject); //Was parseWorkRoom
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses CookBook from JSON object and returns it
    private CookBook parseCookBook(JSONObject jsonObject) {
        CookBook cookBook = new CookBook();
        addPantry(cookBook, jsonObject);
        addRecipes(cookBook, jsonObject);
        return cookBook;
    }

    //EFFECTS: parses pantry from JSON object and adds it to cookbook
    private void addPantry(CookBook cookBook, JSONObject jsonObject) {
        Pantry pantry = new Pantry();
        JSONObject jsonObject1 = jsonObject.getJSONObject("pantry");
        pantry.setIngredients(createIngredientList(jsonObject1));
        cookBook.setPantry(pantry);
    }

    //EFFECTS: parses recipes from JSON object and adds it to cookbook
    private void addRecipes(CookBook cookBook, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("recipes");
        for (Object json : jsonArray) {
            JSONObject nextRecipe = (JSONObject) json;
            addRecipe(cookBook, nextRecipe);
        }
    }

    //EFFECTS: parses a recipe from JSONObject and adds it to cookbook
    private void addRecipe(CookBook cookBook, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Recipe recipe = new Recipe(name);
        recipe.setIngredients(createIngredientList(jsonObject));
        cookBook.addRecipe(recipe);
    }

    //EFFECTS: parses a list of Ingredients from JSONObject and returns the list
    private ArrayList<Ingredient> createIngredientList(JSONObject jsonObject) {
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        JSONArray jsonArray = jsonObject.getJSONArray("ingredients");
        for (Object json : jsonArray) {
            JSONObject nextIngredient = (JSONObject) json;
            addIngredient(ingredients,nextIngredient);
        }
        return ingredients;
    }

    //EFFECTS: constructs an Ingredient from the json Object and adds it to the given list of ingredients
    private ArrayList<Ingredient> addIngredient(ArrayList<Ingredient> ingredients, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int quantity = jsonObject.getInt("quantity");
        String units = jsonObject.getString("units");

        Ingredient ingredient = new Ingredient(name, quantity,units);
        ingredients.add(ingredient);

        return ingredients;
    }



}
