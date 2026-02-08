public class PrikazUkoly implements Prikaz {
    private final Hra hra;

    public PrikazUkoly(Hra hra) {
        this.hra = hra;
    }


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
