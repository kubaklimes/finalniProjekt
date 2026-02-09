public class Hektor extends DialogovaPostava {

    private int zivoty = 5;
    private boolean stitAktivni;
    private boolean nabity;

    public Hektor() {
            super("Hektor");
        }
    public void resetZivoty() {
        zivoty = 5;
        stitAktivni = false;
        nabity = false;
    }
            public void zahajHackovani(){
                System.out.println("Hektor: Zahajuji hackování!");
            }
            public void obrana(){
                System.out.println("Hektor: Bráním se.");
            }
            public boolean jePorazen(){
                return zivoty <= 0;
            }

            public boolean prijmiUtok(int zraneni) {
                if (stitAktivni) {
                    stitAktivni = false;
                    return false;
                }
                zivoty = Math.max(0, zivoty - zraneni);
                return true;
            }

            public void aktivujStit() {
                stitAktivni = true;
            }

            public void nabijSe() {
                nabity = true;
            }

            public boolean jeNabity() {
                return nabity;
            }

            public int utokNormal(java.util.Random random) {
                int bonus = zivoty <= 2 ? 1 : 0;
                return 1 + random.nextInt(2) + bonus;
            }

            public int utokNabity(java.util.Random random) {
                nabity = false;
                int bonus = zivoty <= 2 ? 1 : 0;
                return 2 + random.nextInt(2) + bonus;
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
