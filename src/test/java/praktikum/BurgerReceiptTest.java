package praktikum;

import org.junit.Test;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BurgerReceiptTest {

    // Проверка отображения булочки в чеке дважды
    @Test
    public void shouldWrapReceiptWithBunName() {
        Bun bun = mock(Bun.class);
        when(bun.getName()).thenReturn("Краторная");
        when(bun.getPrice()).thenReturn(100f);

        Burger burger = new Burger();
        burger.setBuns(bun);

        String receipt = burger.getReceipt();

        assertTrue(receipt.startsWith("(==== Краторная ====)"));
        assertNotEquals(receipt.indexOf("Краторная"), receipt.lastIndexOf("Краторная"));
    }
    // Формат строки ингредиента
    @Test
    public void shouldFormatIngredientLine() {
        Bun bun = mock(Bun.class);
        when(bun.getName()).thenReturn("Краторная");
        when(bun.getPrice()).thenReturn(100f);

        Ingredient ingredient = mock(Ingredient.class);
        when(ingredient.getType()).thenReturn(IngredientType.FILLING);
        when(ingredient.getName()).thenReturn("Котлета");
        when(ingredient.getPrice()).thenReturn(50f);

        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        String receipt = burger.getReceipt();

        assertTrue(receipt.contains("Краторная"));
        assertTrue(receipt.contains("= filling Котлета ="));
    }
    // Порядок ингредиентов в чеке
    @Test
    public void shouldKeepIngredientOrderInReceipt() {
        Bun bun = mock(Bun.class);
        when(bun.getName()).thenReturn("Краторная");
        when(bun.getPrice()).thenReturn(100f);

        Ingredient ingredientMeat = mock(Ingredient.class);
        when(ingredientMeat.getType()).thenReturn(IngredientType.FILLING);
        when(ingredientMeat.getName()).thenReturn("A");
        when(ingredientMeat.getPrice()).thenReturn(10f);

        Ingredient ingredientSoup = mock(Ingredient.class);
        when(ingredientSoup.getType()).thenReturn(IngredientType.FILLING);
        when(ingredientSoup.getName()).thenReturn("B");
        when(ingredientSoup.getPrice()).thenReturn(15f);

        Ingredient ingredientCoffee = mock(Ingredient.class);
        when(ingredientCoffee.getType()).thenReturn(IngredientType.FILLING);
        when(ingredientCoffee.getName()).thenReturn("C");
        when(ingredientCoffee.getPrice()).thenReturn(25f);

        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(ingredientMeat);
        burger.addIngredient(ingredientSoup);
        burger.addIngredient(ingredientCoffee);

        String receipt = burger.getReceipt();

        assertTrue(receipt.indexOf("A") < receipt.indexOf("B"));
        assertTrue(receipt.indexOf("B") < receipt.indexOf("C"));
    }
    // Итоговая цена в чеке
    @Test
    public void shouldContainPriceLine() {
        Bun bun = mock(Bun.class);
        when(bun.getName()).thenReturn("Краторная");
        when(bun.getPrice()).thenReturn(100f);

        Ingredient ingredient = mock(Ingredient.class);
        when(ingredient.getType()).thenReturn(IngredientType.FILLING);
        when(ingredient.getName()).thenReturn("Котлета");
        when(ingredient.getPrice()).thenReturn(50f);

        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("Price: 250"));
    }
}
