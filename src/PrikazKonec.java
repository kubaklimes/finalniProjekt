public class PrikazKonec implements Prikaz {
    private final Hra hra;

    public PrikazKonec(Hra hra) {
        this.hra = hra;
    }


    public void proved(String[] parametry) {
        hra.konecHry();
    }


    public String getNazev() {
        return "konec";
    }


    public String getPopis() {
        return "Ukončí hru.";
    }
}
