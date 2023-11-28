package controller;

import model.Lager;

import java.util.List;

public interface Storage {
    public List<Lager> getLagre();
    public void tilføjLager(Lager lager);
    public Lager sletLager(Lager lager);
}
