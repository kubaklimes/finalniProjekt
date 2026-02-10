import java.util.HashMap;
import java.util.Map;
import java.text.Normalizer;

public class Lokace {

    private String nazev;
    private String popis;

    private Map<String, Lokace> vychody;
    private Map<String, Predmet> predmety;
    private Map<String, Postava> postavy;
    private boolean zamcena;
    private boolean navstivena;

    public Lokace(String nazev, String popis,boolean zamcena) {
        this.nazev = nazev;
        this.popis = popis;
        this.zamcena = zamcena;
        this.vychody = new HashMap<>();
        this.predmety = new HashMap<>();
        this.postavy = new HashMap<>();
    }

    public String getNazev() {
        return nazev;
    }

    public String getPopis() {
        return popis;
    }
    public boolean jeZamcena() {
        return zamcena;
    }

    public void odemkni() {
        this.zamcena = false;
    }

    public boolean jeNavstivena() {
        return navstivena;
    }

    public void oznacNavstivenou() {
        navstivena = true;
    }

    public void pridejVychod(String smer, Lokace lokace) {
        vychody.put(smer, lokace);
    }

    public Lokace getVychod(String smer) {
        return vychody.get(smer);
    }

    public void pridejPredmet(Predmet predmet) {
        predmety.put(predmet.getNazev(), predmet);
    }

    public void odeberPredmet(String nazev) {
        predmety.remove(nazev);
    }

    public Predmet getPredmet(String nazev) {
        if (nazev == null) {
            return null;
        }
        String hledany = normalizujText(nazev);
        for (Predmet predmet : predmety.values()) {
            if (normalizujText(predmet.getNazev()).equals(hledany)) {
                return predmet;
            }
        }
        return null;
    }

    public void pridejPostavu(Postava postava) {
        postavy.put(postava.getJmeno(), postava);
    }

    public Postava getPostavu(String jmeno) {
        if (jmeno == null) {
            return null;
        }
        String hledany = normalizujText(jmeno);
        for (Postava postava : postavy.values()) {
            if (normalizujText(postava.getJmeno()).equals(hledany)) {
                return postava;
            }
        }
        return null;
    }

    public void vypisPredmety() {
        if (predmety.isEmpty()) {
            return;
        }
        System.out.println("Předměty v lokaci:");
        for (Predmet predmet : predmety.values()) {
            System.out.println("- " + predmet.getNazev() + ": " + predmet.getPopis());
        }
    }

    public void vypisPostavy() {
        if (postavy.isEmpty()) {
            return;
        }
        System.out.println("Postavy v lokaci:");
        for (Postava postava : postavy.values()) {
            System.out.println("- " + postava.getJmeno());
        }
    }

    public void vypisVychody() {
        System.out.print("Východy: ");
        for (String smer : vychody.keySet()) {
            System.out.print(smer + " ");
        }
        System.out.println();
    }
    public void vypisMapu() {
        System.out.println();
        System.out.println("           [Centrální mozek] ----- [Serverovna] ------------- [Skladiště]");
        System.out.println("                   |                   |                            |");
        System.out.println("         [Kancelář správce] -----  [Úklidová místnost] ----- [Centrální chodba]");
        System.out.println("                                                                    |");
        System.out.println("                                                            [Výtahová šachta]");
        System.out.println();
    }

    private String normalizujText(String text) {
        if (text == null) return null;

        return text
                .toLowerCase()
                .replace("ý", "y")
                .replace("ě","e")
                .replace("č","c")
                .replace("í","i")
                .replace("š","s")
                .replace("ú","u")
                .replace("ř","r")
                .replace("á", "a")
                .trim();
    }

}
