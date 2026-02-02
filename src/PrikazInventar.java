public class PrikazInventar implements Prikaz {

    private final Hra hra;


    public PrikazInventar(Hra hra) {
        this.hra = hra;
    }

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
