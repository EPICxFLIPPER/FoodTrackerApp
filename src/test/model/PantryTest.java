package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PantryTest {
    private Pantry testPantry;
    private Ingredient cheese;
    private Ingredient crackers;

    @BeforeEach
    void setup() {
        testPantry = new Pantry();
        cheese = new Ingredient("Cheese", 500, "grams");
        crackers = new Ingredient("Crackers", 10, "crackers");
    }

    @Test
    void testConstructor() {
        assertEquals(0,testPantry.getIngredients().size());
    }
}