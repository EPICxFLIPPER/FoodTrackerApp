package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class IngredientListTest {
    IngredientList testList;
    protected Ingredient cheese;
    protected Ingredient crackers;

    @BeforeEach
    void setup() {
        cheese = new Ingredient("Cheese",500,"grams");
        crackers = new Ingredient("Crackers", 10, "crackers");
    }


    @Test
    void testAddIngredientOnce() {
        testList.addIngredient(cheese);
        assertEquals(1, testList.getIngredients().size());
        assertEquals(cheese, testList.getIngredients().get(0));
    }

    @Test
    void testAddIngredientMultiple() {
        testList.addIngredient(cheese);
        testList.addIngredient(crackers);
        assertEquals(2, testList.getIngredients().size());
        assertEquals(cheese, testList.getIngredients().get(0));
        assertEquals(crackers, testList.getIngredients().get(1));
    }

    @Test
    void testRemoveIngredientOnce() {
        testList.addIngredient(cheese);
        testList.addIngredient(crackers);
        testList.removeIngredient("Cheese");
        assertEquals(1, testList.getIngredients().size());
        assertEquals(crackers, testList.getIngredients().get(0));
    }

    @Test
    void testRemoveIngredientMultiple() {
        testList.addIngredient(cheese);
        testList.addIngredient(crackers);
        testList.removeIngredient("Cheese");
        testList.removeIngredient("Crackers");
        assertEquals(0, testList.getIngredients().size());
    }

    @Test
    void testRemoveIngredientNotThere() {
        testList.addIngredient(cheese);
        testList.addIngredient(crackers);
        testList.removeIngredient("Blecke");
        assertEquals(2, testList.getIngredients().size());
        assertEquals(cheese, testList.getIngredients().get(0));
        assertEquals(crackers, testList.getIngredients().get(1));
    }


}
