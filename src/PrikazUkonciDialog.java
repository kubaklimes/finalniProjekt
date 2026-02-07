public class PrikazUkonciDialog implements Prikaz {
    private final Hra hra;

    public PrikazUkonciDialog(Hra hra) {
        this.hra = hra;
    }

    @Override
    public void proved(String[] parametry) {
        hra.ukonciDialog();
    }

    @Override
    public String getNazev() {
        return "ukonci";
    }

    @Override
    public String getPopis() {
        return "Ukončí aktivní dialog.";
    }
}
