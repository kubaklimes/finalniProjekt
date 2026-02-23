public class PrikazUtok implements Prikaz {
    /**
     * Hlavní definice třídy PrikazUtok.
     *
     * @author já
     */
    private final Hra hra;

    public PrikazUtok(Hra hra) {
        this.hra = hra;
    }

    /**
     * Provede útok na Hektora.
     */
    public void proved(String[] parametry) {
        hra.provedUtok();
    }


    public String getNazev() {
        return "utok";
    }


    public String getPopis() {
        return "Spustí útok v hackovacím souboji.";
    }
}
