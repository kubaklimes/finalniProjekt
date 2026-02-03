public class Ukol {
    private final String nazev;
    private final String popis;
    private boolean splnen;


    public Ukol(String nazev, String popis) {
            this.nazev = nazev;
            this.popis = popis;
            this.splnen = false;
        }


        public String getNazev(){
            return nazev;
        }
        public String getPopis(){
            return popis;
        }
        public boolean jeSplnen(){
            return splnen;
        }
        public void splnit(){
            splnen = true;
        }
    }