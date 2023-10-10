package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PantryTest extends IngredientListTest{


    @BeforeEach
    void createIngredients() {
        testList = new Pantry();
        cheese = new Ingredient("Cheese",500,"grams");
        crackers = new Ingredient("Crackers", 10, "crackers");
    }

    @Test
    void testConstructor() {
        assertEquals(0,testList.getIngredients().size());
    }
}