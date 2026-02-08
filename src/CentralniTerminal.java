public class CentralniTerminal extends DialogovaPostava {
    public CentralniTerminal() {
        super("Centrální terminál");
    }


    public void mluv() {
        System.out.println("Centrální terminál: Přístup k autorizaci je omezen.");
    }


    protected void vypisUvod(Hra hra) {
        if (!hra.maSplneneUvodniUkoly()) {
            System.out.println("Centrální terminál: Přístup odmítnut. Získej důvěru robotů.");
            return;
        }
        System.out.println("Centrální terminál: Pro ověření vyřeš hádanku.");
        System.out.println("Který z těchto obvodů je nejtišší?");
        System.out.println("1) Přetížený.");
        System.out.println("2) Vypnutý.");
        System.out.println("3) Iskřící.");
    }


    protected void zpracujOdpoved(int volba, Hra hra) {
        if (!hra.maSplneneUvodniUkoly()) {
            return;
        }
        if (volba == 2) {
            System.out.println("Centrální terminál: Správně. Tady máš ID kartu.");
            hra.ziskejIDKartu();
            hra.splnUkol("ID karta");
            hra.ukonciDialog();
            return;
        }
        System.out.println("Centrální terminál: Špatně. Zkus to znovu.");
    }
}