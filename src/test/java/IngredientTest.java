import org.junit.Test;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;

public class IngredientTest {

    @Test
    public void ingredientGetTypeSaucePositiveResult() {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "ingredient name", 100);
        String actual = ingredient.getType().name();
        String expected = "SAUCE";
        assertEquals("Kind of type does not match", expected, actual);
    }

    @Test
    public void ingredientGetTypeFillingPositiveResult() {
        Ingredient ingredient = new Ingredient(IngredientType.FILLING, "ingredient name", 100);
        String actual = ingredient.getType().name();
        String expected = "FILLING";
        assertEquals("Kind of type does not match", expected, actual);
    }

    @Test
    public void ingredientGetNamePositiveResult() {
        Ingredient ingredient = new Ingredient(IngredientType.FILLING, "super filling", 100);
        String actual = ingredient.getName();
        String expected = "super filling";
        assertEquals("Names of ingredient does not match", expected, actual);
    }

    @Test
    public void ingredientGetPricePositiveResult() {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "hot sauce", 999.99f);
        float actual = ingredient.getPrice();
        float expected = 999.99f;
        assertEquals("Price of ingredient does not match", expected, actual, 0);
    }
}