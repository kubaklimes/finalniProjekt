public class PrikazDetail implements Prikaz {
    /**
     * Hlavní definice třídy PrikazDetail.
     *
     * @author Jakub Klimeš
     */
    private final Hra hra;


    /**
     * Vytvoří instanci třídy PrikazDetail.
     */
    public PrikazDetail(Hra hra) {
        this.hra = hra;
    }

    /**
     * Vypíše detail úkolu.
     */
    public void proved(String[] parametry) {
        if (parametry.length < 2) {
            System.out.println("Použití: detail <úkol>");
            return;
        }
        String nazev = String.join(" ", java.util.Arrays.copyOfRange(parametry, 1, parametry.length));
        hra.getSpravceUkolu().detailUkolu(nazev);
    }


    public String getNazev() {
        return "detail";
    }


    public String getPopis() {
        return "Vypíše detail úkolu (detail <úkol>).";
    }
}
