import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Testy třídy Lokace.
 *
 * @author já
 */
public class LokaceTest {

    @Test
    void lokaceNajdePredmetBezDiakritiky() {
        Lokace lokace = new Lokace("Test", "Test", false);
        lokace.pridejPredmet(new TestPredmet("ID karta", true));

        Predmet predmet = lokace.getPredmet("id karta");

        Assertions.assertNotNull(predmet);
        Assertions.assertEquals("ID karta", predmet.getNazev());
    }

    @Test
    void lokaceVratiNullProNeznamyPredmet() {
        Lokace lokace = new Lokace("Test", "Test", false);

        Predmet predmet = lokace.getPredmet("neexistuje");

        Assertions.assertNull(predmet);
    }
}
