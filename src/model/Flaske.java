package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Flaske {
    private final int nummer;
    private final Whisky whisky;
    private int antalFlasker;
    public Flaske(int nummer, Whisky whisky, int antalFlasker) {
        this.nummer = nummer;
        this.whisky = whisky;
        this.antalFlasker = antalFlasker;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        int år = whisky.getÅr();
        String kval = whisky.getKvalitetsstempel();

        sb.append(whisky.getNavn() + ", flaske nr: " + nummer + " ud af " + antalFlasker + ". \n");
        sb.append("Denne " + kval + "  whisky har lagret i " + år + " år. Alc. %: " + Math.floor(whisky.getAlkoholProcent()) + ". \n");

        Set<String> marker = new HashSet<>();
        Set<String> landmænd = new HashSet<>();
        Set<String> kornsorter = new HashSet<>();
        Set<String> rygemateriale = new HashSet<>();
        Set<String> maltningsProcessor = new HashSet<>();
        Set<String> fadTræSort = new HashSet<>();
        Set<String> fadOprindLand = new HashSet<>();
        Set<String> fadTidlIndhold = new HashSet<>();
        Set<Integer> årHøst = new HashSet<>();

        List<NewMake> alleNewMakes = whisky.getAlleNewMakesRekursiv(whisky.getNewMakes());

        List<FadTilNM> alleFade = new ArrayList<>(getAlleFadeFraNMS(alleNewMakes));
        List<Destillat> alleDestillater = new ArrayList<>(getAlleDestillaterFraNMS(alleNewMakes));
        List<Korn> alleKorn = new ArrayList<>(getAlleKornFraDestillater(alleDestillater));

        for(FadTilNM ftnm : alleFade){
            fadTræSort.add(ftnm.getFad().getMateriale());
            fadOprindLand.add(ftnm.getFad().getOprindeslesland());
            fadTidlIndhold.add(ftnm.getFad().getTidligereIndhold());
        }

        for(Destillat d : alleDestillater){
            rygemateriale.add(d.getRygemateriale());
        }

        for(Korn k : alleKorn){
            maltningsProcessor.add(k.getSort());
            kornsorter.add(k.getSort());
            marker.add(k.getMark());
            landmænd.add(k.getBondemand());
            årHøst.add(k.getÅr());
        }

        sb.append("Denne whisky består af kornsorterne: " + setTilString(kornsorter) + ". \n");
        sb.append("Kornet er høstet fra markerne: " + setTilString(marker));
        sb.append(", af landmænd: " + setTilString(landmænd) + ". I årene: " + setTilString(årHøst) + ". \n");
        sb.append("Kornet har været igennem maltningsprocesserne: " + setTilString(maltningsProcessor) + ". \n");
        if(rygemateriale.size()!=0)sb.append("Kornet er røget med: " + setTilString(rygemateriale)+ ". \n");
        sb.append("Whiskyen har lagret på fade af: " + setTilString(fadTræSort) + ". \n");
        sb.append("Fadene er fra: " + setTilString(fadOprindLand) + ". \n");
        sb.append("Fadene har tidligere indeholdt: " + setTilString(fadTidlIndhold) + ". \n");

        return sb.toString();
    }


    private Set<Destillat> getAlleDestillaterFraNMS(List<NewMake> newMakes){
        Set<Destillat> result = new HashSet<>();
        for(NewMake nm : newMakes){
            if(nm.getMængder().size()!=0){
                for(Mængde m : nm.getMængder()){
                    result.add(m.getDestillat());
                }
            }
        }
        return result;
    }

    private Set<FadTilNM> getAlleFadeFraNMS(List<NewMake> newMakes){
        Set<FadTilNM> result = new HashSet<>();
        for(NewMake nm : newMakes){
                for(FadTilNM ftnm : nm.getFad()){
                    result.add(ftnm);
                }
        }
        return result;
    }

    private Set<Korn> getAlleKornFraDestillater(List<Destillat> destillater){
        Set<Korn> result = new HashSet<>();
        for(Destillat d : destillater){
            result.add(d.getKorn());
        }
        return result;
    }

    private <T> String  setTilString(Set<T> set){
        String result = set.toString();
        result = result.substring(1, result.length()-1);
        return result;
    }

}
