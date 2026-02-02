import java.util.Scanner;

public class Hra {

    private Hrac hrac;
    private HerniSvet svet;
    private PrikazovyManager prikazovyManager;
    private SpravceUkolu spravceUkolu;
    private boolean konec = false;

    public void start() {
        svet = new HerniSvet();
        svet.nactiZeSouboru("svet.json");

        hrac = new Hrac(svet.getStartovniLokace());
        spravceUkolu = new SpravceUkolu();
        prikazovyManager = new PrikazovyManager();
        registrujPrikazy();

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
        prikazovyManager.vykonejPrikaz(vstup);
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
    private void registrujPrikazy() {
        prikazovyManager.registrujPrikaz(new PrikazJdi(this));
        prikazovyManager.registrujPrikaz(new PrikazMluv(this));
        prikazovyManager.registrujPrikaz(new PrikazPouzij(this));
        prikazovyManager.registrujPrikaz(new PrikazInventar(this));
        prikazovyManager.registrujPrikaz(new PrikazKonec(this));
        prikazovyManager.registrujPrikaz(new PrikazNapoveda(prikazovyManager));
    }
}
