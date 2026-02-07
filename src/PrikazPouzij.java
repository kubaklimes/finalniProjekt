public class PrikazPouzij implements Prikaz {
        private final Hra hra;

    public PrikazPouzij(Hra hra) {
            this.hra = hra;
        }

            public void proved(String[] parametry){
                if (parametry.length < 2) {
                    System.out.println("Použití: pouzij <předmět>");
                    return;
                }
                String nazev = String.join(" ", java.util.Arrays.copyOfRange(parametry, 1, parametry.length));
                Inventar inventar = hra.getHrac().getInventar();
                Predmet predmet = inventar.getPredmet(nazev);
                if (predmet == null) {
                    System.out.println("Předmět \"" + nazev + "\" nemáš v inventáři.");
                    return;
                }
                predmet.pouzij(hra);
            }
            public String getNazev(){
                return "pouzij";
            }
            public String getPopis(){
                return "Použije předmět z inventáře (pouzij <předmět>).";
            }
        }
