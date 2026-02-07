public class PrikazDetail implements Prikaz {
    private final Hra hra;

    public PrikazDetail(Hra hra) {
        this.hra = hra;
    }

    @Override
    public void proved(String[] parametry) {
        if (parametry.length < 2) {
            System.out.println("Použití: detail <úkol>");
            return;
        }
        String nazev = String.join(" ", java.util.Arrays.copyOfRange(parametry, 1, parametry.length));
        hra.getSpravceUkolu().detailUkolu(nazev);
    }

    @Override
    public String getNazev() {
        return "detail";
    }

    @Override
    public String getPopis() {
        return "Vypíše detail úkolu (detail <úkol>).";
    }
}
