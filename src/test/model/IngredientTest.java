package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IngredientTest {
    private Ingredient testIngredient;

    @BeforeEach
    void setup(){
        testIngredient = new Ingredient("Garlic",2,"cloves");
    }

    @Test
    void testConstructior(){
        assertEquals("Garlic",testIngredient.getName());
        assertEquals(2,testIngredient.getQuantity());
        assertEquals("cloves",testIngredient.getUnits());
    }



}
