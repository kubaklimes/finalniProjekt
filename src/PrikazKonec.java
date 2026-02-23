public class PrikazKonec implements Prikaz {
    /**
     * Hlavní definice třídy PrikazKonec.
     *
     * @author já
     */
    private final Hra hra;
    /**
     * Vytvoří instanci třídy PrikazKonec.
     */
    public PrikazKonec(Hra hra) {
        this.hra = hra;
    }

    /**
     * Provede příkaz konec hry.
     */
    public void proved(String[] parametry) {
        hra.konecHry();
    }


    public String getNazev() {
        return "konec";
    }


    public String getPopis() {
        return "Ukončí hru.";
    }
}
