import java.util.Scanner;

public class Hra {

    private Hrac hrac;
    private HerniSvet svet;
    private PrikazovyManager prikazovyManager;
    private SpravceUkolu spravceUkolu;
    private Bzucak bzucak;
    private DialogovaPostava aktivniDialog;
    private Hektor hektor;
    private boolean hackovaniAktivni;
    private int integritaSystemu;
    private int pocitadloChodby;
    private boolean prachZiskan;
    private boolean hektorPorazen;
    private boolean konec = false;

    public void start() {
        svet = new HerniSvet();
        svet.nactiZeSouboru("resource/svet.json");

        hrac = new Hrac(svet.getStartovniLokace());
        spravceUkolu = new SpravceUkolu();
        bzucak = new Bzucak();
        prikazovyManager = new PrikazovyManager();
        pripravSvet();
        registrujPrikazy();

        Scanner sc = new Scanner(System.in);

        System.out.println("Hra spuštěna.");
        System.out.println("Marek se probouzí v zamčené úklidové skříni.");
        vypisAktualniLokaci();
        hrac.getAktualniLokace().oznacNavstivenou();
        bzucak.komentarKLokaci(hrac.getAktualniLokace());
        bzucak.napoveda();

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
        l.vypisPostavy();
        l.vypisPredmety();
        l.vypisVychody();
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
        prikazovyManager.registrujPrikaz(new PrikazVstup(this));
        prikazovyManager.registrujPrikaz(new PrikazSeber(this));
        prikazovyManager.registrujPrikaz(new PrikazMluv(this));
        prikazovyManager.registrujPrikaz(new PrikazOdpovez(this));
        prikazovyManager.registrujPrikaz(new PrikazUkonciDialog(this));
        prikazovyManager.registrujPrikaz(new PrikazPouzij(this));
        prikazovyManager.registrujPrikaz(new PrikazInventar(this));
        prikazovyManager.registrujPrikaz(new PrikazUkoly(this));
        prikazovyManager.registrujPrikaz(new PrikazDetail(this));
        prikazovyManager.registrujPrikaz(new PrikazUtok(this));
        prikazovyManager.registrujPrikaz(new PrikazObrana(this));
        prikazovyManager.registrujPrikaz(new PrikazKonec(this));
        prikazovyManager.registrujPrikaz(new PrikazPomoc(prikazovyManager));
        prikazovyManager.registrujPrikaz(new PrikazNapoveda(this));
    }

    private void pripravSvet() {
        Lokace skladiste = svet.getLokace("Skladiště");
        Lokace kancelar = svet.getLokace("Kancelář správce");
        Lokace serverovna = svet.getLokace("Serverovna");
        Lokace mozek = svet.getLokace("Centrální mozek");

        if (skladiste != null) {
            skladiste.pridejPostavu(new Sberac());
        }
        if (serverovna != null) {
            serverovna.pridejPostavu(new X100());
        }
        if (mozek != null) {
            hektor = new Hektor();
            mozek.pridejPostavu(hektor);
        }
        if (kancelar != null) {
            kancelar.pridejPredmet(new Baterie());
        }

        spravceUkolu.pridejUkol(new Ukol("Baterie", "Najít baterie pro robota Sběrače."));
        spravceUkolu.pridejUkol(new Ukol("Kabely", "Opravit datové kabely pro X100."));
        spravceUkolu.pridejUkol(new Ukol("ID karta", "Získat ID kartu v centrálním mozku."));
        spravceUkolu.pridejUkol(new Ukol("Hektor", "Přelstít Hektora v hackování."));
        spravceUkolu.pridejUkol(new Ukol("Evakuace", "Odemknout výtahovou šachtu a uniknout."));
    }

    public void pohniHrace(String smer) {
        if (hackovaniAktivni) {
            System.out.println("Nemůžeš odejít během hackování.");
            return;
        }
        if (aktivniDialog != null) {
            aktivniDialog.ukoncitDialog();
            aktivniDialog = null;
        }
        Lokace aktualni = hrac.getAktualniLokace();
        Lokace nova = aktualni.getVychod(smer);
        if (nova == null) {
            System.out.println("Tímto směrem se jít nedá.");
            return;
        }
        if (nova.jeZamcena()) {
            System.out.println("Dveře jsou zamčené. Potřebuješ přístup.");
            return;
        }
        if ("Centrální mozek".equals(nova.getNazev()) && !maSplneneUvodniUkoly()) {
            System.out.println("Bez důvěry robotů tě Hektor dál nepustí.");
            return;
        }
        hrac.presunSe(nova);
        if (!nova.jeNavstivena()) {
            nova.oznacNavstivenou();
        }
        vypisAktualniLokaci();
        bzucak.komentarKLokaci(nova);
        kontrolaPrachu(nova);
        if ("Výtahová šachta".equals(nova.getNazev()) && !nova.jeZamcena()) {
            System.out.println("Dveře výtahu se otevírají. Marek uniká na povrch!");
            konecHry();
        }
    }

    public void vstupDo(String nazev) {
        if (nazev == null || nazev.isBlank()) {
            System.out.println("Použití: vstup <místo>.");
            return;
        }
        Lokace cil = svet.getLokace(normalizujNazevLokace(nazev));
        if (cil == null) {
            System.out.println("Takové místo neexistuje.");
            return;
        }
        Lokace aktualni = hrac.getAktualniLokace();
        boolean soused = false;
        for (String smer : new String[] { "sever", "jih", "východ", "západ" }) {
            if (aktualni.getVychod(smer) == cil) {
                soused = true;
                pohniHrace(smer);
                break;
            }
        }
        if (!soused) {
            System.out.println("Odtud se tam vstoupit nedá.");
        }
    }

    private String normalizujNazevLokace(String nazev) {
        return switch (nazev.toLowerCase()) {
            case "vytah", "výtah", "šachta", "sachta", "vytahova", "výtahová" -> "Výtahová šachta";
            case "mozko", "mozek", "centrální mozek", "centralni mozek" -> "Centrální mozek";
            case "skladiste", "skladiště" -> "Skladiště";
            case "serverovna" -> "Serverovna";
            case "kancelar", "kancelář", "kancelar spravce", "kancelář správce" -> "Kancelář správce";
            case "chodba", "centralni chodba", "centrální chodba" -> "Centrální chodba";
            case "uklidova mistnost", "úklidová místnost" -> "Úklidová místnost";
            default -> nazev;
        };
    }

    private void kontrolaPrachu(Lokace nova) {
        if (!"Centrální chodba".equals(nova.getNazev())) {
            pocitadloChodby = 0;
            return;
        }
        pocitadloChodby++;
        if (pocitadloChodby < 2 || prachZiskan) {
            return;
        }
        prachZiskan = true;
        Prach prach = new Prach();
        Inventar inventar = hrac.getInventar();
        if (inventar.pridejPredmet(prach)) {
            return;
        }
        Predmet vyhozeny = inventar.odeberPosledniPredmet();
        if (vyhozeny != null) {
            nova.pridejPredmet(vyhozeny);
            System.out.println("Prach ti zaplnil kapsu a něco ti vypadlo na zem.");
            inventar.pridejPredmet(prach);
        }
    }

    public void zahajDialog(DialogovaPostava postava) {
        if (aktivniDialog != null) {
            aktivniDialog.ukoncitDialog();
        }
        aktivniDialog = postava;
        postava.zahajDialog(this);
    }

    public void odpovezDialogu(int volba) {
        if (aktivniDialog == null) {
            System.out.println("Žádný dialog není aktivní.");
            return;
        }
        aktivniDialog.odpovez(volba, this);
    }

    public void ukonciDialog() {
        if (aktivniDialog == null) {
            System.out.println("Žádný dialog není aktivní.");
            return;
        }
        aktivniDialog.ukoncitDialog();
        aktivniDialog = null;
    }

    public boolean maSplneneUvodniUkoly() {
        return spravceUkolu != null
                && spravceUkolu.jeUkolSplnen("Baterie")
                && spravceUkolu.jeUkolSplnen("Kabely");
    }

    public void splnUkol(String nazev) {
        spravceUkolu.splnitUkol(nazev);
        if ("Baterie".equals(nazev) || "Kabely".equals(nazev)) {
            if (maSplneneUvodniUkoly()) {
                bzucak.napovedaKPostupu("Teď můžeme do centrálního mozku.");
            }
        }
    }

    public void ziskejIDKartu() {
        Inventar inventar = hrac.getInventar();
        if (inventar.obsahuje("ID karta")) {
            return;
        }
        if (!inventar.pridejPredmet(new IDKarta())) {
            Predmet vyhozeny = null;
            for (Predmet predmet : inventar.getPredmety()) {
                if (!"Prach".equalsIgnoreCase(predmet.getNazev())) {
                    vyhozeny = inventar.odeberPredmetBezHlaseni(predmet.getNazev());
                    break;
                }
            }
            if (vyhozeny == null) {
                vyhozeny = inventar.odeberPosledniPredmet();
            }
            if (vyhozeny != null) {
                hrac.getAktualniLokace().pridejPredmet(vyhozeny);
                System.out.println("Nemáš místo, ID karta ti vypadla na zem.");
            }
            inventar.pridejPredmet(new IDKarta());
        }
    }

    public void zahajHackovani() {
        hackovaniAktivni = true;
        integritaSystemu = 3;
        if (hektor != null) {
            hektor.resetZivoty();
            hektor.zahajHackovani();
        }
    }

    public void provedUtok() {
        if (!hackovaniAktivni) {
            System.out.println("Teď není co hackovat.");
            return;
        }
        if (hektor == null) {
            System.out.println("Systém nereaguje.");
            return;
        }
        hektor.utok();
        integritaSystemu--;
        System.out.println("Tvá integrita systému: " + integritaSystemu);
        zkontrolujHack();
    }

    public void provedObranu() {
        if (!hackovaniAktivni) {
            System.out.println("Teď není co hackovat.");
            return;
        }
        if (hektor == null) {
            System.out.println("Systém nereaguje.");
            return;
        }
        System.out.println("Spouštíš obranný firewall. Útok odražen.");
        zkontrolujHack();
    }

    private void zkontrolujHack() {
        if (hektor != null && hektor.jePorazen()) {
            hackovaniAktivni = false;
            hektorPorazen = true;
            System.out.println("Hektor je poražen. Bezpečnostní systém se restartuje.");
            splnUkol("Hektor");
            return;
        }
        if (integritaSystemu <= 0) {
            hackovaniAktivni = false;
            System.out.println("Hektor tě odpojil. Pokus se znovu.");
        }
    }

    public boolean jeHektorPorazen() {
        return hektorPorazen;
    }

    public void oznamEvakuacniProtokol() {
        splnUkol("Evakuace");
        bzucak.napovedaKPostupu("Výtah je otevřený. Stačí do něj vstoupit.");
    }

    public void vypisNapovedu() {
        if (!spravceUkolu.jeUkolSplnen("Baterie")) {
            bzucak.napovedaKPostupu("Baterie by měly být v kanceláři správce.");
            return;
        }
        if (!spravceUkolu.jeUkolSplnen("Kabely")) {
            bzucak.napovedaKPostupu("Zkus promluvit s terminálem X100 v serverovně.");
            return;
        }
        if (!spravceUkolu.jeUkolSplnen("ID karta")) {
            bzucak.napovedaKPostupu("V centrálním mozku budeš muset vyřešit hádanku.");
            return;
        }
        if (!spravceUkolu.jeUkolSplnen("Hektor")) {
            bzucak.napovedaKPostupu("Mluv s Hektorem a spusť hackování.");
            return;
        }
        if (!spravceUkolu.jeUkolSplnen("Evakuace")) {
            bzucak.napovedaKPostupu("Použij ID kartu u výtahové šachty v centrální chodbě.");
            return;
        }
        bzucak.napovedaKPostupu("Výtah je volný. Útěk je blízko.");
    }
}
