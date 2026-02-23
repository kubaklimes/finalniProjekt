import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Testy třídy PrikazovyManager.
 *
 * @author já,chatgpt
 */
public class PrikazovyManagerTest {

    @Test
    void prikazovyManagerOsetriPrazdnyVstup() {
        PrikazovyManager manager = new PrikazovyManager();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(out));
        try {
            manager.vykonejPrikaz("   ");
        } finally {
            System.setOut(originalOut);
        }

        Assertions.assertTrue(out.toString().contains("Zadej příkaz"));
    }
}
