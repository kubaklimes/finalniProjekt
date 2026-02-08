public class PrikazOdpovez implements Prikaz {
    private final Hra hra;

    public PrikazOdpovez(Hra hra) {
        this.hra = hra;
    }


    public void proved(String[] parametry) {
        if (parametry.length < 2) {
            System.out.println("Použití: odpovez <číslo>");
            return;
        }
        try {
            int volba = Integer.parseInt(parametry[1]);
            hra.odpovezDialogu(volba);
        } catch (NumberFormatException e) {
            System.out.println("Zadej číslo volby.");
        }
    }


    public String getNazev() {
        return "odpovez";
    }


    public String getPopis() {
        return "Odpoví v dialogu (odpovez <číslo>).";
    }
}
