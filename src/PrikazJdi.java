import java.text.Normalizer;

public class PrikazJdi implements Prikaz {
    private final Hra hra;

    public PrikazJdi(Hra hra) {
        this.hra = hra;
        }

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

