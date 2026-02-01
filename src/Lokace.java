import java.util.HashMap;
import java.util.Map;

public class Lokace {

    private String nazev;
    private String popis;

    private Map<String, Lokace> vychody;
    private Map<String, Predmet> predmety;
    private Map<String, Postava> postavy;

    public Lokace(String nazev, String popis) {
        this.nazev = nazev;
        this.popis = popis;
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

    public void pridejPostavu(Postava postava) {
        postavy.put(postava.getJmeno(), postava);
    }

    public Postava getPostavu(String jmeno) {
        return postavy.get(jmeno);
    }

    public void vypisVychody() {
        System.out.print("Východy: ");
        for (String smer : vychody.keySet()) {
            System.out.print(smer + " ");
        }
        System.out.println();
    }

}
