package model;

public class Hylde {
    private int nummer;
    private boolean[] pladserArray;
    private boolean erLedig;
    private int størrelse;

    public Hylde(int nummer, int størrelse) {
        this.nummer = nummer;
        this.størrelse = størrelse;
        pladserArray  = new boolean[størrelse];
    }
}
