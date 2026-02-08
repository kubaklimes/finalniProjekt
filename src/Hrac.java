public class Hrac {

    private Lokace aktualniLokace;
    private Inventar inventar;

    public Hrac(Lokace start) {
        this.aktualniLokace = start;
        this.inventar = new Inventar(2);
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
