public class IDKarta extends Predmet {
    public IDKarta() {
        super("ID karta", "Autorizovaná karta pro přístup k evakuačnímu protokolu.", true);
    }

    @Override
    public void pouzij(Hra hra) {
        if (hra == null) {
            return;
        }
        Lokace lokace = hra.getHrac().getAktualniLokace();
        if (!hra.jeHektorPorazen()) {
            System.out.println("Terminál odmítá kartu. Nejdřív musíš vyřešit Hektora.");
            return;
        }
        if ("Centrální chodba".equals(lokace.getNazev())) {
            Lokace vytah = hra.getSvet().getLokace("Výtahová šachta");
            if (vytah != null && vytah.jeZamcena()) {
                vytah.odemkni();
                System.out.println("Karta odemkla masivní vrata výtahu.");
                hra.oznamEvakuacniProtokol();
                return;
            }
            System.out.println("Vrata jsou už odemčená.");
            return;
        }
        System.out.println("Tady karta nic neudělá.");
    }
}
