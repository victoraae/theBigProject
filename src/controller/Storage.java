package controller;

import model.Lager;

public interface Storage {
    public void tilføjLager(Lager lager);
    public void sletLager(Lager lager);
}
