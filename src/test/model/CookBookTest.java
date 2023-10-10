package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CookBookTest {

    private CookBook testCookBook;
    private Recipe testRecipe1;
    private Recipe testRecipe2;
    private Ingredient cheese500;
    private Ingredient crackers10;
    private Ingredient goldfish20;


    @BeforeEach
    void setup() {
        testCookBook = new CookBook();
        testRecipe1 = new Recipe("Cheese+Crackers");
        testRecipe2 = new Recipe("Goldfish");
        cheese500 = new Ingredient("Cheese", 500, "grams");
        crackers10 = new Ingredient("Crackers", 10, "crackers");
        goldfish20 = new Ingredient("Goldfish", 20, "fish");
    }

    @Test
    void constructorTest() {
        assertEquals(0, testCookBook.getPantry().getIngredients().size());
        assertEquals(0, testCookBook.getRecipes().size());
    }

    @Test
    void addRecipeOnceTest() {
        testCookBook.addRecipe(testRecipe1);
        assertEquals(1, testCookBook.getRecipes().size());
        assertEquals(testRecipe1, testCookBook.getRecipes().get(0));
    }

    @Test
    void addRecipeMultipleTest() {
        testCookBook.addRecipe(testRecipe1);
        testCookBook.addRecipe(testRecipe2);
        assertEquals(2, testCookBook.getRecipes().size());
        assertEquals(testRecipe1, testCookBook.getRecipes().get(0));
        assertEquals(testRecipe2, testCookBook.getRecipes().get(1));
    }

    @Test
    void removeRecipeOnceTest() {
        testCookBook.addRecipe(testRecipe1);
        testCookBook.removeRecipe("Cheese+Crackers");
        assertEquals(0, testCookBook.getRecipes().size());
    }

    @Test
    void removeRecipeMultipleTest() {
        testCookBook.addRecipe(testRecipe1);
        testCookBook.addRecipe(testRecipe2);
        testCookBook.removeRecipe("Cheese+Crackers");
        testCookBook.removeRecipe("Goldfish");
        assertEquals(0, testCookBook.getRecipes().size());
    }

    @Test
    void removeRecipeAddTwoSubOneTest() {
        testCookBook.addRecipe(testRecipe1);
        testCookBook.addRecipe(testRecipe2);
        testCookBook.removeRecipe("Cheese+Crackers");
        assertEquals(testRecipe2,testCookBook.getRecipes().get(0));
        assertEquals(1, testCookBook.getRecipes().size());
    }

    @Test
    void canCookRecipeNoIngredientsTest() {
        testCookBook.addRecipe(testRecipe1);
        assertTrue(testCookBook.canCookRecipe("Cheese+Crackers"));
    }

    @Test
    void canCookRecipeTooFewIngredientsTest() {
        Ingredient cheese100 = new Ingredient("Cheese",100,"grams");
        testRecipe1.addIngredient(cheese500);
        testCookBook.addRecipe(testRecipe1);
        testCookBook.addToPantry(cheese100);

        assertFalse(testCookBook.canCookRecipe("Cheese+Crackers"));
    }

    @Test
    void canCookRecipeMissingIngredientTest() {
        Ingredient cheese100 = new Ingredient("Cheese",100,"grams");
        testRecipe1.addIngredient(cheese500);
        testRecipe1.addIngredient(crackers10);
        testCookBook.addRecipe(testRecipe1);
        testCookBook.addToPantry(cheese500);

        assertFalse(testCookBook.canCookRecipe("Cheese+Crackers"));
    }

    @Test
    void canCookRecipePerfectIngredientsTest() {
        Ingredient cheese100 = new Ingredient("Cheese",100,"grams");
        testRecipe1.addIngredient(cheese500);
        testRecipe1.addIngredient(crackers10);
        testCookBook.addRecipe(testRecipe1);
        testCookBook.addToPantry(crackers10);
        testCookBook.addToPantry(cheese500);

        assertTrue(testCookBook.canCookRecipe("Cheese+Crackers"));
    }

    @Test
    void canCookRecipeExcessIngredientsTest() {
        Ingredient cheese100 = new Ingredient("Cheese",100,"grams");
        testRecipe1.addIngredient(cheese100);
        testRecipe1.addIngredient(crackers10);
        testCookBook.addRecipe(testRecipe1);
        testCookBook.addToPantry(crackers10);
        testCookBook.addToPantry(cheese500);

        assertTrue(testCookBook.canCookRecipe("Cheese+Crackers"));
    }

    @Test
    void itemsToCookNoItemsTest(){
        testRecipe1.addIngredient(cheese500);
        testRecipe1.addIngredient(crackers10);
        testCookBook.addRecipe(testRecipe1);
        assertEquals(2,testCookBook.itemsToCook("Cheese+Crackers").size());
        assertEquals("Cheese",testCookBook.itemsToCook("Cheese+Crackers").get(0));
        assertEquals("Crackers",testCookBook.itemsToCook("Cheese+Crackers").get(1));
    }

    @Test
    void itemsToCookOneItemTest(){
        testRecipe1.addIngredient(cheese500);
        testRecipe1.addIngredient(crackers10);
        testCookBook.addToPantry(cheese500);
        testCookBook.addRecipe(testRecipe1);
        assertEquals(1,testCookBook.itemsToCook("Cheese+Crackers").size());
        assertEquals("Crackers",testCookBook.itemsToCook("Cheese+Crackers").get(0));
    }

    @Test
    void itemsToCookAllItemTest(){
        testRecipe1.addIngredient(cheese500);
        testRecipe1.addIngredient(crackers10);
        testCookBook.addToPantry(cheese500);
        testCookBook.addToPantry(crackers10);
        testCookBook.addRecipe(testRecipe1);
        assertEquals(0,testCookBook.itemsToCook("Cheese+Crackers").size());
    }

    @Test
    void itemsToCookTooLittleQuantity(){
        testRecipe1.addIngredient(cheese500);
        testRecipe1.addIngredient(crackers10);
        Ingredient cheese100 = new Ingredient("Cheese",100,"grams");
        testCookBook.addToPantry(cheese100);
        testCookBook.addRecipe(testRecipe1);
        assertEquals(2,testCookBook.itemsToCook("Cheese+Crackers").size());
        assertEquals("Cheese",testCookBook.itemsToCook("Cheese+Crackers").get(0));
        assertEquals("Crackers",testCookBook.itemsToCook("Cheese+Crackers").get(1));

    }

    @Test
    void removeFromPantryTestOnce() {
        testCookBook.addToPantry(cheese500);
        testCookBook.removeFromPantry("Cheese");
        assertEquals(0,testCookBook.getPantry().getIngredients().size());
    }

    @Test
    void removeFromPantryTestMultiple() {
        testCookBook.addToPantry(cheese500);
        testCookBook.addToPantry(crackers10);
        testCookBook.removeFromPantry("Cheese");
        testCookBook.removeFromPantry("Crackers");
        assertEquals(0,testCookBook.getPantry().getIngredients().size());
    }

    @Test
    void removeFromPantryTestItemNotInPantry() {
        testCookBook.addToPantry(cheese500);
        testCookBook.addToPantry(crackers10);
        testCookBook.removeFromPantry("blech");
        assertEquals(2,testCookBook.getPantry().getIngredients().size());
        assertEquals(cheese500,testCookBook.getPantry().getIngredients().get(0));
        assertEquals(crackers10,testCookBook.getPantry().getIngredients().get(1));
    }

    @Test
    void removeFromPantryTestNoItemsInPantry() {
        testCookBook.removeFromPantry("blech");
        assertEquals(0,testCookBook.getPantry().getIngredients().size());
    }


}
