public abstract class Postava {
    private final String jmeno;

    protected Postava(String jmeno) {
        this.jmeno = jmeno;
    }
    public String getJmeno(){
        return jmeno;
    }

    public void mluv(){
        System.out.println(jmeno + ": ...");
    }
}
