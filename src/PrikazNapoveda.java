public class PrikazNapoveda implements Prikaz {
    private final PrikazovyManager manager;

    public PrikazNapoveda(PrikazovyManager manager) {
        this.manager = manager;
    }


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
