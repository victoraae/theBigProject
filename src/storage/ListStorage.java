package storage;

import model.*;

import java.util.ArrayList;

public class ListStorage {
    private final ArrayList<Lager> lagre = new ArrayList<>();

    public Lager sletLager(Lager lager){
        lagre.remove(lager);
        return lager;
    }
    public void tilføjLager(Lager lager){
        lagre.add(lager);
    }

    public ArrayList<Lager> getLagre() {
        return new ArrayList<>(lagre);
    }
}
