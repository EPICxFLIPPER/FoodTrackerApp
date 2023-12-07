package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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
        cheese500 = new Ingredient("Cheese", 500, "g");
        crackers10 = new Ingredient("Crackers", 10, "unit");
        goldfish20 = new Ingredient("Goldfish", 20, "unit");
    }

    @Test
    void testConstructor() {
        assertEquals(0, testCookBook.getPantry().getIngredients().size());
        assertEquals(0, testCookBook.getRecipes().size());
    }

    @Test
    void testAddRecipeOnce() {
        testCookBook.addRecipe(testRecipe1);
        assertEquals(1, testCookBook.getRecipes().size());
        assertEquals(testRecipe1, testCookBook.getRecipes().get(0));
    }

    @Test
    void testAddRecipeMultiple() {
        testCookBook.addRecipe(testRecipe1);
        testCookBook.addRecipe(testRecipe2);
        assertEquals(2, testCookBook.getRecipes().size());
        assertEquals(testRecipe1, testCookBook.getRecipes().get(0));
        assertEquals(testRecipe2, testCookBook.getRecipes().get(1));
    }

    @Test
    void testRemoveRecipeOnce() {
        testCookBook.addRecipe(testRecipe1);
        testCookBook.removeRecipe("Cheese+Crackers");
        assertEquals(0, testCookBook.getRecipes().size());
    }

    @Test
    void testRemoveRecipeMultiple() {
        testCookBook.addRecipe(testRecipe1);
        testCookBook.addRecipe(testRecipe2);
        testCookBook.removeRecipe("Cheese+Crackers");
        testCookBook.removeRecipe("Goldfish");
        assertEquals(0, testCookBook.getRecipes().size());
    }

    @Test
    void testRemoveRecipeMultipleRecipeNotThere() {
        testCookBook.addRecipe(testRecipe1);
        testCookBook.addRecipe(testRecipe2);
        testCookBook.removeRecipe("Blech");
        assertEquals(2, testCookBook.getRecipes().size());
        assertEquals(testRecipe1, testCookBook.getRecipes().get(0));
        assertEquals(testRecipe2, testCookBook.getRecipes().get(1));
    }


    @Test
    void testRemoveRecipeAddTwoSubOne() {
        testCookBook.addRecipe(testRecipe1);
        testCookBook.addRecipe(testRecipe2);
        testCookBook.removeRecipe("Cheese+Crackers");
        assertEquals(testRecipe2,testCookBook.getRecipes().get(0));
        assertEquals(1, testCookBook.getRecipes().size());
    }

    @Test
    void testCanCookRecipeNoIngredients() {
        testCookBook.addRecipe(testRecipe1);
        assertTrue(testCookBook.canCookRecipe("Cheese+Crackers"));
    }

    @Test
    void testCanCookRecipeTooFewIngredients() {
        Ingredient cheese100 = new Ingredient("Cheese",100,"g");
        testRecipe1.addIngredient(cheese500);
        testCookBook.addRecipe(testRecipe1);
        testCookBook.addToPantry(cheese100);

        assertFalse(testCookBook.canCookRecipe("Cheese+Crackers"));
    }

    @Test
    void testCanCookRecipeMissingIngredient() {
        Ingredient cheese100 = new Ingredient("Cheese",100,"g");
        testRecipe1.addIngredient(cheese500);
        testRecipe1.addIngredient(crackers10);
        testCookBook.addRecipe(testRecipe1);
        testCookBook.addToPantry(cheese500);

        assertFalse(testCookBook.canCookRecipe("Cheese+Crackers"));
    }

    @Test
    void testCanCookRecipePerfectIngredients() {
        Ingredient cheese100 = new Ingredient("Cheese",100,"g");
        testRecipe1.addIngredient(cheese500);
        testRecipe1.addIngredient(crackers10);
        testCookBook.addRecipe(testRecipe1);
        testCookBook.addToPantry(crackers10);
        testCookBook.addToPantry(cheese500);

        assertTrue(testCookBook.canCookRecipe("Cheese+Crackers"));
    }

    @Test
    void testCanCookRecipeExcessIngredients() {
        Ingredient cheese100 = new Ingredient("Cheese",100,"g");
        testRecipe1.addIngredient(cheese100);
        testRecipe1.addIngredient(crackers10);
        testCookBook.addRecipe(testRecipe1);
        testCookBook.addToPantry(crackers10);
        testCookBook.addToPantry(cheese500);

        assertTrue(testCookBook.canCookRecipe("Cheese+Crackers"));
    }

    @Test
    void testItemsToCookNoItems(){
        testRecipe1.addIngredient(cheese500);
        testRecipe1.addIngredient(crackers10);
        testCookBook.addRecipe(testRecipe1);
        assertEquals(2,testCookBook.itemsToCook("Cheese+Crackers").size());
        assertEquals("Cheese",testCookBook.itemsToCook("Cheese+Crackers").get(0));
        assertEquals("Crackers",testCookBook.itemsToCook("Cheese+Crackers").get(1));
    }

    @Test
    void testItemsToCookOneItem(){
        testRecipe1.addIngredient(cheese500);
        testRecipe1.addIngredient(crackers10);
        testCookBook.addToPantry(cheese500);
        testCookBook.addRecipe(testRecipe1);
        assertEquals(1,testCookBook.itemsToCook("Cheese+Crackers").size());
        assertEquals("Crackers",testCookBook.itemsToCook("Cheese+Crackers").get(0));
    }

    @Test
    void testItemsToCookAllItem(){
        testRecipe1.addIngredient(cheese500);
        testRecipe1.addIngredient(crackers10);
        testCookBook.addToPantry(cheese500);
        testCookBook.addToPantry(crackers10);
        testCookBook.addRecipe(testRecipe1);
        assertEquals(0,testCookBook.itemsToCook("Cheese+Crackers").size());
    }

    @Test
    void testItemsToCookTooLittleQuantity(){
        testRecipe1.addIngredient(cheese500);
        testRecipe1.addIngredient(crackers10);
        Ingredient cheese100 = new Ingredient("Cheese",100,"g");
        testCookBook.addToPantry(cheese100);
        testCookBook.addRecipe(testRecipe1);
        assertEquals(2,testCookBook.itemsToCook("Cheese+Crackers").size());
        assertEquals("Cheese",testCookBook.itemsToCook("Cheese+Crackers").get(0));
        assertEquals("Crackers",testCookBook.itemsToCook("Cheese+Crackers").get(1));

    }

    @Test
    void testRemoveFromPantryOnce() {
        testCookBook.addToPantry(cheese500);
        testCookBook.removeFromPantry("Cheese");
        assertEquals(0,testCookBook.getPantry().getIngredients().size());
    }

    @Test
    void testRemoveFromPantryMultiple() {
        testCookBook.addToPantry(cheese500);
        testCookBook.addToPantry(crackers10);
        testCookBook.removeFromPantry("Cheese");
        testCookBook.removeFromPantry("Crackers");
        assertEquals(0,testCookBook.getPantry().getIngredients().size());
    }

    @Test
    void testRemoveFromPantryItemNotInPantry() {
        testCookBook.addToPantry(cheese500);
        testCookBook.addToPantry(crackers10);
        testCookBook.removeFromPantry("blech");
        assertEquals(2,testCookBook.getPantry().getIngredients().size());
        assertEquals(cheese500,testCookBook.getPantry().getIngredients().get(0));
        assertEquals(crackers10,testCookBook.getPantry().getIngredients().get(1));
    }

    @Test
    void testRemoveFromPantryNoItemsInPantry() {
        testCookBook.removeFromPantry("blech");
        assertEquals(0,testCookBook.getPantry().getIngredients().size());
    }

    @Test
    void testCanCookRecipeRecipeNotThere() {
        boolean rsf = testCookBook.canCookRecipe("blech");
        assertTrue(rsf);
    }

    @Test
    void testItemsToCookRecipeNotThere() {
        ArrayList<String> rsf = testCookBook.itemsToCook("clagkjs");
        assertEquals(0,rsf.size());
    }

    @Test
    void testCanCookComparableIngredientsTrue() {
        testRecipe1.addIngredient(cheese500);
        testCookBook.addToPantry(new Ingredient("Cheese",1,"kg"));
        testCookBook.addRecipe(testRecipe1);
        assertTrue(testCookBook.canCookRecipe("Cheese+Crackers"));
    }

    @Test
    void testCanCookComparableIngredientsFalse() {
        testRecipe1.addIngredient(cheese500);
        testCookBook.addToPantry(new Ingredient("Cheese",1,"mg"));
        testCookBook.addRecipe(testRecipe1);
        assertFalse(testCookBook.canCookRecipe("Cheese+Crackers"));
    }

    @Test
    void testCanCookNotComparableIngredients() {
        testRecipe1.addIngredient(cheese500);
        testCookBook.addToPantry(new Ingredient("Cheese",1,"unit"));
        testCookBook.addRecipe(testRecipe1);
        assertFalse(testCookBook.canCookRecipe("Cheese+Crackers"));
    }


}
