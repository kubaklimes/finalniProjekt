public class Bzucak extends Postava {
    public Bzucak() {
            super("Bzučák");
        }

        public void komentarKLokaci(Lokace lokace){
            System.out.println("Bzučák: Vypadá to tu zajímavě. (" + lokace.getNazev() + ")");
        }
        public void napoveda(){
            System.out.println("Bzučák: Zkus se rozhlédnout a mluvit s postavami.");
        }

        public void napovedaKPostupu(String text){
            System.out.println("Bzučák: " + text);
        }

        public void mluv() {
            System.out.println("Bzučák: Vždycky je nějaká cesta dál.");
        }
    }
