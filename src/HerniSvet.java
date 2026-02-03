import com.google.gson.*;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class HerniSvet {

        private Map<String, Lokace> lokace;
        private Lokace startovniLokace;

        public HerniSvet() {
                lokace = new HashMap<>();
        }

        public void nactiZeSouboru(String cesta) {
                try {
                        JsonObject root = JsonParser.parseReader(new FileReader(cesta)).getAsJsonObject();
                        for (JsonElement e : root.getAsJsonArray("lokace")) {
                                JsonObject obj = e.getAsJsonObject();
                                String nazev = obj.get("nazev").getAsString();
                                String popis = obj.get("popis").getAsString();
                                lokace.put(nazev, new Lokace(nazev, popis));
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
                        System.out.println("Chyba při načítání světa.");
                        e.printStackTrace();
                }
        }

        public Lokace getStartovniLokace() {
                return startovniLokace;
        }
}
