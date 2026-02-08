public class Sberac extends DialogovaPostava {
    public Sberac() {
        super("Sběrač");
    }

    public void prijmiBaterie(){
        System.out.println("Sběrač: Díky za baterie. Budu vděčný navždy.");
    }

    public void mluv() {
        System.out.println("Sběrač: Hledám baterie do sběrače.");
    }


    protected void vypisUvod(Hra hra) {
        System.out.println("Sběrač: Máš pro mě baterie?");
        System.out.println("1) Předat baterie.");
        System.out.println("2) Ještě je hledám.");
    }


    protected void zpracujOdpoved(int volba, Hra hra) {
        if (volba == 1) {
            Inventar inventar = hra.getHrac().getInventar();
            if (!inventar.obsahuje("Baterie")) {
                System.out.println("Sběrač: Žádné baterie u tebe nevidím.");
                return;
            }
            inventar.odeberPredmet("Baterie");
            prijmiBaterie();
            hra.splnUkol("Baterie");
            hra.ukonciDialog();
            return;
        }
        System.out.println("Sběrač: Tak je najdi ve starých kancelářích.");
    }
}
