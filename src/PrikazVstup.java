public class PrikazVstup implements Prikaz {
    private final Hra hra;

    public PrikazVstup(Hra hra) {
        this.hra = hra;
    }


    public void proved(String[] parametry) {
        if (parametry.length < 2) {
            System.out.println("Použití: vstup <místo>");
            return;
        }
        String nazev = String.join(" ", java.util.Arrays.copyOfRange(parametry, 1, parametry.length));
        hra.vstupDo(nazev);
    }


    public String getNazev() {
        return "vstup";
    }


    public String getPopis() {
        return "Vstoupí do konkrétního místa (vstup <místo>).";
    }
}
