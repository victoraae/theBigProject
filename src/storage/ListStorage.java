package storage;

import controller.Storage;
import model.*;

import java.util.ArrayList;
import java.util.List;

public class ListStorage implements Storage {
    private final ArrayList<Lager> lagre = new ArrayList<>();

    public Lager sletLager(Lager lager){
        lagre.remove(lager);
        return lager;
    }
    public void tilføjLager(Lager lager){
        lagre.add(lager);
        System.out.println("TEST");
    }

    public List<Lager> getLagre() {
        return new ArrayList<>(lagre);
    }
}
