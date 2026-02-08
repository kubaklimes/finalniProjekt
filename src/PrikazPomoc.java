public class PrikazPomoc implements Prikaz {
    private final PrikazovyManager manager;

    public PrikazPomoc(PrikazovyManager manager) {
        this.manager = manager;
    }


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
