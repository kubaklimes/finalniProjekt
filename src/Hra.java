import java.util.Random;
import java.util.Scanner;

public class Hra {
    /**
     * Hlavní definice třídy Hra.
     *
     * @author Jakub Klimeš,stackoverflow,chatgpt
     */

    private Hrac hrac;
    private HerniSvet svet;
    private PrikazovyManager prikazovyManager;
    private SpravceUkolu spravceUkolu;
    private Bzucak bzucak;
    private DialogovaPostava aktivniDialog;
    private Hektor hektor;
    private boolean hackovaniAktivni;
    private int integritaSystemu;
    private boolean opustilChodbu;
    private boolean prachZiskan;
    private boolean hektorPorazen;
    private final Random random = new Random();
    private boolean konec = false;
    /**
     * Hlavní game loop,opakující se nekonečně krát, dokud se neukončí hra.
     */
    public void start() {
        svet = new HerniSvet();
        svet.nactiZeSouboru("svet.json");

        if (svet.getStartovniLokace() == null) {
            System.out.println("Hru nelze spustit bez platně načteného světa.");
            return;
        }

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
    /**
     * Vypíše aktuální lokaci ve které se uživatel nachází.
     */
    private void vypisAktualniLokaci() {
        if (hrac == null || hrac.getAktualniLokace() == null) {
            System.out.println("Aktuální lokaci se nepodařilo načíst.");
            return;
        }
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
    /**
     * Přídání příkazů do příkazového manageru.
     */
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
        prikazovyManager.registrujPrikaz(new PrikazNapoveda(prikazovyManager));
        prikazovyManager.registrujPrikaz(new PrikazRada(this));
    }
    /**
     * Načte svět, a přídá úkoly potřebné pro výhru.
     */
    private void pripravSvet() {
        Lokace skladiste = svet.getLokace("Skladiště");
        Lokace kancelar = svet.getLokace("Kancelář správce");
        Lokace serverovna = svet.getLokace("Serverovna");
        Lokace mozek = svet.getLokace("Centrální mozek");
        Lokace vytah = svet.getLokace("Výtahová šachta");

        if (skladiste != null) {
            skladiste.pridejPostavu(new Sberac());
        }
        if (serverovna != null) {
            serverovna.pridejPostavu(new X100());
        }
        if (mozek != null) {
            mozek.pridejPostavu(new CentralniTerminal());
        }
        if (vytah != null) {
            hektor = new Hektor();
            vytah.pridejPostavu(hektor);
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
    /**
     * Pohne hráče, zkontroluje jestli se dá nebo nedá jít do místnosti, kontrola prachu také.
     */
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
        boolean odchaziZChodby = "Centrální chodba".equals(aktualni.getNazev());
        Lokace nova = aktualni.getVychod(smer);
        if (nova == null) {
            System.out.println("Tímto směrem se jít nedá.");
            return;
        }
        if (nova.jeZamcena()) {
            System.out.println("Dveře jsou zamčené. Potřebuješ přístup ID kartou.");
            return;
        }
        if ("Centrální mozek".equals(nova.getNazev()) && !maSplneneUvodniUkoly()) {
            System.out.println("Bez důvěry robotů tě terminál dál nepustí.");
            return;
        }
        hrac.presunSe(nova);
        if (!nova.jeNavstivena()) {
            nova.oznacNavstivenou();
        }
        vypisAktualniLokaci();
        bzucak.komentarKLokaci(nova);
        kontrolaPrachu(nova, odchaziZChodby);
        if ("Výtahová šachta".equals(nova.getNazev()) && !nova.jeZamcena()) {
            if (hektor != null && !hektorPorazen) {
                System.out.println("Hektor blokuje výtah. Připrav se na duel.");
                zahajHackovani();
                return;
            }
            System.out.println("Dveře výtahu se otevírají. Marek uniká na povrch!");
            konecHry();
        }
    }
    /**
     * Dodatečný příkaz vstup do, skoro to samé jako hýbání.
     */
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
    /**
     * Zajištění diakritiky.
     */
    private String normalizujNazevLokace(String nazev) {
        String lower = nazev.toLowerCase();
        switch (lower) {
            case "vytah":
            case "výtah":
            case "šachta":
            case "sachta":
            case "vytahova":
            case "výtahová":
                return "Výtahová šachta";
            case "mozko":
            case "mozek":
            case "centrální mozek":
            case "centralni mozek":
                return "Centrální mozek";
            case "skladiste":
            case "skladiště":
                return "Skladiště";
            case "serverovna":
                return "Serverovna";
            case "kancelar":
            case "kancelář":
            case "kancelar spravce":
            case "kancelář správce":
                return "Kancelář správce";
            case "chodba":
            case "centralni chodba":
            case "centrální chodba":
                return "Centrální chodba";
            case "uklidova mistnost":
            case "úklidová místnost":
                return "Úklidová místnost";
            default:
                return nazev;
        }
    }
    /**
     * Kontrola prachu.
     */
    private void kontrolaPrachu(Lokace nova, boolean odchaziZChodby) {
        if ("Centrální chodba".equals(nova.getNazev())) {
            if (!prachZiskan && opustilChodbu) {
                prachZiskan = true;
                pridejPrach(nova);
            }
            opustilChodbu = false;
            return;
        }
        if (odchaziZChodby) {
            opustilChodbu = true;
        } else {
            opustilChodbu = false;
        }
    }
    /**
     * Přidání prachu do inventáře, pokud kontrola prachu projde.
     */
    private void pridejPrach(Lokace nova) {
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
    /**
     * Zahájení dialogu s mluvící postavou.
     */
    public void zahajDialog(DialogovaPostava postava) {
        if (aktivniDialog != null) {
            aktivniDialog.ukoncitDialog();
        }
        aktivniDialog = postava;
        postava.zahajDialog(this);
    }
    /**
     * Odpovezení k dialogu.
     */
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
    /**
     * Kontrola zdali má splněné první dva potřebné úkoly k postoupení dál.
     */
    public boolean maSplneneUvodniUkoly() {
        return spravceUkolu != null
                && spravceUkolu.jeUkolSplnen("Baterie")
                && spravceUkolu.jeUkolSplnen("Kabely");
    }
    /**
     * Splnění úkolu.
     */
    public void splnUkol(String nazev) {
        spravceUkolu.splnitUkol(nazev);
        if ("Baterie".equals(nazev) || "Kabely".equals(nazev)) {
            if (maSplneneUvodniUkoly()) {
                bzucak.napovedaKPostupu("Teď můžeme do centrálního mozku.");
            }
        }
    }
    /**
     * Získání ID karty pokud mám místo v inventáři.
     */
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
    /**
     * Zahájení boss fightu s Hektorem.
     */
    public void zahajHackovani() {
        hackovaniAktivni = true;
        integritaSystemu = 6;
        if (hektor != null) {
            hektor.resetZivoty();
            hektor.zahajHackovani();
        }
    }
    /**
     * Provede útok.
     */
    public void provedUtok() {
        if (!hackovaniAktivni) {
            System.out.println("Teď není co hackovat.");
            return;
        }
        if (hektor == null) {
            System.out.println("Systém nereaguje.");
            return;
        }
        int zraneni = 1 + random.nextInt(2);
        if (hektor.prijmiUtok(zraneni)) {
            System.out.println("Tvůj útok zasáhl Hektora za " + zraneni + " body.");
        } else {
            System.out.println("Hektorův štít pohltil tvůj útok.");
        }
        hektorTah(false);
        zkontrolujHack();
    }
    /**
     * Provede obranu.
     */
    public void provedObranu() {
        if (!hackovaniAktivni) {
            System.out.println("Teď není co hackovat.");
            return;
        }
        if (hektor == null) {
            System.out.println("Systém nereaguje.");
            return;
        }
        System.out.println("Spouštíš obranný firewall. Snižuješ přicházející škody.");
        hektorTah(true);
        zkontrolujHack();
    }
    /**
     * Hektor zaútočí.
     */
    private void hektorTah(boolean obranaAktivni) {
        if (hektor == null || hektor.jePorazen()) {
            return;
        }
        if (hektor.jeNabity()) {
            int zraneni = hektor.utokNabity(random);
            zraneni = upravZraneniObranou(zraneni, obranaAktivni);
            integritaSystemu -= zraneni;
            System.out.println("Hektor spouští nabitý útok za " + zraneni + " bodů.");
            vypisIntegritu();
            return;
        }
        int volba = random.nextInt(100);
        if (volba < 25) {
            hektor.aktivujStit();
            System.out.println("Hektor aktivuje ochranný štít.");
            return;
        }
        if (volba < 50) {
            hektor.nabijSe();
            System.out.println("Hektor se nabíjí na silný útok.");
            return;
        }
        int zraneni = hektor.utokNormal(random);
        zraneni = upravZraneniObranou(zraneni, obranaAktivni);
        integritaSystemu -= zraneni;
        vypisIntegritu();
    }
    /**
     * Obrana uzivatele.
     */
    private int upravZraneniObranou(int zraneni, boolean obranaAktivni) {
        if (!obranaAktivni) {
            return zraneni;
        }
        int snizene = Math.max(0, zraneni - 1);
        if (snizene == 0) {
            System.out.println("Obrana odrazila celý útok.");
    }
        return snizene;
    }
    /**
     * Vypíše jak jsi na tom s HP.
     */
    private void vypisIntegritu() {
        System.out.println("Tvé HP: " + integritaSystemu);
    }
    /**
     * Zkontroluje jestli jsi porazil Hektora, pokud ano, vyhráls!
     */
    private void zkontrolujHack() {
        if (hektor != null && hektor.jePorazen()) {
            hackovaniAktivni = false;
            hektorPorazen = true;
            System.out.println("Hektor je poražen. Bezpečnostní systém se restartuje.");
            splnUkol("Hektor");
            System.out.println("Výhra! Utekl jsi na povrch živ a zdráv!");
            konecHry();
            return;
        }
        if (integritaSystemu <= 0) {
            hackovaniAktivni = false;
            System.out.println("Hektor tě odpojil. Pokus se znovu.");
        }
    }

    /**
     * Konečná finální zpráva od Bzučáka.
     */
    public void oznamEvakuacniProtokol() {
        splnUkol("Evakuace");
        bzucak.napovedaKPostupu("Výtah je otevřený. Stačí do něj vstoupit.");
    }
    /**
     * Vypíše nápovědu podle toho jaký úkol ti chybí, a následuje.
     */
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
            bzucak.napovedaKPostupu("V centrálním mozku na tebe čeká odměna.");
            return;
        }
        if (!spravceUkolu.jeUkolSplnen("Hektor")) {
            bzucak.napovedaKPostupu("Použij ID kartu u výtahové šachty v centrální chodbě.");
            return;
        }
        if (!spravceUkolu.jeUkolSplnen("Evakuace")) {
            bzucak.napovedaKPostupu("Poraž Hektora pro svobodu.");
            return;
        }
        bzucak.napovedaKPostupu("Výtah je volný. Útěk je blízko.");
    }
}