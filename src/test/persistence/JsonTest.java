package persistence;

import model.Ingredient;
import model.Recipe;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JsonTest {

    protected void checkRecipe(String name, Recipe recipe) {
        assertEquals(name, recipe.getName());
        assertTrue(recipe.getIngredients().size() >= 0);
    }

    protected void checkIngredient(String name, int quantity, String units, Ingredient ingredient) {
        assertEquals(name,ingredient.getName());
        assertEquals(quantity,ingredient.getQuantity());
        assertEquals(units,ingredient.getUnits());
    }


}
