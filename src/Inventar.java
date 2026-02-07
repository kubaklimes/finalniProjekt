import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Inventar {

    private int kapacita;
    private List<Predmet> predmety;

    public Inventar(int kapacita){
        this.kapacita = kapacita;
        this.predmety = new ArrayList<>();
    }

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
    public void odeberPredmet(String nazev){
        Predmet predmet = getPredmet(nazev);
        if (predmet != null) {
            predmety.remove(predmet);
            System.out.println("Předmět \"" + nazev + "\" byl odebrán z inventáře.");
            return;
        }
        System.out.println("Předmět \"" + nazev + "\" v inventáři není.");
    }

    public Predmet odeberPredmetBezHlaseni(String nazev) {
        Predmet predmet = getPredmet(nazev);
        if (predmet != null) {
            predmety.remove(predmet);
        }
        return predmet;
    }

    public Predmet odeberPosledniPredmet() {
        if (predmety.isEmpty()) {
            return null;
        }
        return predmety.remove(predmety.size() - 1);
    }
    public boolean obsahuje(String nazev){
        return getPredmet(nazev) != null;
    }
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
        for (Predmet predmet : predmety) {
            if (predmet.getNazev().equalsIgnoreCase(nazev)) {
                return predmet;
            }
        }
        return null;
        }

    public List<Predmet> getPredmety() {
        return Collections.unmodifiableList(predmety);
    }
    }
