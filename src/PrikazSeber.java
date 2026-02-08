public class PrikazSeber implements Prikaz {
    private final Hra hra;

    public PrikazSeber(Hra hra) {
        this.hra = hra;
    }


    public void proved(String[] parametry) {
        if (parametry.length < 2) {
            System.out.println("Použití: seber <předmět>");
            return;
        }
        String nazev = String.join(" ", java.util.Arrays.copyOfRange(parametry, 1, parametry.length));
        Lokace lokace = hra.getHrac().getAktualniLokace();
        Predmet predmet = lokace.getPredmet(nazev);
        if (predmet == null) {
            System.out.println("Takový předmět tu není.");
            return;
        }
        if (hra.getHrac().getInventar().pridejPredmet(predmet)) {
            lokace.odeberPredmet(predmet.getNazev());
        }
    }


    public String getNazev() {
        return "seber";
    }


    public String getPopis() {
        return "Sebere předmět z lokace (seber <předmět>).";
    }
}
