public class PrikazObrana implements Prikaz {
    private final Hra hra;

    public PrikazObrana(Hra hra) {
        this.hra = hra;
    }

    @Override
    public void proved(String[] parametry) {
        hra.provedObranu();
    }

    @Override
    public String getNazev() {
        return "obrana";
    }

    @Override
    public String getPopis() {
        return "Spustí obranu v hackovacím souboji.";
    }
}
