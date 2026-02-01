import java.util.Scanner;

public class Hra {

    private Hrac hrac;
    private HerniSvet svet;
    private SpravceUkolu spravceUkolu;
    private boolean konec = false;

    public void start() {
        svet = new HerniSvet();
        svet.nactiZeSouboru("svet.json");

        hrac = new Hrac(svet.getStartovniLokace());
        spravceUkolu = new SpravceUkolu();

        Scanner sc = new Scanner(System.in);

        System.out.println("Hra spuštěna.");
        vypisAktualniLokaci();

        while (!konec) {
            System.out.print("> ");
            String vstup = sc.nextLine();
            zpracujPrikaz(vstup);
        }
    }

    public void konecHry() {
        konec = true;
        System.out.println("Hra byla ukončena.");
    }

    public void zpracujPrikaz(String vstup) {

        if (vstup.equals("konec")) {
            konecHry();
            return;
        }

        if (vstup.startsWith("jdi ")) {
            String smer = vstup.substring(4);
            hrac.jdi(smer);
            vypisAktualniLokaci();
            return;
        }

        System.out.println("Neznámý příkaz.");
    }

    private void vypisAktualniLokaci() {
        Lokace l = hrac.getAktualniLokace();
        System.out.println("Nacházíš se v: " + l.getNazev());
        System.out.println(l.getPopis());
    }

    public Hrac getHrac() {
        return hrac;
    }

    public HerniSvet getSvet() {
        return svet;
    }

    public SpravceUkolu getSpravceUkolu() {
        return spravceUkolu;
    }
}
