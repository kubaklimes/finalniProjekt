public abstract class Predmet {
    /**
     * Hlavní definice třídy Predmet.
     *
     * @author já
     */
    private final String nazev;
    private final String popis;
    private final boolean prenosny;

    /**
     * Vytvoří instanci třídy Predmet.
     */
    public Predmet(String nazev, String popis, boolean prenosny) {
        this.nazev = nazev;
        this.popis = popis;
        this.prenosny = prenosny;
    }


    public String getNazev(){
        return nazev;
    }
    public String getPopis(){
        return popis;
    }

    public boolean jePrenosny(){
        return prenosny;
    }

    /**
     * Nepoužije předmět.
     */
    public void pouzij(Hra hra){
        System.out.println("Předmět \"" + nazev + "\" nemá žádné speciální použití.");
    }
}
