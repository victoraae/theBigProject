package storage;

import controller.Storage;
import model.*;

import java.util.ArrayList;
import java.util.List;

public class ListStorage implements Storage {
    private final List<Lager> lagre = new ArrayList<>();
    private final List<Destillat> destillater = new ArrayList<>();
    private final List<Fad> fade = new ArrayList<>();
    private final List<Korn> korn = new ArrayList<>();

    public Lager sletLager(Lager lager){
        lagre.remove(lager);
        return lager;
    }

    @Override
    public void tilføjDestillat(Destillat destillat) {
        destillater.add(destillat);
    }

    @Override
    public List<Destillat> getDestillater() {
        return new ArrayList<>(destillater);
    }

    @Override
    public void tilføjFad(Fad fad) {
        fade.add(fad);
    }

    @Override
    public List<Fad> getFade() {
        return new ArrayList<>(fade);
    }

    @Override
    public void tilføjKorn(Korn korn) {
        this.korn.add(korn);
    }
    @Override
    public List<Korn> getKorn() {
        return new ArrayList<>(korn);
    }

    public void tilføjLager(Lager lager){
        lagre.add(lager);
    }

    public List<Lager> getLagre() {
        return new ArrayList<>(lagre);
    }
}
