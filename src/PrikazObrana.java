public class PrikazObrana implements Prikaz {
    private final Hra hra;

    public PrikazObrana(Hra hra) {
        this.hra = hra;
    }


    public void proved(String[] parametry) {
        hra.provedObranu();
    }


    public String getNazev() {
        return "obrana";
    }

    public String getPopis() {
        return "Spustí obranu v hackovacím souboji.";
    }
}
