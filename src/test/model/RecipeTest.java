package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RecipeTest {
    private Pantry testPantry;
    private Ingredient cheese;
    private Ingredient crackers;

    @BeforeEach
    void setup() {
        testPantry = new Pantry();
        cheese = new Ingredient("Cheese",500,"grams");
        crackers = new Ingredient("Crackers", 10, "crackers");

    }

    @Test
    void testConstructor() {
        assertEquals(0, testPantry.getIngredients().size());
    }

    @Test
    void testAddIngredientOnce(){
        testPantry.addIngredient(cheese);
        assertEquals(1, testPantry.getIngredients().size());
        assertEquals(cheese, testPantry.getIngredients().get(0));
    }

    @Test
    void testAddIngredientMultiple(){
        testPantry.addIngredient(cheese);
        testPantry.addIngredient(crackers);
        assertEquals(2, testPantry.getIngredients().size());
        assertEquals(cheese, testPantry.getIngredients().get(0));
        assertEquals(crackers, testPantry.getIngredients().get(1));
    }

    @Test
    void testRemoveIngredientOnce(){
        testPantry.addIngredient(cheese);
        testPantry.addIngredient(crackers);
        testPantry.removeIngredient("Cheese");
        assertEquals(1, testPantry.getIngredients().size());
        assertEquals(crackers, testPantry.getIngredients().get(0));
    }

    @Test
    void testRemoveIngredientMultiple(){
        testPantry.addIngredient(cheese);
        testPantry.addIngredient(crackers);
        testPantry.removeIngredient("Cheese");
        testPantry.removeIngredient("Crackers");
        assertEquals(0, testPantry.getIngredients().size());
    }

    @Test
    void testEditIngredientQuantityOnce(){
        testPantry.addIngredient(cheese);
        testPantry.editIngredientQuantity("Cheese",100);
        assertEquals(1, testPantry.getIngredients().size());
        assertEquals(cheese, testPantry.getIngredients().get(0));
        assertEquals(100, testPantry.getIngredients().get(0).getQuantity());
    }

    @Test
    void testEditIngredientQuantityMultiple(){
        testPantry.addIngredient(cheese);

        testPantry.editIngredientQuantity("Cheese",100);
        assertEquals(1, testPantry.getIngredients().size());
        assertEquals(cheese, testPantry.getIngredients().get(0));
        assertEquals(100, testPantry.getIngredients().get(0).getQuantity());

        testPantry.editIngredientQuantity("Cheese",200);
        assertEquals(1, testPantry.getIngredients().size());
        assertEquals(cheese, testPantry.getIngredients().get(0));
        assertEquals(200, testPantry.getIngredients().get(0).getQuantity());
    }




}
