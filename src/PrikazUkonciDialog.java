public class PrikazUkonciDialog implements Prikaz {
    /**
     * Hlavní definice třídy PrikazUkonciDialog.
     *
     * @author Jakub Klimeš
     */
    private final Hra hra;

    public PrikazUkonciDialog(Hra hra) {
        this.hra = hra;
    }

    /**
     * Ukončí dialog s postavou.
     */
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
