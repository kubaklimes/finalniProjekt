public abstract class DialogovaPostava extends Postava {
    private boolean dialogAktivni;

    protected DialogovaPostava(String jmeno) {
            super(jmeno);
        }

        public void zahajDialog(){
            dialogAktivni = true;
            System.out.println(getJmeno() + ": Začínáme dialog.");
        }

            public void odpovez(int volba){
                if (!dialogAktivni) {
                    System.out.println(getJmeno() + ": Nejdřív se mnou začni dialog.");
                    return;
                }
                System.out.println(getJmeno() + ": Zvolil jsi možnost " + volba + ".");
            }

                public void ukoncitDialog(){
                    dialogAktivni = false;
                    System.out.println(getJmeno() + ": Dialog ukončen.");
                }
            }