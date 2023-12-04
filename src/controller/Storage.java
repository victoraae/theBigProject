package controller;

import model.*;

import java.util.List;

public interface Storage {
    public List<Lager> getLagre();
    public void tilføjLager(Lager lager);

    /**
     * @return null hvis lager ikke findes
     */
    public Lager sletLager(Lager lager);
    public void tilføjDestillat(Destillat destillat);
    public List<Destillat> getDestillater();
    public void tilføjFad(Fad fad);
    public List<Fad> getFade();
    public void tilføjKorn(Korn korn);
    public List<Korn> getKorn();
    public void tilføjNewMake(NewMake newMake);
    public List<NewMake> getNewMakes();
}
