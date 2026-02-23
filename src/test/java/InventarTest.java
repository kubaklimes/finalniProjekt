import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Testy třídy Inventar.
 *
 * @author já
 */
public class InventarTest {

    @Test
    void inventarPridaPrenosnyPredmet() {
        Inventar inventar = new Inventar(2);

        boolean added = inventar.pridejPredmet(new TestPredmet("Klíč", true));

        Assertions.assertTrue(added);
        Assertions.assertTrue(inventar.obsahuje("klíč"));
    }

    @Test
    void inventarNepridaNeprenosnyPredmet() {
        Inventar inventar = new Inventar(2);

        boolean added = inventar.pridejPredmet(new TestPredmet("Skříň", false));

        Assertions.assertFalse(added);
        Assertions.assertFalse(inventar.obsahuje("Skříň"));
    }

    @Test
    void inventarRespektujeKapacitu() {
        Inventar inventar = new Inventar(1);
        inventar.pridejPredmet(new TestPredmet("A", true));

        boolean added = inventar.pridejPredmet(new TestPredmet("B", true));

        Assertions.assertFalse(added);
        Assertions.assertFalse(inventar.obsahuje("B"));
    }
}
