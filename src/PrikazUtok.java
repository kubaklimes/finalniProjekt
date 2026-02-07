public class PrikazUtok implements Prikaz {
    private final Hra hra;

    public PrikazUtok(Hra hra) {
        this.hra = hra;
    }

    @Override
    public void proved(String[] parametry) {
        hra.provedUtok();
    }

    @Override
    public String getNazev() {
        return "utok";
    }

    @Override
    public String getPopis() {
        return "Spustí útok v hackovacím souboji.";
    }
}
