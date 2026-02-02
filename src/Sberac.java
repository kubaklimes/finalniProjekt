public class Sberac extends DialogovaPostava {
    public Sberac() {
        super("Sběrač");
    }

    public void prijmiBaterie(){
        System.out.println("Sběrač: Díky za baterie.");
    }

    public void mluv() {
        System.out.println("Sběrač: Hledám baterie do sběrače.");
    }
}
