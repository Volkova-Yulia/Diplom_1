package praktikum;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BurgerReceiptTest {

    private Burger burgerWithBun() {
        Bun bun = mock(Bun.class);
        when(bun.getName()).thenReturn("Краторная");
        when(bun.getPrice()).thenReturn(100f);

        Burger burger = new Burger();
        burger.setBuns(bun);
        return burger;
    }

    // Проверка отображения булочки в чеке дважды
    @Test
    public void shouldWrapReceiptWithBunName() {
        Burger burger = burgerWithBun();

        String receipt = burger.getReceipt();

        String expected = String.format("(==== Краторная ====)%n(==== Краторная ====)%n%nPrice: %f%n", 200f);
        assertEquals(expected, receipt);
    }

    // Формат строки ингредиента
    @Test
    public void shouldFormatIngredientLine() {
        Burger burger = burgerWithBun();

        Ingredient ingredient = mock(Ingredient.class);
        when(ingredient.getType()).thenReturn(IngredientType.FILLING);
        when(ingredient.getName()).thenReturn("Котлета");
        when(ingredient.getPrice()).thenReturn(50f);
        burger.addIngredient(ingredient);

        String receipt = burger.getReceipt();

        String expected = String.format("(==== Краторная ====)%n= filling Котлета =%n(==== Краторная ====)%n%nPrice: %f%n", 250f);
        assertEquals(expected, receipt);
    }

    // Порядок ингредиентов в чеке
    @Test
    public void shouldKeepIngredientOrderInReceipt() {
        Burger burger = burgerWithBun();

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

        burger.addIngredient(ingredientMeat);
        burger.addIngredient(ingredientSoup);
        burger.addIngredient(ingredientCoffee);

        String receipt = burger.getReceipt();

        String expected = String.format("(==== Краторная ====)%n= filling A =%n= filling B =%n= filling C =%n(==== Краторная ====)%n%nPrice: %f%n", 250f);
        assertEquals(expected, receipt);
    }

    // Итоговая цена в чеке
    @Test
    public void shouldContainPriceLine() {
        Burger burger = burgerWithBun();

        Ingredient ingredient = mock(Ingredient.class);
        when(ingredient.getType()).thenReturn(IngredientType.FILLING);
        when(ingredient.getName()).thenReturn("Котлета");
        when(ingredient.getPrice()).thenReturn(50f);
        burger.addIngredient(ingredient);

        String receipt = burger.getReceipt();

        assertTrue(receipt.contains("Price: 250"));
    }
}
