package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerPriceTest {

    private float bunPrice;
    private List<Float> ingredientPrices;
    private float expectedTotal;

    public BurgerPriceTest(float bunPrice, List<Float> ingredientPrices, float expectedTotal) {
        this.bunPrice = bunPrice;
        this.ingredientPrices = ingredientPrices;
        this.expectedTotal = expectedTotal;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {100f, List.of(), 200f},
                {100f, List.of(50f), 250f},
                {100f, List.of(50f, 30f, 20f), 300f}
        });
    }

    // Расчёт цены на трёх наборах данных
    @Test
    public void shouldCalculatePrice() {
        Bun bun = mock(Bun.class);
        when(bun.getPrice()).thenReturn(bunPrice);

        Burger burger = new Burger();
        burger.setBuns(bun);

        for (Float price : ingredientPrices) {
            Ingredient ingredient = mock(Ingredient.class);
            when(ingredient.getPrice()).thenReturn(price);
            burger.addIngredient(ingredient);
        }

        assertEquals(expectedTotal, burger.getPrice(), 0.0001f);
    }
}
