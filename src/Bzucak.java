public class Bzucak extends Postava {
    public Bzucak() {
            super("Bzučák");
        }

        public void komentarKLokaci(Lokace lokace){
            String nazev = lokace.getNazev();
            String komentar;
            switch (nazev) {
                case "Úklidová místnost":
                    komentar = "Tvoje postel byla nejspíš police s košťaty.";
                    break;
                case "Centrální chodba":
                    komentar = "Chodba, která tě prověří. Zkus se nezamotávat.";
                    break;
                case "Skladiště":
                    komentar = "Tady by se i šrot cítil doma.";
                    break;
                case "Kancelář správce":
                    komentar = "Kafe je studené, ale aspoň ti nic nesežere formulář.";
                    break;
                case "Serverovna":
                    komentar = "Hučí to víc než moje vrtule. A to je co říct.";
                    break;
                case "Centrální mozek":
                    komentar = "Velký mozek, malé soukromí. Hlavně nic nepokazit.";
                    break;
                case "Výtahová šachta":
                    komentar = "Tady končí hra a začíná šance na výtah.";
                    break;
                default:
                    komentar = "Neznámé místo, neznámé problémy.";
                    break;
            }
            System.out.println("Bzučák: " + komentar);
        }
        public void napoveda(){
            System.out.println("Bzučák: Zkus se rozhlédnout a mluvit s postavami.");
        }


    public void napovedaKPostupu(String text){
        System.out.println("Bzučák: " + text);
    }


    public void mluv() {
            System.out.println("Bzučák: Vždycky je nějaká cesta dál.");
        }
    }