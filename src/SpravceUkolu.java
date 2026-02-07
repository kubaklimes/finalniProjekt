public class SpravceUkolu {
    private final java.util.Map<String, Ukol> ukoly = new java.util.HashMap<>();

    public void pridejUkol(Ukol ukol){
        if (ukol == null) {
            return;
        }
        ukoly.put(ukol.getNazev(), ukol);
    }
    public void splnitUkol(String nazev){
        Ukol ukol = ukoly.get(nazev);
        if (ukol == null) {
            System.out.println("Ukol \"" + nazev + "\" neexistuje.");
            return;
        }
        ukol.splnit();
        System.out.println("Ukol \"" + nazev + "\" byl splněn.");
    }
    public void vypisUkoly(){
        if (ukoly.isEmpty()) {
            System.out.println("Žádné úkoly nejsou aktivní.");
            return;
        }
        System.out.println("Aktivní úkoly:");
        for (Ukol ukol : ukoly.values()) {
            String stav = ukol.jeSplnen() ? "splněno" : "nesplněno";
            System.out.println("- " + ukol.getNazev() + " (" + stav + ")");
        }
    }
    public void detailUkolu(String nazev){
        Ukol ukol = ukoly.get(nazev);
        if (ukol == null) {
            System.out.println("Ukol \"" + nazev + "\" neexistuje.");
            return;
        }
        String stav = ukol.jeSplnen() ? "splněno" : "nesplněno";
        System.out.println(ukol.getNazev() + ": " + ukol.getPopis() + " (" + stav + ")");
    }

    public Ukol getUkol(String nazev) {
        return ukoly.get(nazev);
    }

    public boolean jeUkolSplnen(String nazev) {
        Ukol ukol = ukoly.get(nazev);
        return ukol != null && ukol.jeSplnen();
    }
}
