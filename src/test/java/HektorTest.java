import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;

/**
 * Testy třídy Hektor.
 *
 * @author Jakub Klimeš
 */
public class HektorTest {

    @Test
    void hektorStitPohltiPrvniUtok() {
        Hektor hektor = new Hektor();
        hektor.aktivujStit();

        boolean zasah = hektor.prijmiUtok(2);

        Assertions.assertFalse(zasah);
        Assertions.assertFalse(hektor.jePorazen());
    }

    @Test
    void hektorNabityUtokVraciVyssiPoskozeni() {
        Hektor hektor = new Hektor();
        hektor.nabijSe();

        int dmg = hektor.utokNabity(new Random(1));

        Assertions.assertTrue(dmg >= 2 && dmg <= 3);
        Assertions.assertFalse(hektor.jeNabity());
    }
}
