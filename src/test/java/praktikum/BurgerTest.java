package praktikum;

import org.junit.*;
import org.mockito.*;
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
        assertTrue(burger.ingredients.isEmpty());
        burger.addIngredient(mockIngredient);
        assertEquals(1, burger.ingredients.size());
        assertSame(mockIngredient, burger.ingredients.get(0));
    }

    // Порядок при добавлении
    @Test
    public void shouldKeepOrderAfterAdd() {
        Ingredient first = mock(Ingredient.class);
        Ingredient second = mock(Ingredient.class);

        burger.addIngredient(first);
        burger.addIngredient(second);

        assertSame(first, burger.ingredients.get(0));
        assertSame(second, burger.ingredients.get(1));
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

        assertSame(first, burger.ingredients.get(0));
        assertSame(third, burger.ingredients.get(1));
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

        assertSame(second, burger.ingredients.get(0));
        assertSame(third, burger.ingredients.get(1));

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

        assertSame(first, burger.ingredients.get(0));
        assertSame(second, burger.ingredients.get(1));

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

        assertSame(second, burger.ingredients.get(0));
        assertSame(third, burger.ingredients.get(1));
        assertSame(first, burger.ingredients.get(2));
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

        assertSame(third, burger.ingredients.get(0));
        assertSame(first, burger.ingredients.get(1));
        assertSame(second, burger.ingredients.get(2));
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

        assertSame(first, burger.ingredients.get(0));
        assertSame(second, burger.ingredients.get(1));
        assertSame(third, burger.ingredients.get(2));
    }
}
