package model;

public class Korn {
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

    public int getÅr() {
        return år;
    }

    public String getMark() {
        return mark;
    }

    public String getMaltningsprocess() {
        return maltningsprocess;
    }
}
