package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RecipeTest {
    private Recipe testRecipe;
    private Ingredient cheese;
    private Ingredient crackers;

    @BeforeEach
    void setup() {
        testRecipe = new Recipe("Cheese+Crackers");
        cheese = new Ingredient("Cheese",500,"grams");
        crackers = new Ingredient("Crackers", 10, "crackers");

    }

    @Test
    void testConstructor() {
        assertEquals("Cheese+Crackers",testRecipe.getName());
        assertEquals(0,testRecipe.getIngredients().size());
    }

    @Test
    void testAddIngredientOnce(){
        testRecipe.addIngredient(cheese);
        assertEquals(1,testRecipe.getIngredients().size());
        assertEquals(cheese,testRecipe.getIngredients().get(0));
    }

    @Test
    void testAddIngredientMultiple(){
        testRecipe.addIngredient(cheese);
        testRecipe.addIngredient(crackers);
        assertEquals(2,testRecipe.getIngredients().size());
        assertEquals(cheese,testRecipe.getIngredients().get(0));
        assertEquals(crackers,testRecipe.getIngredients().get(1));
    }

    @Test
    void testRemoveIngredientOnce(){
        testRecipe.addIngredient(cheese);
        testRecipe.addIngredient(crackers);
        testRecipe.removeIngredient("Cheese");
        assertEquals(1,testRecipe.getIngredients().size());
        assertEquals(crackers,testRecipe.getIngredients().get(0));
    }

    @Test
    void testRemoveIngredientMultiple(){
        testRecipe.addIngredient(cheese);
        testRecipe.addIngredient(crackers);
        testRecipe.removeIngredient("Cheese");
        testRecipe.removeIngredient("Crackers");
        assertEquals(0,testRecipe.getIngredients().size());
    }

    @Test
    void testEditIngredientQuantityOnce(){
        testRecipe.addIngredient(cheese);
        testRecipe.editIngredientQuantity("Cheese",100);
        assertEquals(1,testRecipe.getIngredients().size());
        assertEquals(cheese,testRecipe.getIngredients().get(0));
        assertEquals(100,testRecipe.getIngredients().get(0).getQuantity());
    }

    @Test
    void testEditIngredientQuantityMultiple(){
        testRecipe.addIngredient(cheese);

        testRecipe.editIngredientQuantity("Cheese",100);
        assertEquals(1,testRecipe.getIngredients().size());
        assertEquals(cheese,testRecipe.getIngredients().get(0));
        assertEquals(100,testRecipe.getIngredients().get(0).getQuantity());

        testRecipe.editIngredientQuantity("Cheese",200);
        assertEquals(1,testRecipe.getIngredients().size());
        assertEquals(cheese,testRecipe.getIngredients().get(0));
        assertEquals(200,testRecipe.getIngredients().get(0).getQuantity());
    }




}
