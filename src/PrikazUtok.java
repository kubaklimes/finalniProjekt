public class PrikazUtok implements Prikaz {
    private final Hra hra;

    public PrikazUtok(Hra hra) {
        this.hra = hra;
    }


    public void proved(String[] parametry) {
        hra.provedUtok();
    }


    public String getNazev() {
        return "utok";
    }


    public String getPopis() {
        return "Spustí útok v hackovacím souboji.";
    }
}
