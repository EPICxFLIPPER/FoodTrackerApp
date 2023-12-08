package persistence;

import model.CookBook;
import model.Ingredient;
import model.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest{

    private CookBook cookBook;
    private Ingredient cheese;
    private Ingredient crackers;
    private Ingredient ham;
    private Recipe testRecipeCC;
    private Recipe testRecipeH;

    @BeforeEach
    void setup() {
        //Establish a cookbook
       cookBook = new CookBook();

       //Create test ingredients
       cheese = new Ingredient("Cheese",500,"grams");
       crackers = new Ingredient("Crackers",10,"crackers");
       ham = new Ingredient("ham",20,"lbs");

       //Create a test recipe
       testRecipeCC = new Recipe("Cheese+Crackers");
       testRecipeCC.addIngredient(cheese);
       testRecipeCC.addIngredient(crackers);

       testRecipeH = new Recipe("Ham");
       testRecipeH.addIngredient(ham);
    }


    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyCookBook() {
        try {
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyCookBook.json");
            writer.open();
            writer.write(cookBook);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyCookBook.json");
            cookBook = reader.read();

            assertEquals(0,cookBook.getNumRecipes());
            assertEquals(0,cookBook.getNumIngredientsPantry());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterCookBook1Recipe() {
        try {
            cookBook.addRecipe(testRecipeCC);
            JsonWriter writer = new JsonWriter("./data/testWriter1Recipe.json");
            writer.open();
            writer.write(cookBook);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriter1Recipe.json");
            cookBook = reader.read();

            //The new recipe that should have been read
            Recipe currentRecipe = cookBook.getRecipe("Cheese+Crackers");

            assertEquals(1,cookBook.recipesSize());
            assertEquals(0,cookBook.pantrySize());
            checkRecipe(currentRecipe.getName(),testRecipeCC);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterCookBook2Recipe() {
        try {
            cookBook.addRecipe(testRecipeCC);
            cookBook.addRecipe(testRecipeH);
            JsonWriter writer = new JsonWriter("./data/testWriter2Recipe.json");
            writer.open();
            writer.write(cookBook);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriter2Recipe.json");
            cookBook = reader.read();

            //The new recipe that should have been read at each index
            Recipe currentRecipe0 = cookBook.getRecipe("Cheese+Crackers");
            Recipe currentRecipe1 = cookBook.getRecipe("Ham");

            assertEquals(2,cookBook.recipesSize());
            assertEquals(0,cookBook.pantrySize());
            checkRecipe(currentRecipe0.getName(),testRecipeCC);
            checkRecipe(currentRecipe1.getName(),testRecipeH);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterCookBook1IngInPantry() {
        try {
            cookBook.addToPantry(cheese);
            JsonWriter writer = new JsonWriter("./data/testWriter1IngInPantry.json");
            writer.open();
            writer.write(cookBook);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriter1IngInPantry.json");
            cookBook = reader.read();

            //The Ingredient that should be at 0 in the pantrys ingredients
            Ingredient curr0 = cookBook.getPantry().getIngredients().get("Cheese");

            assertEquals(0,cookBook.recipesSize());
            assertEquals(1,cookBook.pantrySize());
            checkIngredient(curr0,cheese);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterCookBook2IngInPantry() {
        try {
            cookBook.addToPantry(cheese);
            cookBook.addToPantry(crackers);
            JsonWriter writer = new JsonWriter("./data/testWriter2IngInPantry.json");
            writer.open();
            writer.write(cookBook);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriter2IngInPantry.json");
            cookBook = reader.read();

            //The Ingredient that should be at 0 in the pantrys ingredients
            Ingredient curr0 = cookBook.getPantry().getIngredients().get("Cheese");
            Ingredient curr1 = cookBook.getPantry().getIngredients().get("Crackers");

            assertEquals(0,cookBook.recipesSize());
            assertEquals(2,cookBook.pantrySize());
            checkIngredient(curr0,cheese);
            checkIngredient(curr1,crackers);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    //TODO
    //FINISH TESTS




    private void checkIngredient(Ingredient toCheck, Ingredient correct) {
        checkIngredient(toCheck.getName(),toCheck.getQuantity(),toCheck.getUnits(),correct);
    }


}

