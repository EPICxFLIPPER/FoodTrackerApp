package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CookBookTest {

    private CookBook testCookBook;
    private Recipe testCheeseCrackers;
    private Recipe testGoldfish;
    private Ingredient cheese500g;
    private Ingredient crackers10;
    private Ingredient goldfish20;


    @BeforeEach
    void setup() {
        testCookBook = new CookBook();
        testCheeseCrackers = new Recipe("Cheese+Crackers");
        testGoldfish = new Recipe("Goldfish");
        cheese500g = new Ingredient("Cheese", 500, "g");
        crackers10 = new Ingredient("Crackers", 10, "unit");
        goldfish20 = new Ingredient("Goldfish", 20, "unit");
    }

    @Test
    void testConstructor() {
        assertEquals(0, testCookBook.pantrySize());
        assertEquals(0, testCookBook.recipesSize());
    }

    @Test
    void testAddRecipeOnce() {
        testCookBook.addRecipe(testCheeseCrackers);
        assertEquals(1, testCookBook.recipesSize());
        assertEquals(testCheeseCrackers, testCookBook.getRecipes().get("Cheese+Crackers"));
    }

    @Test
    void testAddRecipeMultiple() {
        testCookBook.addRecipe(testCheeseCrackers);
        testCookBook.addRecipe(testGoldfish);
        assertEquals(2, testCookBook.recipesSize());
        assertEquals(testCheeseCrackers, testCookBook.getRecipe("Cheese+Crackers"));
        assertEquals(testGoldfish, testCookBook.getRecipe("Goldfish"));
    }

    @Test
    void testRemoveRecipeOnce() {
        testCookBook.addRecipe(testCheeseCrackers);
        testCookBook.removeRecipe("Cheese+Crackers");
        assertEquals(0, testCookBook.getRecipes().size());
    }

    @Test
    void testRemoveRecipeMultiple() {
        testCookBook.addRecipe(testCheeseCrackers);
        testCookBook.addRecipe(testGoldfish);
        testCookBook.removeRecipe("Cheese+Crackers");
        testCookBook.removeRecipe("Goldfish");
        assertEquals(0, testCookBook.getRecipes().size());
    }

    @Test
    void testRemoveRecipeMultipleRecipeNotThere() {
        testCookBook.addRecipe(testCheeseCrackers);
        testCookBook.addRecipe(testGoldfish);
        testCookBook.removeRecipe("Blech");
        assertEquals(2, testCookBook.recipesSize());
        assertEquals(testCheeseCrackers, testCookBook.getRecipe("Cheese+Crackers"));
        assertEquals(testGoldfish, testCookBook.getRecipe("Goldfish"));
    }


    @Test
    void testRemoveRecipeAddTwoSubOne() {
        testCookBook.addRecipe(testCheeseCrackers);
        testCookBook.addRecipe(testGoldfish);
        testCookBook.removeRecipe("Cheese+Crackers");
        assertEquals(testGoldfish,testCookBook.getRecipe("Goldfish"));
        assertEquals(1, testCookBook.recipesSize());
    }

    @Test
    void testCanCookRecipeNoIngredients() {
        testCookBook.addRecipe(testCheeseCrackers);
        assertTrue(testCookBook.canCookRecipe("Cheese+Crackers"));
    }

    @Test
    void testCanCookRecipeTooFewIngredients() {
        Ingredient cheese100 = new Ingredient("Cheese",100,"g");
        testCheeseCrackers.addIngredient(cheese500g);
        testCookBook.addRecipe(testCheeseCrackers);
        testCookBook.addToPantry(cheese100);

        assertFalse(testCookBook.canCookRecipe("Cheese+Crackers"));
    }

    @Test
    void testCanCookRecipeMissingIngredient() {
        Ingredient cheese100 = new Ingredient("Cheese",100,"g");
        testCheeseCrackers.addIngredient(cheese500g);
        testCheeseCrackers.addIngredient(crackers10);
        testCookBook.addRecipe(testCheeseCrackers);
        testCookBook.addToPantry(cheese500g);

        assertFalse(testCookBook.canCookRecipe("Cheese+Crackers"));
    }

    @Test
    void testCanCookRecipePerfectIngredients() {
        Ingredient cheese100 = new Ingredient("Cheese",100,"g");
        testCheeseCrackers.addIngredient(cheese500g);
        testCheeseCrackers.addIngredient(crackers10);
        testCookBook.addRecipe(testCheeseCrackers);
        testCookBook.addToPantry(crackers10);
        testCookBook.addToPantry(cheese500g);

        assertTrue(testCookBook.canCookRecipe("Cheese+Crackers"));
    }

    @Test
    void testCanCookRecipeExcessIngredients() {
        Ingredient cheese100 = new Ingredient("Cheese",100,"g");
        testCheeseCrackers.addIngredient(cheese100);
        testCheeseCrackers.addIngredient(crackers10);
        testCookBook.addRecipe(testCheeseCrackers);
        testCookBook.addToPantry(crackers10);
        testCookBook.addToPantry(cheese500g);

        assertTrue(testCookBook.canCookRecipe("Cheese+Crackers"));
    }

    @Test
    void testItemsToCookNoItems(){
        testCheeseCrackers.addIngredient(cheese500g);
        testCheeseCrackers.addIngredient(crackers10);
        testCookBook.addRecipe(testCheeseCrackers);
        assertEquals(2,testCookBook.itemsToCook("Cheese+Crackers").size());
        assertEquals("Cheese",testCookBook.itemsToCook("Cheese+Crackers").get(0));
        assertEquals("Crackers",testCookBook.itemsToCook("Cheese+Crackers").get(1));
    }

    @Test
    void testItemsToCookOneItem(){
        testCheeseCrackers.addIngredient(cheese500g);
        testCheeseCrackers.addIngredient(crackers10);
        testCookBook.addToPantry(cheese500g);
        testCookBook.addRecipe(testCheeseCrackers);
        assertEquals(1,testCookBook.itemsToCook("Cheese+Crackers").size());
        assertEquals("Crackers",testCookBook.itemsToCook("Cheese+Crackers").get(0));
    }

    @Test
    void testItemsToCookAllItem(){
        testCheeseCrackers.addIngredient(cheese500g);
        testCheeseCrackers.addIngredient(crackers10);
        testCookBook.addToPantry(cheese500g);
        testCookBook.addToPantry(crackers10);
        testCookBook.addRecipe(testCheeseCrackers);
        assertEquals(0,testCookBook.itemsToCook("Cheese+Crackers").size());
    }

    @Test
    void testItemsToCookTooLittleQuantity(){
        testCheeseCrackers.addIngredient(cheese500g);
        testCheeseCrackers.addIngredient(crackers10);
        Ingredient cheese100 = new Ingredient("Cheese",100,"g");
        testCookBook.addToPantry(cheese100);
        testCookBook.addRecipe(testCheeseCrackers);
        assertEquals(2,testCookBook.itemsToCook("Cheese+Crackers").size());
        assertEquals("Cheese",testCookBook.itemsToCook("Cheese+Crackers").get(0));
        assertEquals("Crackers",testCookBook.itemsToCook("Cheese+Crackers").get(1));

    }

    @Test
    void testRemoveFromPantryOnce() {
        testCookBook.addToPantry(cheese500g);
        testCookBook.removeFromPantry("Cheese");
        assertEquals(0,testCookBook.pantrySize());
    }

    @Test
    void testRemoveFromPantryMultiple() {
        testCookBook.addToPantry(cheese500g);
        testCookBook.addToPantry(crackers10);
        testCookBook.removeFromPantry("Cheese");
        testCookBook.removeFromPantry("Crackers");
        assertEquals(0,testCookBook.pantrySize());
    }

    @Test
    void testRemoveFromPantryItemNotInPantry() {
        testCookBook.addToPantry(cheese500g);
        testCookBook.addToPantry(crackers10);
        testCookBook.removeFromPantry("blech");
        assertEquals(2,testCookBook.pantrySize());
        assertEquals(cheese500g,testCookBook.getPantry().getIngredients().get("Cheese"));
        assertEquals(crackers10,testCookBook.getPantry().getIngredients().get("Crackers"));
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
        testCheeseCrackers.addIngredient(cheese500g);
        testCookBook.addToPantry(new Ingredient("Cheese",1,"kg"));
        testCookBook.addRecipe(testCheeseCrackers);
        assertTrue(testCookBook.canCookRecipe("Cheese+Crackers"));
    }

    @Test
    void testCanCookComparableIngredientsFalse() {
        testCheeseCrackers.addIngredient(cheese500g);
        testCookBook.addToPantry(new Ingredient("Cheese",1,"mg"));
        testCookBook.addRecipe(testCheeseCrackers);
        assertFalse(testCookBook.canCookRecipe("Cheese+Crackers"));
    }

    @Test
    void testCanCookNotComparableIngredients() {
        testCheeseCrackers.addIngredient(cheese500g);
        testCookBook.addToPantry(new Ingredient("Cheese",1,"unit"));
        testCookBook.addRecipe(testCheeseCrackers);
        assertFalse(testCookBook.canCookRecipe("Cheese+Crackers"));
    }


}
