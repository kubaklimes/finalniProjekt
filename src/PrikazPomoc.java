public class PrikazPomoc implements Prikaz {
    private final PrikazovyManager manager;

    public PrikazPomoc(PrikazovyManager manager) {
        this.manager = manager;
    }

    @Override
    public void proved(String[] parametry) {
        manager.vypisNapovedu();
    }

    @Override
    public String getNazev() {
        return "pomoc";
    }

    @Override
    public String getPopis() {
        return "Zobrazí nápovědu k příkazům.";
    }
}
