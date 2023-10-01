package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CookBookTest {

    private CookBook testCookBook;
    private Recipe testRecipe;
    private Ingredient cheese;
    private Ingredient crackers;


    @BeforeEach
    void setup() {
        testCookBook = new CookBook();
        testRecipe = new Recipe("Cheese+Crackers");
        cheese = new Ingredient("Cheese",500,"grams");
        crackers = new Ingredient("Crackers",10,"crackers");
    }

    @Test
    void constructorTest() {
        assertEquals(0,testCookBook.getPantry().getIngredients().size());
        assertEquals(0,testCookBook.getRecipes().size());
    }
}
