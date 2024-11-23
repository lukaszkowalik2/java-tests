package zadanie05;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class EKuchniaTest {

    private EKuchnia kitchen;

    @BeforeEach
    void setUp() {
        kitchen = new EKuchnia();
    }

    private float calculateMaxPortion(Przepis recipe, Map<Produkt, Integer> pantry) {
        float maxPortion = Integer.MAX_VALUE;
        for (Skladnik ingredient : recipe.sklad()) {
            Produkt product = ingredient.produkt();
            int requiredGrams = ingredient.gramow();
            int availableGrams = pantry.getOrDefault(product, 0);

            if (availableGrams < requiredGrams) {
                return 0;
            }

            maxPortion = Math.min(maxPortion, (float) availableGrams / requiredGrams);
        }
        return maxPortion;
    }


    @Test
    void shouldAddSingleProductToPantry() {
        kitchen.dodajDoSpizarni(Produkt.CUKIER, 500);

        Map<Produkt, Integer> pantry = kitchen.stanSpizarni();

        assertEquals(500, pantry.get(Produkt.CUKIER));
    }

    @Test
    void shouldAddSameProductTwice() {
        kitchen.dodajDoSpizarni(Produkt.CUKIER, 500);
        kitchen.dodajDoSpizarni(Produkt.CUKIER, 300);

        Map<Produkt, Integer> pantry = kitchen.stanSpizarni();

        assertEquals(800, pantry.get(Produkt.CUKIER));
    }

    @Test
    void shouldAddMultipleDifferentProducts() {
        kitchen.dodajDoSpizarni(Produkt.CUKIER, 500);
        kitchen.dodajDoSpizarni(Produkt.MAKA_PSZENNA, 1000);

        Map<Produkt, Integer> pantry = kitchen.stanSpizarni();

        assertEquals(500, pantry.get(Produkt.CUKIER));
        assertEquals(1000, pantry.get(Produkt.MAKA_PSZENNA));
    }

    @Test
    void shouldHandleZero() {
        kitchen.dodajDoSpizarni(Produkt.CUKIER, 0);

        Map<Produkt, Integer> pantry = kitchen.stanSpizarni();


        assertTrue(!pantry.containsKey(Produkt.CUKIER) || pantry.get(Produkt.CUKIER) == 0);
    }

    @Test
    void shouldAddAndRemoveProductQuantity() {
        kitchen.dodajDoSpizarni(Produkt.CUKIER, 500);
        kitchen.dodajDoSpizarni(Produkt.CUKIER, -200);

        Map<Produkt, Integer> pantry = kitchen.stanSpizarni();

        assertEquals(300, pantry.get(Produkt.CUKIER));
    }

    @Test
    void shouldRemoveProductCompletely() {
        kitchen.dodajDoSpizarni(Produkt.CUKIER, 500);
        kitchen.dodajDoSpizarni(Produkt.CUKIER, -500);

        Map<Produkt, Integer> pantry = kitchen.stanSpizarni();

        assertTrue(!pantry.containsKey(Produkt.CUKIER) || pantry.get(Produkt.CUKIER) == 0);
    }

    @Test
    void shouldAddManyProducts() {
        for (int i = 1; i <= 10; i++) {
            kitchen.dodajDoSpizarni(Produkt.CUKIER, 10);
        }

        Map<Produkt, Integer> pantry = kitchen.stanSpizarni();

        assertEquals(100, pantry.get(Produkt.CUKIER));
    }

    @Test
    void shouldKeepPantryEmptyInitially() {
        Map<Produkt, Integer> pantry = kitchen.stanSpizarni();

        assertTrue(pantry.isEmpty());
    }

    @Test
    void shouldOnlyTrackAddedProducts() {
        kitchen.dodajDoSpizarni(Produkt.CUKIER, 500);

        Map<Produkt, Integer> pantry = kitchen.stanSpizarni();

        assertEquals(1, pantry.size());
        assertTrue(pantry.containsKey(Produkt.CUKIER));
    }

    @Test
    void shouldCorrectlyReportMultipleProductsInPantry() {
        kitchen.dodajDoSpizarni(Produkt.CUKIER, 500);
        kitchen.dodajDoSpizarni(Produkt.MAKA_PSZENNA, 1000);
        kitchen.dodajDoSpizarni(Produkt.SOL, 200);

        Map<Produkt, Integer> pantry = kitchen.stanSpizarni();

        assertEquals(3, pantry.size());
        assertEquals(500, pantry.get(Produkt.CUKIER));
        assertEquals(1000, pantry.get(Produkt.MAKA_PSZENNA));
        assertEquals(200, pantry.get(Produkt.SOL));
    }

    @Test
    void shouldReturnCorrectStateAfterAddingAndRemovingProducts() {
        kitchen.dodajDoSpizarni(Produkt.CUKIER, 500);
        kitchen.dodajDoSpizarni(Produkt.MAKA_PSZENNA, 1000);
        kitchen.dodajDoSpizarni(Produkt.CUKIER, -200);

        Map<Produkt, Integer> pantry = kitchen.stanSpizarni();

        assertEquals(300, pantry.get(Produkt.CUKIER));
        assertEquals(1000, pantry.get(Produkt.MAKA_PSZENNA));
    }

    @Test
    void shouldAllowAddingProductsBackAfterRemoval() {
        kitchen.dodajDoSpizarni(Produkt.CUKIER, 500);
        kitchen.dodajDoSpizarni(Produkt.CUKIER, -500);
        kitchen.dodajDoSpizarni(Produkt.CUKIER, 300);

        Map<Produkt, Integer> pantry = kitchen.stanSpizarni();

        assertEquals(300, pantry.get(Produkt.CUKIER));
    }

    @Test
    void shouldCorrectlyHandleLargeQuantities() {
        kitchen.dodajDoSpizarni(Produkt.CUKIER, Integer.MAX_VALUE);

        Map<Produkt, Integer> pantry = kitchen.stanSpizarni();

        assertEquals(Integer.MAX_VALUE, pantry.get(Produkt.CUKIER));
    }

    @Test
    void shouldAllowAddingZeroQuantityMultipleTimes() {
        kitchen.dodajDoSpizarni(Produkt.CUKIER, 0);
        kitchen.dodajDoSpizarni(Produkt.CUKIER, 0);
        kitchen.dodajDoSpizarni(Produkt.CUKIER, 500);

        Map<Produkt, Integer> pantry = kitchen.stanSpizarni();

        assertEquals(500, pantry.get(Produkt.CUKIER));
    }

    @Test
    void shouldCombineSmallAdditionsCorrectly() {
        for (int i = 1; i <= 5; i++) {
            kitchen.dodajDoSpizarni(Produkt.CUKIER, 100);
        }

        Map<Produkt, Integer> pantry = kitchen.stanSpizarni();

        assertEquals(500, pantry.get(Produkt.CUKIER));
    }

    @Test
    void shouldHandleAddingNegativeAndPositiveQuantities() {
        kitchen.dodajDoSpizarni(Produkt.CUKIER, 500);
        kitchen.dodajDoSpizarni(Produkt.CUKIER, -200);
        kitchen.dodajDoSpizarni(Produkt.CUKIER, 300);

        Map<Produkt, Integer> pantry = kitchen.stanSpizarni();

        assertEquals(600, pantry.get(Produkt.CUKIER));
    }

    @Test
    void shouldReportCorrectSizeForPantry() {
        kitchen.dodajDoSpizarni(Produkt.CUKIER, 500);
        kitchen.dodajDoSpizarni(Produkt.MAKA_PSZENNA, 300);
        kitchen.dodajDoSpizarni(Produkt.SOL, 200);

        Map<Produkt, Integer> pantry = kitchen.stanSpizarni();

        assertEquals(3, pantry.size());
    }

    @Test
    void shouldRecalculateRecipeForFullPortion() {
        kitchen.dodajDoSpizarni(Produkt.CUKIER, 100);
        kitchen.dodajDoSpizarni(Produkt.MAKA_PSZENNA, 500);
        kitchen.dodajDoSpizarni(Produkt.SOL, 30);

        Przepis recipe = () -> Set.of(
                new Skladnik(Produkt.CUKIER, 50),
                new Skladnik(Produkt.MAKA_PSZENNA, 500),
                new Skladnik(Produkt.SOL, 15)
        );

        float maxPortion = calculateMaxPortion(recipe, kitchen.stanSpizarni());

        Set<Skladnik> recalculated = kitchen.przeliczPrzepis(recipe);

        assertEquals(3, recalculated.size());


        assertTrue(recalculated.contains(new Skladnik(Produkt.CUKIER, (int) (50 * maxPortion))));
        assertTrue(recalculated.contains(new Skladnik(Produkt.MAKA_PSZENNA, (int) (500 * maxPortion))));
        assertTrue(recalculated.contains(new Skladnik(Produkt.SOL, (int) (15 * maxPortion))));
    }

    @Test
    void shouldRecalculateRecipeForPartialPortion() {
        kitchen.dodajDoSpizarni(Produkt.CUKIER, 80);
        kitchen.dodajDoSpizarni(Produkt.MAKA_PSZENNA, 1000);
        kitchen.dodajDoSpizarni(Produkt.SOL, 20);

        Przepis recipe = () -> Set.of(
                new Skladnik(Produkt.CUKIER, 50),
                new Skladnik(Produkt.MAKA_PSZENNA, 500),
                new Skladnik(Produkt.SOL, 15)
        );

        float maxPortion = calculateMaxPortion(recipe, kitchen.stanSpizarni());

        Set<Skladnik> recalculated = kitchen.przeliczPrzepis(recipe);

        assertEquals(3, recalculated.size());

        System.out.println(recalculated + " " + maxPortion);

        assertTrue(recalculated.contains(new Skladnik(Produkt.CUKIER, (int) (50 * maxPortion))));
        assertTrue(recalculated.contains(new Skladnik(Produkt.MAKA_PSZENNA, (int) (500 * maxPortion))));
        assertTrue(recalculated.contains(new Skladnik(Produkt.SOL, (int) (15 * maxPortion))));
    }

    @Test
    void shouldRecalculateRecipeToEmptySetWhenIngredientsAreInsufficient() {
        kitchen.dodajDoSpizarni(Produkt.CUKIER, 10);
        kitchen.dodajDoSpizarni(Produkt.MAKA_PSZENNA, 100);

        Przepis recipe = () -> Set.of(
                new Skladnik(Produkt.CUKIER, 50),
                new Skladnik(Produkt.MAKA_PSZENNA, 500)
        );

        Set<Skladnik> recalculated = kitchen.przeliczPrzepis(recipe);

        assertTrue(recalculated.isEmpty());
    }

    @Test
    void shouldRecalculateRecipeForMaximumPossiblePortions() {
        kitchen.dodajDoSpizarni(Produkt.CUKIER, 120);
        kitchen.dodajDoSpizarni(Produkt.MAKA_PSZENNA, 1500);
        kitchen.dodajDoSpizarni(Produkt.SOL, 45);

        Przepis recipe = () -> Set.of(
                new Skladnik(Produkt.CUKIER, 30),
                new Skladnik(Produkt.MAKA_PSZENNA, 500),
                new Skladnik(Produkt.SOL, 15)
        );
        float maxPortion = calculateMaxPortion(recipe, kitchen.stanSpizarni());

        Set<Skladnik> recalculated = kitchen.przeliczPrzepis(recipe);

        assertEquals(3, recalculated.size());

        assertTrue(recalculated.contains(new Skladnik(Produkt.CUKIER, (int) (30 * maxPortion))));
        assertTrue(recalculated.contains(new Skladnik(Produkt.MAKA_PSZENNA, (int) (500 * maxPortion))));
        assertTrue(recalculated.contains(new Skladnik(Produkt.SOL, (int) (15 * maxPortion))));
    }

    @Test
    void shouldUpdatePantryAfterExecutingRecipe() {
        kitchen.dodajDoSpizarni(Produkt.CUKIER, 100);
        kitchen.dodajDoSpizarni(Produkt.MAKA_PSZENNA, 500);
        kitchen.dodajDoSpizarni(Produkt.SOL, 30);

        Przepis recipe = () -> Set.of(
                new Skladnik(Produkt.CUKIER, 50),
                new Skladnik(Produkt.MAKA_PSZENNA, 500),
                new Skladnik(Produkt.SOL, 15)
        );

        boolean result = kitchen.wykonaj(recipe);

        assertTrue(result);

        Map<Produkt, Integer> pantry = kitchen.stanSpizarni();
        assertEquals(50, pantry.get(Produkt.CUKIER));
        assertEquals(0, (int) pantry.getOrDefault(Produkt.MAKA_PSZENNA, 0));
        assertEquals(15, pantry.get(Produkt.SOL));
    }

    @Test
    void shouldNotExecuteRecipeWhenIngredientsAreInsufficient() {
        kitchen.dodajDoSpizarni(Produkt.CUKIER, 40); // Not enough sugar
        kitchen.dodajDoSpizarni(Produkt.MAKA_PSZENNA, 300);

        Przepis recipe = () -> Set.of(
                new Skladnik(Produkt.CUKIER, 50),
                new Skladnik(Produkt.MAKA_PSZENNA, 500)
        );

        boolean result = kitchen.wykonaj(recipe);

        assertFalse(result);

        Map<Produkt, Integer> pantry = kitchen.stanSpizarni();
        assertEquals(40, pantry.get(Produkt.CUKIER));
        assertEquals(300, pantry.get(Produkt.MAKA_PSZENNA));
    }

    @Test
    void shouldHandleMultipleExecutionsAndPantryUpdates() {
        kitchen.dodajDoSpizarni(Produkt.CUKIER, 150);
        kitchen.dodajDoSpizarni(Produkt.MAKA_PSZENNA, 1500);
        kitchen.dodajDoSpizarni(Produkt.SOL, 45);

        Przepis recipe = () -> Set.of(
                new Skladnik(Produkt.CUKIER, 50),
                new Skladnik(Produkt.MAKA_PSZENNA, 500),
                new Skladnik(Produkt.SOL, 15)
        );

        assertTrue(kitchen.wykonaj(recipe));
        assertFalse(kitchen.wykonaj(recipe));

        Map<Produkt, Integer> pantry = kitchen.stanSpizarni();

        assertEquals(0, pantry.getOrDefault(Produkt.CUKIER, 0), "Pantry should have 0 or not contain CUKIER");
        assertEquals(0, pantry.getOrDefault(Produkt.MAKA_PSZENNA, 0), "Pantry should have 0 or not contain MAKA_PSZENNA");
        assertEquals(0, pantry.getOrDefault(Produkt.SOL, 0), "Pantry should have 0 or not contain SOL");
    }

    @Test
    void shouldAddAllPossibleProductsToPantry() {
        for (Produkt produkt : Produkt.values()) {
            kitchen.dodajDoSpizarni(produkt, 100);
        }

        Map<Produkt, Integer> pantry = kitchen.stanSpizarni();

        assertEquals(Produkt.values().length, pantry.size());
        for (Produkt produkt : Produkt.values()) {
            assertEquals(100, pantry.get(produkt));
        }
    }

    @Test
    void shouldHandleRecipeUsingAllProducts() {
        for (Produkt produkt : Produkt.values()) {
            kitchen.dodajDoSpizarni(produkt, 100);
        }

        Przepis recipe = () -> Set.of(
                new Skladnik(Produkt.CUKIER, 10),
                new Skladnik(Produkt.MAKA_PSZENNA, 20),
                new Skladnik(Produkt.SOL, 5),
                new Skladnik(Produkt.DROZDZE, 15),
                new Skladnik(Produkt.PROSZEK_DO_PIECZENIA, 8),
                new Skladnik(Produkt.CUKIER_PUDER, 12),
                new Skladnik(Produkt.MLEKO, 25),
                new Skladnik(Produkt.OLEJ_ROSLINNY, 30),
                new Skladnik(Produkt.CUKIER_WANILIOWY, 7),
                new Skladnik(Produkt.MAKA_ZIEMNIACZANA, 40),
                new Skladnik(Produkt.TWAROG, 18),
                new Skladnik(Produkt.RODZYNKI, 10),
                new Skladnik(Produkt.MARGARYNA, 15)
        );

        Set<Skladnik> recalculated = kitchen.przeliczPrzepis(recipe);

        assertEquals(recipe.sklad().size(), recalculated.size());
        float maxPortion = (float) 100 /40;
        assertEquals(recipe.sklad().size(), recalculated.size());
        for (Skladnik skladnik : recipe.sklad()) {
            int expectedGrams = (int) (skladnik.gramow() * maxPortion);
            assertTrue(recalculated.contains(new Skladnik(skladnik.produkt(), expectedGrams)),
                    "Expected ingredient: " + skladnik.produkt() + " with " + expectedGrams + " grams");
        }
    }

    @Test
    void shouldHandleFractionalRecipePortions() {
        kitchen.dodajDoSpizarni(Produkt.CUKIER, 500);
        kitchen.dodajDoSpizarni(Produkt.MAKA_PSZENNA, 16333);
        kitchen.dodajDoSpizarni(Produkt.SOL, 245);

        Przepis recipe = () -> Set.of(
                new Skladnik(Produkt.CUKIER, 10),
                new Skladnik(Produkt.MAKA_PSZENNA, 1000),
                new Skladnik(Produkt.SOL, 15)
        );
        float maxPortion = calculateMaxPortion(recipe,kitchen.stanSpizarni());

        Set<Skladnik> recalculated = kitchen.przeliczPrzepis(recipe);
        System.out.println(recalculated);

        assertTrue(recalculated.contains(new Skladnik(Produkt.CUKIER, (int) (10 * maxPortion))));
        assertTrue(recalculated.contains(new Skladnik(Produkt.MAKA_PSZENNA, (int) (1000 * maxPortion))));
        assertTrue(recalculated.contains(new Skladnik(Produkt.SOL, (int) (15 * maxPortion))));
    }

    @Test
    void shouldUpdatePantryAfterFractionalRecipeExecution() {
        kitchen.dodajDoSpizarni(Produkt.CUKIER, 500);
        kitchen.dodajDoSpizarni(Produkt.MAKA_PSZENNA, 16000);
        kitchen.dodajDoSpizarni(Produkt.SOL, 240);

        Przepis recipe = () -> Set.of(
                new Skladnik(Produkt.CUKIER, 10),
                new Skladnik(Produkt.MAKA_PSZENNA, 1000),
                new Skladnik(Produkt.SOL, 15)
        );
        float maxPortion = calculateMaxPortion(recipe,kitchen.stanSpizarni());

        boolean result = kitchen.wykonaj(recipe);

        assertTrue(result);

        Map<Produkt, Integer> pantry = kitchen.stanSpizarni();
        assertEquals(340 , pantry.get(Produkt.CUKIER));
        assertEquals(0, pantry.getOrDefault(Produkt.MAKA_PSZENNA,0));
        assertEquals(0 , pantry.getOrDefault(Produkt.SOL,0));
    }

    @Test
    void shouldHandleEmptyRecipe() {
        Przepis recipe = Set::of;

        Set<Skladnik> recalculated = kitchen.przeliczPrzepis(recipe);

        assertTrue(recalculated.isEmpty());
    }

    @Test
    void shouldHandleRecipeWithUnavailableProducts() {
        kitchen.dodajDoSpizarni(Produkt.CUKIER, 50);

        Przepis recipe = () -> Set.of(
                new Skladnik(Produkt.CUKIER, 10),
                new Skladnik(Produkt.MAKA_PSZENNA, 100),
                new Skladnik(Produkt.SOL, 15)
        );

        Set<Skladnik> recalculated = kitchen.przeliczPrzepis(recipe);

        assertTrue(recalculated.isEmpty());
    }

    @Test
    void shouldAllowMultipleFractionalExecutions() {
        kitchen.dodajDoSpizarni(Produkt.CUKIER, 510);
        kitchen.dodajDoSpizarni(Produkt.MAKA_PSZENNA, 3000);
        kitchen.dodajDoSpizarni(Produkt.SOL, 90);

        Przepis recipe = () -> Set.of(
                new Skladnik(Produkt.CUKIER, 10),
                new Skladnik(Produkt.MAKA_PSZENNA, 1000),
                new Skladnik(Produkt.SOL, 15)
        );

        assertTrue(kitchen.wykonaj(recipe));
        assertFalse(kitchen.wykonaj(recipe));

        Map<Produkt, Integer> pantry = kitchen.stanSpizarni();
        assertEquals(480, pantry.get(Produkt.CUKIER));
        assertEquals(0, pantry.getOrDefault(Produkt.MAKA_PSZENNA,0));
        assertEquals(45, pantry.getOrDefault(Produkt.SOL,0));
    }

    @Test
    void shouldStopExecutionWhenPantryIsDepleted() {
        kitchen.dodajDoSpizarni(Produkt.CUKIER, 50);
        kitchen.dodajDoSpizarni(Produkt.MAKA_PSZENNA, 500);
        kitchen.dodajDoSpizarni(Produkt.SOL, 15);

        Przepis recipe = () -> Set.of(
                new Skladnik(Produkt.CUKIER, 10),
                new Skladnik(Produkt.MAKA_PSZENNA, 100),
                new Skladnik(Produkt.SOL, 5)
        );

        assertTrue(kitchen.wykonaj(recipe));
        assertFalse(kitchen.wykonaj(recipe));

        Map<Produkt, Integer> pantry = kitchen.stanSpizarni();
        assertEquals(20, pantry.get(Produkt.CUKIER));
        assertEquals(200, pantry.get(Produkt.MAKA_PSZENNA));
        assertEquals(0, pantry.getOrDefault(Produkt.SOL,0));
    }

    @Test
    void shouldHandleSingleIngredientRecipe() {
        kitchen.dodajDoSpizarni(Produkt.CUKIER, 200);

        Przepis recipe = () -> Set.of(
                new Skladnik(Produkt.CUKIER, 50)
        );

        Set<Skladnik> recalculated = kitchen.przeliczPrzepis(recipe);

        assertEquals(1, recalculated.size());
    }

    @Test
    void shouldRecalculateRecipeToMaximumAvailableSinglePortion() {
        kitchen.dodajDoSpizarni(Produkt.CUKIER, 75);
        kitchen.dodajDoSpizarni(Produkt.MAKA_PSZENNA, 1000);

        Przepis recipe = () -> Set.of(
                new Skladnik(Produkt.CUKIER, 50),
                new Skladnik(Produkt.MAKA_PSZENNA, 500)
        );

        float maxPortion = calculateMaxPortion(recipe, kitchen.stanSpizarni());

        Set<Skladnik> recalculated = kitchen.przeliczPrzepis(recipe);

        assertTrue(recalculated.contains(new Skladnik(Produkt.CUKIER, (int) (50 * maxPortion))));
        assertTrue(recalculated.contains(new Skladnik(Produkt.MAKA_PSZENNA, (int) (500 * maxPortion))));
    }

    @Test
    void shouldHandleRecipeWithOneIngredientMissing() {
        kitchen.dodajDoSpizarni(Produkt.CUKIER, 200);

        Przepis recipe = () -> Set.of(
                new Skladnik(Produkt.CUKIER, 50),
                new Skladnik(Produkt.MAKA_PSZENNA, 500)
        );

        Set<Skladnik> recalculated = kitchen.przeliczPrzepis(recipe);

        assertTrue(recalculated.isEmpty());
    }

    @Test
    void shouldHandlePantryWithZeroQuantityOfAnIngredient() {
        kitchen.dodajDoSpizarni(Produkt.CUKIER, 0);
        kitchen.dodajDoSpizarni(Produkt.MAKA_PSZENNA, 500);

        Przepis recipe = () -> Set.of(
                new Skladnik(Produkt.CUKIER, 50),
                new Skladnik(Produkt.MAKA_PSZENNA, 500)
        );

        Set<Skladnik> recalculated = kitchen.przeliczPrzepis(recipe);

        assertTrue(recalculated.isEmpty());
    }

    @Test
    void shouldHandleComplexRecipeWithFractionalPortions() {
        kitchen.dodajDoSpizarni(Produkt.CUKIER, 450);
        kitchen.dodajDoSpizarni(Produkt.MAKA_PSZENNA, 1200);
        kitchen.dodajDoSpizarni(Produkt.SOL, 45);

        Przepis recipe = () -> Set.of(
                new Skladnik(Produkt.CUKIER, 150),
                new Skladnik(Produkt.MAKA_PSZENNA, 500),
                new Skladnik(Produkt.SOL, 15)
        );
        float maxPortion = calculateMaxPortion(recipe, kitchen.stanSpizarni());

        Set<Skladnik> recalculated = kitchen.przeliczPrzepis(recipe);


        assertEquals(3, recalculated.size());
        assertTrue(recalculated.contains(new Skladnik(Produkt.CUKIER, (int) (150 * maxPortion))));
        assertTrue(recalculated.contains(new Skladnik(Produkt.MAKA_PSZENNA, (int) (500 * maxPortion))));
        assertTrue(recalculated.contains(new Skladnik(Produkt.SOL, (int) (15 * maxPortion))));
    }

    @Test
    void shouldSupportRecipesWithNonStandardIngredients() {
        kitchen.dodajDoSpizarni(Produkt.RODZYNKI, 200);
        kitchen.dodajDoSpizarni(Produkt.TWAROG, 500);

        Przepis recipe = () -> Set.of(
                new Skladnik(Produkt.RODZYNKI, 50),
                new Skladnik(Produkt.TWAROG, 100)
        );
        float maxPortion = calculateMaxPortion(recipe, kitchen.stanSpizarni());

        Set<Skladnik> recalculated = kitchen.przeliczPrzepis(recipe);

        assertEquals(2, recalculated.size());
        assertTrue(recalculated.contains(new Skladnik(Produkt.RODZYNKI, (int) (50 * maxPortion))));
        assertTrue(recalculated.contains(new Skladnik(Produkt.TWAROG, (int) (100 * maxPortion))));
    }

    @Test
    void shouldReportEmptyPantryInitially() {
        Map<Produkt, Integer> pantry = kitchen.stanSpizarni();

        assertTrue(pantry.isEmpty());
    }

    @Test
    void shouldNotModifyPantryWhenRecipeCannotBeExecuted() {
        kitchen.dodajDoSpizarni(Produkt.CUKIER, 50);

        Przepis recipe = () -> Set.of(
                new Skladnik(Produkt.CUKIER, 100) // Insufficient
        );

        boolean executed = kitchen.wykonaj(recipe);

        assertFalse(executed);

        Map<Produkt, Integer> pantry = kitchen.stanSpizarni();
        assertEquals(50, pantry.get(Produkt.CUKIER));
    }

    @Test
    void shouldHandleRecipeWithZeroGramsOfIngredients() {
        kitchen.dodajDoSpizarni(Produkt.CUKIER, 100);

        Przepis recipe = () -> Set.of(
                new Skladnik(Produkt.CUKIER, 0)
        );

        Set<Skladnik> recalculated = kitchen.przeliczPrzepis(recipe);

        assertTrue(recalculated.contains(new Skladnik(Produkt.CUKIER, 0)));
    }

    @Test
    void shouldHandleRecipesWithMixedAvailability() {
        kitchen.dodajDoSpizarni(Produkt.CUKIER, 200);
        kitchen.dodajDoSpizarni(Produkt.MAKA_PSZENNA, 400);

        Przepis recipe = () -> Set.of(
                new Skladnik(Produkt.CUKIER, 50),
                new Skladnik(Produkt.MAKA_PSZENNA, 500)
        );

        Set<Skladnik> recalculated = kitchen.przeliczPrzepis(recipe);

        assertTrue(recalculated.isEmpty());
    }

    @Test
    void shouldHandleAddingZeroGramsToExistingProduct() {
        kitchen.dodajDoSpizarni(Produkt.CUKIER, 100);
        kitchen.dodajDoSpizarni(Produkt.CUKIER, 0);

        Map<Produkt, Integer> pantry = kitchen.stanSpizarni();

        assertEquals(100, pantry.get(Produkt.CUKIER));
    }

    @Test
    void shouldSupportLargeFractionalRecipes() {
        kitchen.dodajDoSpizarni(Produkt.MLEKO, 4900);

        Przepis recipe = () -> Set.of(
                new Skladnik(Produkt.MLEKO, 1500)
        );
        float maxPortion = calculateMaxPortion(recipe, kitchen.stanSpizarni());

        Set<Skladnik> recalculated = kitchen.przeliczPrzepis(recipe);

        assertTrue(recalculated.contains(new Skladnik(Produkt.MLEKO, (int) (1500 * maxPortion))));
    }

    @Test
    void shouldStopRecipeExecutionWhenPantryIsDepleted() {
        kitchen.dodajDoSpizarni(Produkt.CUKIER, 50);

        Przepis recipe = () -> Set.of(
                new Skladnik(Produkt.CUKIER, 25)
        );

        assertTrue(kitchen.wykonaj(recipe));
        assertFalse(kitchen.wykonaj(recipe));

        Map<Produkt, Integer> pantry = kitchen.stanSpizarni();
        assertFalse(pantry.containsKey(Produkt.CUKIER));
    }

    @Test
    void shouldSupportAddingAndExecutingMultipleRecipes() {
        kitchen.dodajDoSpizarni(Produkt.CUKIER, 300);
        kitchen.dodajDoSpizarni(Produkt.MAKA_PSZENNA, 600);

        Przepis recipe1 = () -> Set.of(
                new Skladnik(Produkt.CUKIER, 150),
                new Skladnik(Produkt.MAKA_PSZENNA, 200)
        );

        Przepis recipe2 = () -> Set.of(
                new Skladnik(Produkt.CUKIER, 50),
                new Skladnik(Produkt.MAKA_PSZENNA, 100)
        );

        assertTrue(kitchen.wykonaj(recipe1));
        kitchen.dodajDoSpizarni(Produkt.CUKIER,51);
        assertTrue(kitchen.wykonaj(recipe2));

        Map<Produkt, Integer> pantry = kitchen.stanSpizarni();
        assertEquals(0, pantry.getOrDefault(Produkt.CUKIER,0));
        assertEquals(98, pantry.get(Produkt.MAKA_PSZENNA));
    }

    @Test
    void shouldReturnEmptySetWhenRecipeCalledBeforeAddingIngredients() {
        Przepis recipe = () -> Set.of(
                new Skladnik(Produkt.CUKIER, 50),
                new Skladnik(Produkt.MAKA_PSZENNA, 500)
        );

        Set<Skladnik> recalculated = kitchen.przeliczPrzepis(recipe);

        assertTrue(recalculated.isEmpty());
    }

    @Test
    void shouldHandleMultipleKitchenInstancesIndependently() {
        EKuchnia kitchen1 = new EKuchnia();
        EKuchnia kitchen2 = new EKuchnia();

        kitchen1.dodajDoSpizarni(Produkt.CUKIER, 100);
        kitchen2.dodajDoSpizarni(Produkt.CUKIER, 200);

        Map<Produkt, Integer> pantry1 = kitchen1.stanSpizarni();
        Map<Produkt, Integer> pantry2 = kitchen2.stanSpizarni();

        assertEquals(100, pantry1.get(Produkt.CUKIER));
        assertEquals(200, pantry2.get(Produkt.CUKIER));
        assertNotEquals(pantry1, pantry2);
    }

    @Test
    void shouldNotAffectOtherInstanceWhenOneKitchenExecutesRecipe() {
        EKuchnia kitchen1 = new EKuchnia();
        EKuchnia kitchen2 = new EKuchnia();

        kitchen1.dodajDoSpizarni(Produkt.CUKIER, 100);
        kitchen2.dodajDoSpizarni(Produkt.CUKIER, 200);

        Przepis recipe = () -> Set.of(
                new Skladnik(Produkt.CUKIER, 50)
        );

        assertTrue(kitchen1.wykonaj(recipe));
        assertFalse(kitchen2.wykonaj(() -> Set.of(new Skladnik(Produkt.CUKIER, 250))));

        Map<Produkt, Integer> pantry1 = kitchen1.stanSpizarni();
        Map<Produkt, Integer> pantry2 = kitchen2.stanSpizarni();

        assertEquals(0, pantry1.getOrDefault(Produkt.CUKIER,0));
        assertEquals(200, pantry2.get(Produkt.CUKIER));
    }

    @Test
    void shouldAllowRecipeWithEmptyPantryAndEmptyRecipe() {
        Przepis recipe = Set::of; // Empty recipe

        Set<Skladnik> recalculated = kitchen.przeliczPrzepis(recipe);

        assertTrue(recalculated.isEmpty());
    }

    @Test
    void shouldHandleNegativeQuantitiesBeforePositiveAdditions() {
        kitchen.dodajDoSpizarni(Produkt.CUKIER, -50);
        kitchen.dodajDoSpizarni(Produkt.CUKIER, 100);

        Map<Produkt, Integer> pantry = kitchen.stanSpizarni();

        assertEquals(50, pantry.get(Produkt.CUKIER));
    }

    @Test
    void shouldAllowAddingAndExecutingWithOneIngredientOnly() {
        kitchen.dodajDoSpizarni(Produkt.CUKIER, 100);

        Przepis recipe = () -> Set.of(
                new Skladnik(Produkt.CUKIER, 50)
        );

        boolean executed = kitchen.wykonaj(recipe);

        assertTrue(executed);

        Map<Produkt, Integer> pantry = kitchen.stanSpizarni();
        assertEquals(0, pantry.getOrDefault(Produkt.CUKIER,0));
    }

    @Test
    void shouldFailExecutionWhenPantryHasZeroIngredients() {
        Przepis recipe = () -> Set.of(
                new Skladnik(Produkt.CUKIER, 10)
        );

        boolean executed = kitchen.wykonaj(recipe);

        assertFalse(executed);

        Map<Produkt, Integer> pantry = kitchen.stanSpizarni();
        assertTrue(pantry.isEmpty());
    }

    @Test
    void shouldHandleMultipleConcurrentRecipesOnSeparateInstances() {
        EKuchnia kitchen1 = new EKuchnia();
        EKuchnia kitchen2 = new EKuchnia();

        kitchen1.dodajDoSpizarni(Produkt.CUKIER, 100);
        kitchen2.dodajDoSpizarni(Produkt.MAKA_PSZENNA, 200);

        Przepis recipe1 = () -> Set.of(new Skladnik(Produkt.CUKIER, 50));
        Przepis recipe2 = () -> Set.of(new Skladnik(Produkt.MAKA_PSZENNA, 100));

        assertTrue(kitchen1.wykonaj(recipe1));
        assertTrue(kitchen2.wykonaj(recipe2));

        Map<Produkt, Integer> pantry1 = kitchen1.stanSpizarni();
        Map<Produkt, Integer> pantry2 = kitchen2.stanSpizarni();

        assertTrue(pantry1.isEmpty());
        assertTrue(pantry2.isEmpty());
    }

    @Test
    void shouldHandleRecipeWithZeroAmountIngredients() {
        kitchen.dodajDoSpizarni(Produkt.CUKIER, 100);

        Przepis recipe = () -> Set.of(
                new Skladnik(Produkt.CUKIER, 0)
        );

        boolean executed = kitchen.wykonaj(recipe);

        assertTrue(executed); // No actual ingredient used

        Map<Produkt, Integer> pantry = kitchen.stanSpizarni();
        assertEquals(100, pantry.get(Produkt.CUKIER)); // Nothing consumed
    }

    @Test
    void shouldHandleRecipeWithOneMissingIngredient() {
        kitchen.dodajDoSpizarni(Produkt.CUKIER, 100);

        Przepis recipe = () -> Set.of(
                new Skladnik(Produkt.CUKIER, 50),
                new Skladnik(Produkt.MAKA_PSZENNA, 50) // Missing
        );

        boolean executed = kitchen.wykonaj(recipe);

        assertFalse(executed); // Recipe cannot execute

        Map<Produkt, Integer> pantry = kitchen.stanSpizarni();
        assertEquals(100, pantry.get(Produkt.CUKIER)); // Pantry remains unchanged
    }

    @Test
    void shouldHandleExecutionWithEmptyPantry() {
        Przepis recipe = () -> Set.of(
                new Skladnik(Produkt.CUKIER, 50)
        );

        boolean executed = kitchen.wykonaj(recipe);

        assertFalse(executed); // No ingredients to execute recipe

        Map<Produkt, Integer> pantry = kitchen.stanSpizarni();
        assertTrue(pantry.isEmpty()); // Pantry is empty
    }

    @Test
    void shouldNotAllowNegativePantryAmountsAfterExecution() {
        kitchen.dodajDoSpizarni(Produkt.CUKIER, 50);

        Przepis recipe = () -> Set.of(
                new Skladnik(Produkt.CUKIER, 100) // More than available
        );

        boolean executed = kitchen.wykonaj(recipe);

        assertFalse(executed);

        Map<Produkt, Integer> pantry = kitchen.stanSpizarni();
        assertEquals(50, pantry.get(Produkt.CUKIER)); // Pantry remains unchanged
    }

    @Test
    void shouldSupportAddingAndExecutingMultipleInstancesWithSameIngredient() {
        EKuchnia kitchen1 = new EKuchnia();
        EKuchnia kitchen2 = new EKuchnia();

        kitchen1.dodajDoSpizarni(Produkt.CUKIER, 100);
        kitchen2.dodajDoSpizarni(Produkt.CUKIER, 100);

        Przepis recipe = () -> Set.of(
                new Skladnik(Produkt.CUKIER, 50)
        );

        assertTrue(kitchen1.wykonaj(recipe));
        assertTrue(kitchen2.wykonaj(recipe));

        Map<Produkt, Integer> pantry1 = kitchen1.stanSpizarni();
        Map<Produkt, Integer> pantry2 = kitchen2.stanSpizarni();

        assertEquals(0, pantry1.getOrDefault(Produkt.CUKIER,0));
        assertEquals(0, pantry2.getOrDefault(Produkt.CUKIER,0));
    }

    @Test
    void shouldRecalculateRecipeToMaxPortionsBasedOnAvailableResources() {
        EKuchnia kitchen = new EKuchnia();

        kitchen.dodajDoSpizarni(Produkt.CUKIER, 600);
        kitchen.dodajDoSpizarni(Produkt.MAKA_PSZENNA, 1500);
        kitchen.dodajDoSpizarni(Produkt.SOL, 100);
        kitchen.dodajDoSpizarni(Produkt.DROZDZE, 50);

        Przepis recipe = () -> Set.of(
                new Skladnik(Produkt.CUKIER, 10),
                new Skladnik(Produkt.MAKA_PSZENNA, 500),
                new Skladnik(Produkt.SOL, 15),
                new Skladnik(Produkt.DROZDZE, 25)
        );

        Set<Skladnik> recalculated = kitchen.przeliczPrzepis(recipe);

        assertTrue(recalculated.contains(new Skladnik(Produkt.CUKIER, 20)));
        assertTrue(recalculated.contains(new Skladnik(Produkt.MAKA_PSZENNA, 1000)));
        assertTrue(recalculated.contains(new Skladnik(Produkt.SOL, 30)));
        assertTrue(recalculated.contains(new Skladnik(Produkt.DROZDZE, 50)));
    }

    @Test
    void shouldRecalculateRecipeForLimitedFlour() {
        EKuchnia kitchen = new EKuchnia();

        kitchen.dodajDoSpizarni(Produkt.CUKIER, 600);
        kitchen.dodajDoSpizarni(Produkt.MAKA_PSZENNA, 500);
        kitchen.dodajDoSpizarni(Produkt.SOL, 100);
        kitchen.dodajDoSpizarni(Produkt.DROZDZE, 50);

        Przepis recipe = () -> Set.of(
                new Skladnik(Produkt.CUKIER, 10),
                new Skladnik(Produkt.MAKA_PSZENNA, 250),
                new Skladnik(Produkt.SOL, 15),
                new Skladnik(Produkt.DROZDZE, 25)
        );
        float maxPortion = calculateMaxPortion(recipe,kitchen.stanSpizarni());

        Set<Skladnik> recalculated = kitchen.przeliczPrzepis(recipe);

        assertTrue(recalculated.contains(new Skladnik(Produkt.CUKIER, (int) (10 * maxPortion))));
        assertTrue(recalculated.contains(new Skladnik(Produkt.MAKA_PSZENNA, (int) (250 * maxPortion))));
        assertTrue(recalculated.contains(new Skladnik(Produkt.SOL, (int) (15 * maxPortion))));
        assertTrue(recalculated.contains(new Skladnik(Produkt.DROZDZE, (int) (25 * maxPortion))));
    }

    @Test
    void shouldReturnEmptySetWhenCalculatingRecipeBeforeAddingProducts() {
        Przepis recipe = () -> Set.of(
                new Skladnik(Produkt.CUKIER, 50),
                new Skladnik(Produkt.MAKA_PSZENNA, 500),
                new Skladnik(Produkt.SOL, 15)
        );

        Set<Skladnik> recalculated = kitchen.przeliczPrzepis(recipe);

        assertTrue(recalculated.isEmpty(), "Recalculated recipe should be empty when pantry is empty.");
    }

    @Test
    void shouldNotExecuteRecipeBeforeAddingProducts() {
        Przepis recipe = () -> Set.of(
                new Skladnik(Produkt.CUKIER, 50),
                new Skladnik(Produkt.MAKA_PSZENNA, 500),
                new Skladnik(Produkt.SOL, 15)
        );

        boolean result = kitchen.wykonaj(recipe);

        assertFalse(result, "Recipe should not execute when pantry is empty.");
        assertTrue(kitchen.stanSpizarni().isEmpty(), "Pantry should remain empty after failed execution.");
    }

    @Test
    void shouldAllowRecalculatingRecipeAfterAddingProducts() {
        Przepis recipe = () -> Set.of(
                new Skladnik(Produkt.CUKIER, 50),
                new Skladnik(Produkt.MAKA_PSZENNA, 500),
                new Skladnik(Produkt.SOL, 15)
        );

        Set<Skladnik> recalculatedBefore = kitchen.przeliczPrzepis(recipe);
        assertTrue(recalculatedBefore.isEmpty(), "Recalculated recipe should be empty before adding products.");

        kitchen.dodajDoSpizarni(Produkt.CUKIER, 100);
        kitchen.dodajDoSpizarni(Produkt.MAKA_PSZENNA, 1000);
        kitchen.dodajDoSpizarni(Produkt.SOL, 30);

        Set<Skladnik> recalculatedAfter = kitchen.przeliczPrzepis(recipe);

        assertFalse(recalculatedAfter.isEmpty(), "Recalculated recipe should not be empty after adding products.");
        assertEquals(3, recalculatedAfter.size());
        assertFalse(recalculatedAfter.contains(new Skladnik(Produkt.CUKIER, 50)));
        assertFalse(recalculatedAfter.contains(new Skladnik(Produkt.MAKA_PSZENNA, 500)));
        assertFalse(recalculatedAfter.contains(new Skladnik(Produkt.SOL, 15)));
    }

    @Test
    void shouldNotModifyPantryWhenExecutingRecipeWithEmptyPantry() {
        Przepis recipe = () -> Set.of(
                new Skladnik(Produkt.CUKIER, 50),
                new Skladnik(Produkt.MAKA_PSZENNA, 500)
        );

        boolean result = kitchen.wykonaj(recipe);

        assertFalse(result, "Recipe execution should fail with an empty pantry.");
        assertTrue(kitchen.stanSpizarni().isEmpty(), "Pantry should remain empty after failed execution.");
    }

    @Test
    void shouldHandleAddingProductsAfterFailedRecipeExecution() {
        Przepis recipe = () -> Set.of(
                new Skladnik(Produkt.CUKIER, 50),
                new Skladnik(Produkt.MAKA_PSZENNA, 500)
        );

        boolean result = kitchen.wykonaj(recipe);
        assertFalse(result, "Recipe execution should fail with an empty pantry.");

        kitchen.dodajDoSpizarni(Produkt.CUKIER, 100);
        kitchen.dodajDoSpizarni(Produkt.MAKA_PSZENNA, 1000);

        result = kitchen.wykonaj(recipe);
        assertTrue(result, "Recipe execution should succeed after adding products.");
    }
}
