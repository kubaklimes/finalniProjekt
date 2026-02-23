public interface Prikaz {
    /**
     * Hlavní definice třídy Prikaz.
     *
     * @author já
     */


    /**
     * Provede příkaz s předanými parametry.
     */
    void proved(String[] parametry);


    /**
     * Vrátí textový název příkazu.
     */
    String getNazev();


    /**
     * Vrátí popis příkazu pro nápovědu.
     */
    String getPopis();
}
