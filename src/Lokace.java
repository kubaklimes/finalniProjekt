import java.util.HashMap;
import java.util.Map;

public class Lokace {

    private String nazev;
    private String popis;
    private boolean zamcena;
    private boolean navstivena;

    private Map<String, Lokace> vychody;
    private Map<String, Predmet> predmety;
    private Map<String, Postava> postavy;

    public Lokace(String nazev, String popis, boolean zamcena) {
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
        for (Predmet predmet : predmety.values()) {
            if (predmet.getNazev().equalsIgnoreCase(nazev)) {
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
        for (Postava postava : postavy.values()) {
            if (postava.getJmeno().equalsIgnoreCase(jmeno)) {
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

}
