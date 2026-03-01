import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Testy třídy SpravceUkolu.
 *
 * @author Jakub Klimeš
 */
public class SpravceUkoluTest {

    @Test
    void spravceUkoluOznaciUkolJakoSplneny() {
        SpravceUkolu spravce = new SpravceUkolu();
        spravce.pridejUkol(new Ukol("Baterie", "Najdi baterie"));

        spravce.splnitUkol("Baterie");

        Assertions.assertTrue(spravce.jeUkolSplnen("Baterie"));
    }
}
