public class PrikazNapoveda implements Prikaz {
    private final Hra hra;

    public PrikazNapoveda(Hra hra) {
        this.hra = hra;
    }


    public void proved(String[] parametry) {
        hra.vypisNapovedu();
    }


    public String getNazev() {
        return "napoveda";
    }


    public String getPopis() {
        return "Dá radu jak postupovat dál.";
    }
}
