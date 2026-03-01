public class PrikazNapoveda implements Prikaz {
    /**
     * Hlavní definice třídy PrikazNapoveda.
     *
     * @author Jakub Klimeš
     */
    private final PrikazovyManager manager;

    /**
     * Vytvoří instanci třídy PrikazNapoveda.
     */
    public PrikazNapoveda(PrikazovyManager manager) {
        this.manager = manager;
    }

    /**
     * Vypíše nápovědu.
     */
    public void proved(String[] parametry) {
        manager.vypisNapovedu();
    }


    public String getNazev() {
        return "napoveda";
    }


    public String getPopis() {
        return "Vypíše seznam dostupných příkazů.";
    }
}
