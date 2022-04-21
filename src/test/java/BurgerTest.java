import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import static org.junit.Assert.assertEquals;
import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;

@RunWith(Parameterized.class)

public class BurgerTest {

    @Before
    public void createMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Mock
    Bun bun;
    @Mock
    Ingredient ingredient1;
    @Mock
    Ingredient ingredient2;

    private final float bunPrice;
    private final float ingredient1Price;
    private final float ingredient2Price;
    private final float sum;

    public BurgerTest(float bunPrice, float ingredient1Price, float ingredient2Price, float sum) {
        this.bunPrice = bunPrice;
        this.ingredient1Price = ingredient1Price;
        this.ingredient2Price = ingredient2Price;
        this.sum = sum;
    }

    @Parameterized.Parameters(name = "Test bun price: {0}, ingredient 1 price: {1}, ingredient 2 price: {2}")
    public static Object[][] getIngredientType() {
        return new Object[][]{
                {100f, 200f, 100f, 500f},
                {0f, 150f, 150f, 300f},
                {200.0f, 0.0f, 0.0f, 400.0f},
                {100.99f, 200.99f, 100.01f, 502.98f},
                {-100f, 100f, 200, 100f},
        };
    }

    @Test
    public void burgerSetBunsPositiveResult() {
        Burger burger = new Burger();
        burger.setBuns(bun);
        Mockito.when(burger.bun.getName()).thenReturn("тесто");
        String actual = burger.bun.getName();
        String expected = "тесто";
        assertEquals("setBuns: bun not added", expected, actual);
    }

    @Test
    public void burgerAddIngredientPositiveResult() {
        Burger burger = new Burger();
        burger.addIngredient(ingredient1);
        int actual = burger.ingredients.size();
        int expected = 1;
        assertEquals("The ingredient not added", expected, actual);
    }

    @Test
    public void burgerRemoveIngredientPositiveResult() {
        Burger burger = new Burger();
        burger.addIngredient(ingredient1);
        burger.removeIngredient(0);
        boolean actual = burger.ingredients.isEmpty();
        boolean expected = true;
        assertEquals("The ingredient not removed", expected, actual);
    }

    @Test
    public void burgerMoveIngredientPositiveResult() {
        Burger burger = new Burger();
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.moveIngredient(1, 0);
        Ingredient actual = burger.ingredients.get(0);
        Ingredient expected = ingredient2;
        assertEquals("The ingredient not moved", expected, actual);
    }

    @Test
    public void burgerGetPricePositiveResult() {
        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        Mockito.when(bun.getPrice()).thenReturn(bunPrice);
        Mockito.when(ingredient1.getPrice()).thenReturn(ingredient1Price);
        Mockito.when(ingredient2.getPrice()).thenReturn(ingredient2Price);
        float actual = burger.getPrice();
        float expected = sum;
        assertEquals("Price doesn't match", expected, actual, 0);
    }

    @Test
    public void burgerGetReceiptPositiveResult() {
        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        Mockito.when(bun.getName()).thenReturn("black bun");
        Mockito.when(bun.getPrice()).thenReturn(100f);

        Mockito.when(ingredient1.getType()).thenReturn(SAUCE);
        Mockito.when(ingredient1.getName()).thenReturn("sour cream");
        Mockito.when(ingredient1.getPrice()).thenReturn(200f);

        Mockito.when(ingredient2.getType()).thenReturn(FILLING);
        Mockito.when(ingredient2.getName()).thenReturn("cutlet");
        Mockito.when(ingredient2.getPrice()).thenReturn(100f);

        String actual = burger.getReceipt();
        String expected = String.format("(==== black bun ====)%n" + "= sauce sour cream =%n" + "= filling cutlet =%n" + "(==== black bun ====)%n" + "%nPrice: 500,000000%n");
        assertEquals("Receipt doesn't match", expected, actual);
    }
}
