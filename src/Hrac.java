public class Hrac {
    /**
     * Hlavní definice třídy Hrac.
     *
     * @author Jakub Klimeš
     */
    private Lokace aktualniLokace;
    private Inventar inventar;

    /**
     * Vytvoří instanci třídy Hrac.
     */
    public Hrac(Lokace start) {
        this.aktualniLokace = start;
        this.inventar = new Inventar(2);
    }
    /**
     * Vypíše aktuální polohu hráče.
     */
    public void vypisPolohu() {
        System.out.println("Nacházíš se v: " + aktualniLokace.getNazev());
        aktualniLokace.vypisVychody();
    }

    public Lokace getAktualniLokace() {
        return aktualniLokace;
    }

    public void presunSe(Lokace novaLokace) {
        this.aktualniLokace = novaLokace;
    }

    public Inventar getInventar() {
        return inventar;
    }
}
