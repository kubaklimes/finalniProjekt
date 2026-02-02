public class Hektor extends DialogovaPostava {

        private int zivoty = 3;

    public Hektor() {
            super("Hektor");
        }

            public void zahajHackovani(){
                System.out.println("Hektor: Zahajuji hackování!");
            }
            public void utok(){
                zivoty = Math.max(0, zivoty - 1);
                System.out.println("Hektor: Útok! Zbývající životy: " + zivoty);
            }
            public void obrana(){
                System.out.println("Hektor: Bráním se.");
            }
            public boolean jePorazen(){
                return zivoty <= 0;
            }

            @Override
            public void mluv() {
                System.out.println("Hektor: Pokud chceš projít, musíš mě porazit.");
            }
        }