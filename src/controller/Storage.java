package controller;

import model.Lager;

public interface Storage {
    public void tilføjLager(Lager lager);
    public Lager sletLager(Lager lager);
}
