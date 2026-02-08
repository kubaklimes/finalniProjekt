public class PrikazRada implements Prikaz {
    private final Hra hra;

    public PrikazRada(Hra hra) {
        this.hra = hra;
    }


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
