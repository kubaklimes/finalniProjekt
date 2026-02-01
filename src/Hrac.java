public class Hrac {

    private Lokace aktualniLokace;
    private Inventar inventar;

    public Hrac(Lokace start) {
        this.aktualniLokace = start;
        this.inventar = new Inventar(5); // kapacita inventáře
    }

    public void jdi(String smer) {
        Lokace nova = aktualniLokace.getVychod(smer);

        if (nova != null) {
            presunSe(nova);
            System.out.println("Přesunul ses do: " + aktualniLokace.getNazev());
            System.out.println(aktualniLokace.getPopis());
        } else {
            System.out.println("Tímto směrem se jít nedá.");
        }
    }

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
