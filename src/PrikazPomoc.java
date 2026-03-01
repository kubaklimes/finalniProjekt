public class PrikazPomoc implements Prikaz {
    /**
     * Hlavní definice třídy PrikazPomoc.
     *
     * @author Jakub Klimeš
     */
    private final PrikazovyManager manager;

    public PrikazPomoc(PrikazovyManager manager) {
        this.manager = manager;
    }

    /**
     * Vypíše nápovědu.
     */
    public void proved(String[] parametry) {
        manager.vypisNapovedu();
    }


    public String getNazev() {
        return "pomoc";
    }


    public String getPopis() {
        return "Zobrazí nápovědu k příkazům.";
    }
}
