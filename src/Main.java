public class Main {
    public static void main(String args[]) {
        Loader loader = new Loader();
        PCB pcb = loader.load("zad0");
        pcb.printPCB();
    }
}
