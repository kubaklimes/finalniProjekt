public class Hektor extends DialogovaPostava {

    private int zivoty = 3;

    public Hektor() {
            super("Hektor");
        }
    public void resetZivoty() {
        zivoty = 3;
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


            public void mluv() {
                System.out.println("Hektor: Pokud chceš projít, musíš mě porazit.");
            }

    protected void vypisUvod(Hra hra) {
        System.out.println("Hektor: Jsi připraven na hackovací duel?");
        System.out.println("1) Začít hackování.");
        System.out.println("2) Odejdeme.");
    }


    protected void zpracujOdpoved(int volba, Hra hra) {
        if (volba == 1) {
            hra.zahajHackovani();
            return;
        }
        hra.ukonciDialog();
    }
}