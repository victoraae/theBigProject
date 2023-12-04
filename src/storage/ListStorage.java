package storage;

import controller.Storage;
import model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ListStorage implements Storage, Serializable {
    private final List<Lager> lagre = new ArrayList<>();
    private final List<Destillat> destillater = new ArrayList<>();
    private final List<Fad> fade = new ArrayList<>();
    private final List<Korn> korn = new ArrayList<>();
    private final List<NewMake> newMakes = new ArrayList<>();

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

    public void tilføjNewMake(NewMake newMake){
        this.newMakes.add(newMake);
    }
    public List<NewMake> getNewMakes(){
        return new ArrayList<>(newMakes);
    }


    // -------------------------------------------------------------------------
    public static ListStorage læsStorage(){
        String fileName = "src/storage.ser";
        try (FileInputStream fileIn = new FileInputStream(fileName);
             ObjectInputStream objIn = new ObjectInputStream(fileIn)
        ) {
            Object obj = objIn.readObject();
            ListStorage storage = (ListStorage) obj;
            System.out.println("Storage læst fra fil " + fileName);
            return storage;
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Der opstod en fejl under deserialisering af Storage");
            System.out.println(ex);
            return null;
        }
    }

    public static void gemStorage(Storage storage) {
        String fileName = "src/storage.ser";
        try (FileOutputStream fileOut = new FileOutputStream(fileName);
             ObjectOutputStream objOut = new ObjectOutputStream(fileOut)
        ) {
            objOut.writeObject(storage);
            System.out.println("Storage gemt i filen " + fileName);
        } catch (IOException ex) {
            System.out.println("Der opstod en fejl under deserialisering af Storage");
            System.out.println(ex);
            throw new RuntimeException();
        }
    }
}
