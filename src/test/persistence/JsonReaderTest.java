package persistence;

import model.CookBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {


    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            CookBook cookBook = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testWriterEmptyCookBook.json");
        try {
            CookBook cookBook = reader.read();
            assertEquals(0,cookBook.getNumRecipes());
            assertEquals(0,cookBook.getNumIngredientsPantry());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReader1Recipe() {
        JsonReader reader = new JsonReader("./data/testWriter1Recipe.json");
        try {
            CookBook cookBook = reader.read();
            assertEquals(0,cookBook.getNumIngredientsPantry());
            assertEquals(1,cookBook.getNumRecipes());
            checkRecipe("Cheese+Crackers",cookBook.getRecipeAtIndex(0));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
