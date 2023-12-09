package model;

import java.util.Comparator;

// sammenligner New Makes baseret på dato
public class NewMakeCompare implements Comparator<NewMake> {
    public int compare(NewMake newMake1, NewMake newMake2){
        return newMake1.getDatoForPåfyldning().compareTo(newMake2.getDatoForPåfyldning());
    }
}
