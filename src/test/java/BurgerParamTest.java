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

@RunWith(Parameterized.class)
public class BurgerParamTest {

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

    public BurgerParamTest(float bunPrice, float ingredient1Price, float ingredient2Price, float sum) {
        this.bunPrice = bunPrice;
        this.ingredient1Price = ingredient1Price;
        this.ingredient2Price = ingredient2Price;
        this.sum = sum;
    }

    @Parameterized.Parameters(name = "Test prices: {0}; {1}; {2} expected: {3}")
    public static Object[][] getIngredientType() {
        return new Object[][]{
                {199f, 250f, 119f, 767f},
                {0.1f, 350.5f, 149.5f, 500.2f},
                {100.99f, 99.99f, 100.01f, 401.98f},
                {0f, 150f, 150f, 300f},
                {200.0f, 0.0f, 0f, 400.0f},
                {1580.59f, 100f, 0f, 3261.18f},
                {-980.99f, 690f, 100f, -1171.98f},
                {100f, -150f, 200f, 250f},
                {189f, 1111f, -20.45f, 1468.55f},
        };
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
        assertEquals("Price sum doesn't match", expected, actual, 0);
    }
}