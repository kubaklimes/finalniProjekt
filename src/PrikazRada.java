public class PrikazRada implements Prikaz {
    /**
     * Hlavní definice třídy PrikazRada.
     *
     * @author já
     */
    private final Hra hra;

    public PrikazRada(Hra hra) {
        this.hra = hra;
    }

    /**
     * Hra ti poradí jak dále.
     */
    public void proved(String[] parametry) {
        hra.vypisNapovedu();
    }


    public String getNazev() {
        return "rada";
    }


    public String getPopis() {
        return "Dá radu, jak postupovat dál.";
    }
}
