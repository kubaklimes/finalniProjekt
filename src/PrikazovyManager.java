public class PrikazovyManager {
    /**
     * Hlavní definice třídy PrikazovyManager.
     *
     * @author Jakub Klimeš,stackoverflow
     */
    private final java.util.Map<String, Prikaz> prikazy = new java.util.HashMap<>();
    /**
     * Registruje zadanej příkaz.
     */
    public void registrujPrikaz(Prikaz prikaz){
        if (prikaz == null) {
            return;
        }
        prikazy.put(prikaz.getNazev(), prikaz);
    }
    /**
     * Vykoná zadanej příkaz, a rozdělí ho na 2 části.
     */
    public void vykonejPrikaz(String vstup){
        if (vstup == null || vstup.isBlank()) {
            System.out.println("Zadej příkaz.");
            return;
        }
        String[] casti = vstup.trim().split("\\s+");
        String nazev = casti[0].toLowerCase();
        Prikaz prikaz = prikazy.get(nazev);
        if (prikaz == null) {
            System.out.println("Neznámý příkaz. Napiš napoveda");
            return;
        }
        prikaz.proved(casti);
    }
    /**
     * Vypíše nápovědu všech dostupných příkazů.
     */
    public void vypisNapovedu(){
        System.out.println("Dostupné příkazy:");
        for (Prikaz prikaz : prikazy.values()) {
            System.out.println("- " + prikaz.getNazev() + ": " + prikaz.getPopis());
        }
    }
}