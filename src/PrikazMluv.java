public class PrikazMluv implements Prikaz {
        private final Hra hra;

    public PrikazMluv(Hra hra) {
            this.hra = hra;
        }

        public void proved(String[] parametry){
                if (parametry.length < 2) {
                    System.out.println("Použití: mluv <jméno>");
                    return;
                }
                String jmeno = String.join(" ", java.util.Arrays.copyOfRange(parametry, 1, parametry.length));
                Lokace lokace = hra.getHrac().getAktualniLokace();
                Postava postava = lokace.getPostavu(jmeno);
                if (postava == null) {
                    System.out.println("Postava \"" + jmeno + "\" tu není.");
                    return;
                }
            if (postava instanceof Sberac && hra.getSpravceUkolu().jeUkolSplnen("Baterie")) {
                System.out.println("Sběrač už má hotovo a nemá pro tebe další práci.");
                return;
            }
            if (postava instanceof X100 && hra.getSpravceUkolu().jeUkolSplnen("Kabely")) {
                System.out.println("X100 už je opravený a další úkoly nemá.");
                return;
            }
            if (postava instanceof DialogovaPostava dialogovaPostava) {
                hra.zahajDialog(dialogovaPostava);
            }
            {
                postava.mluv();
            }
            }
            public String getNazev(){
                return "mluv";
            }
            public String getPopis(){
                return "Promluví s postavou v lokaci (mluv <jméno>).";
            }
        }
