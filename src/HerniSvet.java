import com.google.gson.*;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class HerniSvet {
        /**
         * Hlavní definice třídy HerniSvet.
         *
         * @author já,stackoverflow
         */

        private Map<String, Lokace> lokace;
        private Lokace startovniLokace;

        /**
         * Vytvoří instanci třídy HerniSvet.
         */
        public HerniSvet() {
                lokace = new HashMap<>();
        }
        /**
         * Načte herní svět zjednodušeně.
         */
        public void nactiZeSouboru(String cesta) {
                if (cesta == null || cesta.isBlank()) {
                        System.out.println("Chyba při načítání světa: neplatná cesta k souboru.");
                        return;
                }
                try {
                        JsonObject root = JsonParser.parseReader(new FileReader(cesta)).getAsJsonObject();
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

                } catch (Exception e) {
                        System.out.println("Chyba při načítání světa: " + e.getMessage());
                }
        }

        public Lokace getLokace(String nazev) {
                return lokace.get(nazev);
        }

        public Lokace getStartovniLokace() {
                return startovniLokace;
        }
}
