public class PrikazInventar implements Prikaz {
    /**
     * Hlavní definice třídy PrikazInventar.
     *
     * @author Jakub Klimeš
     */

    private final Hra hra;


    public PrikazInventar(Hra hra) {
        this.hra = hra;
    }
    /**
     * Vypíše obsah inventáře.
     */
        public void proved(String[] parametry){
            hra.getHrac().getInventar().vypisObsah();
        }

        public String getNazev(){
            return "inventar";
        }
        public String getPopis(){
            return "Vypíše obsah inventáře.";
        }
}
