import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.text.Normalizer;

public class Inventar {
    /**
     * Hlavní definice třídy Inventar.
     *
     * @author Jakub Klimeš,chatgpt
     */

    private int kapacita;
    private List<Predmet> predmety;

    public Inventar(int kapacita){
        /**
         * Vytvoří instanci třídy Inventar.
         */
        this.kapacita = kapacita;
        this.predmety = new ArrayList<>();
    }
    /**
     * Přidá předmět do inventáře pokud není plný a je přenosný.
     */
    public boolean pridejPredmet(Predmet predmet){
        if (predmet == null) {
            return false;
        }
        if (!predmet.jePrenosny()) {
            System.out.println("Tento předmět nelze přenést.");
            return false;
        }
        if (predmety.size() >= kapacita) {
            System.out.println("Inventář je plný.");
            return false;
        }
        predmety.add(predmet);
        System.out.println("Předmět \"" + predmet.getNazev() + "\" byl přidán do inventáře.");
        return true;
    }
    /**
     * Odebere předmět z inventáře pokud ho tam máš.
     */
    public void odeberPredmet(String nazev){
        Predmet predmet = getPredmet(nazev);
        if (predmet != null) {
            predmety.remove(predmet);
            System.out.println("Předmět \"" + nazev + "\" byl odebrán z inventáře.");
            return;
        }
        System.out.println("Předmět \"" + nazev + "\" v inventáři není.");
    }
    /**
     * Odebere předmět pokud se vyskytuje prach.
     */
    public Predmet odeberPredmetBezHlaseni(String nazev) {
        Predmet predmet = getPredmet(nazev);
        if (predmet != null) {
            predmety.remove(predmet);
        }
        return predmet;
    }
    /**
     * Odebere poslední předmět v inventáři.
     */
    public Predmet odeberPosledniPredmet() {
        if (predmety.isEmpty()) {
            return null;
        }
        return predmety.remove(predmety.size() - 1);
    }

    public boolean obsahuje(String nazev){
        return getPredmet(nazev) != null;
    }
    /**
     * Vypíše obsah inventáře.
     */
    public void vypisObsah(){
        if (predmety.isEmpty()) {
            System.out.println("Inventář je prázdný.");
            return;
        }
        System.out.println("Inventář (" + predmety.size() + "/" + kapacita + "):");
        for (Predmet predmet : predmety) {
            System.out.println("- " + predmet.getNazev() + ": " + predmet.getPopis());
        }
    }

    public Predmet getPredmet(String nazev) {
        if (nazev == null) {
            return null;
        }
        String hledany = normalizujText(nazev);
        for (Predmet predmet : predmety) {
            if (normalizujText(predmet.getNazev()).equals(hledany)) {
                return predmet;
            }
        }
        return null;
        }

    public List<Predmet> getPredmety() {
        return Collections.unmodifiableList(predmety);
    }
    /**
     * Normalizuje text aby byl bez diakritiky.
     */
    private String normalizujText(String text) {
        String normalized = Normalizer.normalize(text, Normalizer.Form.NFD);
        normalized = normalized.replaceAll("\\p{M}", "");
        return normalized.toLowerCase();
    }
    }
