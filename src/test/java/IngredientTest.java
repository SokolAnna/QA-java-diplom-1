import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class IngredientTest {

    private final IngredientType type;
    private final String expected;

    public IngredientTest(IngredientType type, String expected) {
        this.type = type;
        this.expected = expected;
    }

    @Parameterized.Parameters(name = "Test ingredient type: {0}")
    public static Object[][] getIngredientType() {
        return new Object[][]{
                {IngredientType.SAUCE, "SAUCE"},
                {IngredientType.FILLING, "FILLING"},
        };
    }

    @Test
    public void ingredientGetTypePositiveResult() {
        Ingredient ingredient = new Ingredient(type, "ingredient name", 100);
        String actual = ingredient.getType().name();
        assertEquals("Kind of type does not match", expected, actual);
    }

    @Test
    public void ingredientGetNamePositiveResult() {
        Ingredient ingredient = new Ingredient(type, "ingredient name", 100);
        String actual = ingredient.getName();
        String expected = "ingredient name";
        assertEquals("Names of ingredient does not match", expected, actual);
    }

    @Test
    public void ingredientGetPricePositiveResult() {
        Ingredient ingredient = new Ingredient(type, "hot sauce", 100);
        int actual = (int) ingredient.getPrice();
        int expected = 100;
        assertEquals("Price of ingredient does not match", expected, actual);
    }
}
