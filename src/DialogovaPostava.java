public abstract class DialogovaPostava extends Postava {
    private boolean dialogAktivni;

    protected DialogovaPostava(String jmeno) {
            super(jmeno);
        }

        public void zahajDialog(Hra hra){
            dialogAktivni = true;
            vypisUvod(hra);
        }

            public void odpovez(int volba,Hra hra){
                if (!dialogAktivni) {
                    System.out.println(getJmeno() + ": Nejdřív se mnou začni dialog.");
                    return;
                }
                zpracujOdpoved(volba,hra);
            }

                public void ukoncitDialog(){
                    dialogAktivni = false;
                    System.out.println(getJmeno() + ": Dialog ukončen.");
                }
    public boolean jeDialogAktivni() {
        return dialogAktivni;
    }

    protected abstract void vypisUvod(Hra hra);

    protected abstract void zpracujOdpoved(int volba, Hra hra);
}
