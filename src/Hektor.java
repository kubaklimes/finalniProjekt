public class Hektor extends DialogovaPostava {

        private int zivoty = 3;
        private boolean idKartaVydana;

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

            @Override
            public void mluv() {
                System.out.println("Hektor: Pokud chceš projít, musíš mě porazit.");
            }

            @Override
            protected void vypisUvod(Hra hra) {
                if (!hra.maSplneneUvodniUkoly()) {
                    System.out.println("Hektor: Neznámý uživatel. Přístup odepřen.");
                    return;
                }
                if (!idKartaVydana) {
                    System.out.println("Hektor: Pro ověření vyřeš hádanku.");
                    System.out.println("Který z těchto obvodů je nejtišší?");
                    System.out.println("1) Přetížený.");
                    System.out.println("2) Vypnutý.");
                    System.out.println("3) Iskřící.");
                    return;
                }
                System.out.println("Hektor: Jsi připraven na hackovací duel?");
                System.out.println("1) Začít hackování.");
                System.out.println("2) Odejdeme.");
            }

            @Override
            protected void zpracujOdpoved(int volba, Hra hra) {
                if (!hra.maSplneneUvodniUkoly()) {
                    return;
                }
                if (!idKartaVydana) {
                    if (volba == 2) {
                        idKartaVydana = true;
                        System.out.println("Hektor: Správně. Tady máš ID kartu.");
                        hra.ziskejIDKartu();
                        hra.splnUkol("ID karta");
                        hra.ukonciDialog();
                        return;
                    }
                    System.out.println("Hektor: Špatně. Zkus to znovu.");
                    return;
                }
                if (volba == 1) {
                    hra.zahajHackovani();
                    return;
                }
                hra.ukonciDialog();
            }
        }
