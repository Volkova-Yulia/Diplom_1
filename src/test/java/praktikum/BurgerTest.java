package praktikum;

import org.junit.*;
import org.mockito.*;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class BurgerTest {

    @Mock
    private Bun mockBun;

    @Mock
    private Ingredient mockIngredient;

    private Burger burger;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        burger = new Burger();
    }

    // Установка булочки
    @Test
    public void shouldSetBun() {
        burger.setBuns(mockBun);
        assertSame(mockBun, burger.bun);
    }

    // Добавление ингредиента
    @Test
    public void shouldAddIngredient() {
        burger.addIngredient(mockIngredient);
        assertEquals(Collections.singletonList(mockIngredient), burger.ingredients);
    }

    // Порядок при добавлении
    @Test
    public void shouldKeepOrderAfterAdd() {
        Ingredient first = mock(Ingredient.class);
        Ingredient second = mock(Ingredient.class);

        burger.addIngredient(first);
        burger.addIngredient(second);

        assertEquals(Arrays.asList(first, second), burger.ingredients);
    }

    // Удаление из середины
    @Test
    public void shouldRemoveByIndex() {
        Ingredient first = mock(Ingredient.class);
        Ingredient second = mock(Ingredient.class);
        Ingredient third = mock(Ingredient.class);

        burger.addIngredient(first);
        burger.addIngredient(second);
        burger.addIngredient(third);

        burger.removeIngredient(1);

        assertEquals(Arrays.asList(first, third), burger.ingredients);
    }

    // Удаление первого
    @Test
    public void shouldRemoveFirst() {
        Ingredient first = mock(Ingredient.class);
        Ingredient second = mock(Ingredient.class);
        Ingredient third = mock(Ingredient.class);

        burger.addIngredient(first);
        burger.addIngredient(second);
        burger.addIngredient(third);

        burger.removeIngredient(0);

        assertEquals(Arrays.asList(second, third), burger.ingredients);
    }

    // Удаление последнего
    @Test
    public void shouldRemoveLast() {
        Ingredient first = mock(Ingredient.class);
        Ingredient second = mock(Ingredient.class);
        Ingredient third = mock(Ingredient.class);

        burger.addIngredient(first);
        burger.addIngredient(second);
        burger.addIngredient(third);

        burger.removeIngredient(2);

        assertEquals(Arrays.asList(first, second), burger.ingredients);
    }

    // Перемещение вперёд
    @Test
    public void shouldMoveIngredientForward() {
        Ingredient first = mock(Ingredient.class);
        Ingredient second = mock(Ingredient.class);
        Ingredient third = mock(Ingredient.class);

        burger.addIngredient(first);
        burger.addIngredient(second);
        burger.addIngredient(third);

        burger.moveIngredient(0, 2);

        assertEquals(Arrays.asList(second, third, first), burger.ingredients);
    }

    // Перемещение назад
    @Test
    public void shouldMoveIngredientBackward() {
        Ingredient first = mock(Ingredient.class);
        Ingredient second = mock(Ingredient.class);
        Ingredient third = mock(Ingredient.class);

        burger.addIngredient(first);
        burger.addIngredient(second);
        burger.addIngredient(third);

        burger.moveIngredient(2, 0);

        assertEquals(Arrays.asList(third, first, second), burger.ingredients);
    }

    // Перемещение на своё место
    @Test
    public void shouldKeepOrderWhenMovingToSameIndex() {
        Ingredient first = mock(Ingredient.class);
        Ingredient second = mock(Ingredient.class);
        Ingredient third = mock(Ingredient.class);

        burger.addIngredient(first);
        burger.addIngredient(second);
        burger.addIngredient(third);

        burger.moveIngredient(1, 1);

        assertEquals(Arrays.asList(first, second, third), burger.ingredients);
    }
}
