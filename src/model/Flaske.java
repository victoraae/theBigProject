package model;

import java.util.ArrayList;

public class Flaske {
    private final int nummer;
    private final Whisky whisky;
    private int antalFlasker;
    public Flaske(int nummer, Whisky whisky, int antalFlasker) {
        this.nummer = nummer;
        this.whisky = whisky;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        int år = whisky.getÅr();
        String kval = whisky.getKvalitetsstempel();

        sb.append(whisky.getNavn() + ", flaske nr: " + nummer + "ud af " + antalFlasker + "\n");
        sb.append("Denne " + kval + "  whisky har lagret i " + år +  " år ");

        return sb.toString();
    }
}
