import com.google.gson.*;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class HerniSvet {
        /**
         * Hlavní definice třídy HerniSvet.
         *
         * @author Jakub Klimeš,stackoverflow
         */

        private final Map<String, Lokace> lokace;
        private Lokace startovniLokace;

        /**
         * Vytvoří instanci třídy HerniSvet.
         */
        public HerniSvet() {
                lokace = new HashMap<>();
        }

        /**
         * Načte herní svět z classpath (JAR) nebo ze souborového systému.
         */
        public void nactiZeSouboru(String cesta) {
                if (cesta == null || cesta.isBlank()) {
                        System.out.println("Chyba při načítání světa: neplatná cesta k souboru.");
                        return;
                }
                try (Reader reader = otevriZdrojSvetu(cesta)) {
                        if (reader == null) {
                                System.out.println("Chyba při načítání světa: soubor '" + cesta + "' nebyl nalezen.");
                                return;
                        }

                        JsonObject root = JsonParser.parseReader(reader).getAsJsonObject();
                        if (!root.has("lokace") || !root.has("start")) {
                                System.out.println("Chyba při načítání světa: JSON neobsahuje povinná pole.");
                                return;
                        }

                        for (JsonElement e : root.getAsJsonArray("lokace")) {
                                JsonObject obj = e.getAsJsonObject();
                                String nazev = obj.get("nazev").getAsString();
                                String popis = obj.get("popis").getAsString();
                                boolean zamcena = obj.has("isLocked") && obj.get("isLocked").getAsBoolean();
                                lokace.put(nazev, new Lokace(nazev, popis, zamcena));
                        }

                        for (JsonElement e : root.getAsJsonArray("lokace")) {
                                JsonObject obj = e.getAsJsonObject();
                                Lokace aktualni = lokace.get(obj.get("nazev").getAsString());

                                JsonObject vychody = obj.getAsJsonObject("vychody");
                                for (String smer : vychody.keySet()) {
                                        String cil = vychody.get(smer).getAsString();
                                        aktualni.pridejVychod(smer, lokace.get(cil));
                                }
                        }

                        startovniLokace = lokace.get(root.get("start").getAsString());
                        if (startovniLokace == null) {
                                System.out.println("Chyba při načítání světa: startovní lokace v mapě neexistuje.");
                        }
                } catch (Exception e) {
                        System.out.println("Chyba při načítání světa: " + e.getMessage());
                }
        }
        /**
         * Otevře JSON světa z classpath nebo z filesystemu.
         */
        private Reader otevriZdrojSvetu(String cesta) {
                String normalizovana = cesta.replace('\\', '/');

                String bezUvodnihoSlash = normalizovana.startsWith("/") ? normalizovana.substring(1) : normalizovana;
                String bezResourcePrefixu = bezUvodnihoSlash.startsWith("resource/")
                        ? bezUvodnihoSlash.substring("resource/".length())
                        : bezUvodnihoSlash;

                for (String kandidat : new String[] { bezUvodnihoSlash, bezResourcePrefixu }) {
                        InputStream classpathStream = Thread.currentThread()
                                .getContextClassLoader()
                                .getResourceAsStream(kandidat);
                        if (classpathStream != null) {
                                return new InputStreamReader(classpathStream, StandardCharsets.UTF_8);
                        }
                }

                for (String kandidatSouboru : new String[] { cesta, normalizovana, "resource/" + bezResourcePrefixu }) {
                        try {
                                return new FileReader(kandidatSouboru, StandardCharsets.UTF_8);
                        } catch (Exception ignored) {
                                // zkoušíme další kandidátní cestu
                        }
                }

                return null;
        }


        /**
         * Metoda getLokace.
         */
        public Lokace getLokace(String nazev) {
                return lokace.get(nazev);
        }

        public Lokace getStartovniLokace() {
                return startovniLokace;
        }
}
