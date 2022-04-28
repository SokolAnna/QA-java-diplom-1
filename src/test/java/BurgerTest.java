import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import static org.junit.Assert.assertEquals;
import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;

@RunWith(MockitoJUnitRunner.class)

public class BurgerTest {

    @Mock
    Bun bun;
    @Mock
    Ingredient ingredient1;
    @Mock
    Ingredient ingredient2;

    @Test
    public void burgerSetBunsPositiveResult() {
        Burger burger = new Burger();
        burger.setBuns(bun);
        Bun actual = burger.bun;
        Bun expected = bun;
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
        Mockito.when(ingredient2.getPrice()).thenReturn(300f);

        float sum = (bun.getPrice() * 2) + ingredient1.getPrice() + ingredient2.getPrice();
        String actual = burger.getReceipt();
        String expected = String.format("(==== black bun ====)%n" + "= sauce sour cream =%n" + "= filling cutlet =%n" + "(==== black bun ====)%n" + "%nPrice: %f%n", sum);
        assertEquals("Receipt doesn't match", expected, actual);
    }
}