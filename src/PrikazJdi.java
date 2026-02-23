import java.text.Normalizer;

public class PrikazJdi implements Prikaz {
    /**
     * Hlavní definice třídy PrikazJdi.
     *
     * @author uživatel
     */
    private final Hra hra;

    /**
     * Vytvoří instanci třídy PrikazJdi.
     */
    public PrikazJdi(Hra hra) {
        this.hra = hra;
        }

    /**
     * Provede příkaz jdi.
     */
    public void proved(String[] parametry){
        if (parametry.length < 2) {
            System.out.println("Použití: jdi <směr>");
            return;
        }
        String smer = normalizujText(parametry[1]);
        hra.pohniHrace(smer);
    }
    public String getNazev(){
        return "jdi";
            }
            public String getPopis(){
                               return "Přesun do jiné lokace (jdi <směr>).";
            }
    private String normalizujText(String text) {
        if (text == null) return null;

        return text
                .toLowerCase()
                .replace("ý", "y")
                .replace("á", "a")
                .trim();
    }
}

