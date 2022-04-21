import org.junit.Test;
import praktikum.Bun;

import static org.junit.Assert.assertEquals;

public class BunTest {

    @Test
    public void bunGetNamePositiveResult() {
        Bun bun = new Bun("black bun", 100);
        String actual = bun.getName();
        String expected = "black bun";
        assertEquals("Names of buns does not match", expected, actual);
    }

    @Test
    public void bunGetPricePositiveResult() {
        Bun bun = new Bun("black bun", 100);
        int actual = (int) bun.getPrice();
        int expected = 100;
        assertEquals("Price of buns does not match", expected, actual);
    }
}
