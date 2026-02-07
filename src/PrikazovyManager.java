public class PrikazovyManager {
    private final java.util.Map<String, Prikaz> prikazy = new java.util.HashMap<>();

    public void registrujPrikaz(Prikaz prikaz){
        if (prikaz == null) {
            return;
        }
        prikazy.put(prikaz.getNazev(), prikaz);
    }
    public void vykonejPrikaz(String vstup){
        if (vstup == null || vstup.isBlank()) {
            System.out.println("Zadej příkaz.");
            return;
        }
        String[] casti = vstup.trim().split(" ");
        String nazev = casti[0].toLowerCase();
        Prikaz prikaz = prikazy.get(nazev);
        if (prikaz == null) {
            System.out.println("Neznámý příkaz. Napiš napoveda");
            return;
        }
        prikaz.proved(casti);
    }
    public void vypisNapovedu(){
        System.out.println("Dostupné příkazy:");
        for (Prikaz prikaz : prikazy.values()) {
            System.out.println("- " + prikaz.getNazev() + ": " + prikaz.getPopis());
        }
    }
}
