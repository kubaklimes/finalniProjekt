public class PrikazUkonciDialog implements Prikaz {
    private final Hra hra;

    public PrikazUkonciDialog(Hra hra) {
        this.hra = hra;
    }


    public void proved(String[] parametry) {
        hra.ukonciDialog();
    }


    public String getNazev() {
        return "ukonci";
    }


    public String getPopis() {
        return "Ukončí aktivní dialog.";
    }
}
