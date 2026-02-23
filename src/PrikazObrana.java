public class PrikazObrana implements Prikaz {
    /**
     * Hlavní definice třídy PrikazObrana.
     *
     * @author já
     */
    private final Hra hra;

    public PrikazObrana(Hra hra) {
        this.hra = hra;
    }

    /**
     * Provede operaci obrana.
     */
    public void proved(String[] parametry) {
        hra.provedObranu();
    }


    public String getNazev() {
        return "obrana";
    }

    public String getPopis() {
        return "Spustí obranu v hackovacím souboji.";
    }
}
