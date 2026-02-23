public class PrikazUkoly implements Prikaz {
    /**
     * Hlavní definice třídy PrikazUkoly.
     *
     * @author já
     */
    private final Hra hra;

    public PrikazUkoly(Hra hra) {
        this.hra = hra;
    }

    /**
     * Vypíše všechny úkoly.
     */
    public void proved(String[] parametry) {
        hra.getSpravceUkolu().vypisUkoly();
    }


    public String getNazev() {
        return "ukoly";
    }


    public String getPopis() {
        return "Vypíše seznam úkolů.";
    }
}
