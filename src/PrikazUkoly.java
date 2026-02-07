public class PrikazUkoly implements Prikaz {
    private final Hra hra;

    public PrikazUkoly(Hra hra) {
        this.hra = hra;
    }

    @Override
    public void proved(String[] parametry) {
        hra.getSpravceUkolu().vypisUkoly();
    }

    @Override
    public String getNazev() {
        return "ukoly";
    }

    @Override
    public String getPopis() {
        return "Vypíše seznam úkolů.";
    }
}
