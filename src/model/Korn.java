package model;

import java.io.Serializable;

public class Korn implements Serializable {
    private final String sort;
    private final String bondemand;
    private final int år;
    private final String mark;
    private final String maltningsprocess;

    public Korn(String sort, String bondemand, int år, String mark, String maltningsprocess) {
        this.sort = sort;
        this.bondemand = bondemand;
        this.år = år;
        this.mark = mark;
        this.maltningsprocess = maltningsprocess;
    }

    // getter metoder - alle er nødvendlige, da oplysningerne skal bruges i historiefortællingen
    public String getSort() {
        return sort;
    }

    public String getBondemand() {
        return bondemand;
    }

    public String getMark() {
        return mark;
    }

    public String getMaltningsprocess() {
        return maltningsprocess;
    }

    public int getÅr() {
        return år;
    }

    @Override
    public String toString() {
        String result = "Korn sort " + sort + ", dyrket af " + bondemand + " i " + år + " på marken "
                + mark + ". Information om maltningsprocessen: " + maltningsprocess;

        return result;
    }

}
