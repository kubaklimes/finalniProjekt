public class X100 extends DialogovaPostava {
    public X100() {
        super("X100");
    }

    public void opravKabely(){
        System.out.println("X100: Kabely byly opraveny.");
    }

    public void mluv() {
        System.out.println("X100: Potřebuji opravit kabely.");
    }


    protected void vypisUvod(Hra hra) {
        System.out.println("X100: Diagnostika hlásí přerušené kabely.");
        System.out.println("Pro opravu musíš zvolit správnou sekvenci kabelů.");
        System.out.println("Modrý=2, Červený=4, Zelený=1, Žlutý=3.");
        System.out.println("Pořadí pro restart: Zelený -> Modrý -> Žlutý -> Červený.");
        System.out.println("1) 1-2-3-4");
        System.out.println("2) 1-2-4-3");
        System.out.println("3) 2-1-3-4");
    }


    protected void zpracujOdpoved(int volba, Hra hra) {
        if (volba == 1) {
            opravKabely();
            hra.splnUkol("Kabely");
            hra.ukonciDialog();
            return;
        }
        System.out.println("X100: Sekvence nesouhlasí. Zkus to znovu.");
    }
}