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

    @Override
    protected void vypisUvod(Hra hra) {
        System.out.println("X100: Diagnostika hlásí přerušené kabely.");
        System.out.println("1) Opravit kabely.");
        System.out.println("2) Teď nemám čas.");
    }

    @Override
    protected void zpracujOdpoved(int volba, Hra hra) {
        if (volba == 1) {
            opravKabely();
            hra.splnUkol("Kabely");
            hra.ukonciDialog();
            return;
        }
        System.out.println("X100: Dobrá, ozvi se až budeš připraven.");
    }
}
