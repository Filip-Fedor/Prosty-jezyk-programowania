package Srodowisko;

import Debugger.Debugger;
import Wyrazenia.Zmienna;

import java.util.ArrayList;
import java.util.List;

public class Srodowisko {

    private Debugger debugger;

    private List<List<Zmienna>> listaListZmiennych;

    public Srodowisko() {
        listaListZmiennych = new ArrayList<>();
    }

    public void ustawDebugger(int obecnyKrok, int ileKrokowWykonac, int poziomZagniezdzenia) {
        debugger = new Debugger(obecnyKrok,ileKrokowWykonac,poziomZagniezdzenia);
    }

    public Debugger getDebugger() {
        return debugger;
    }

    public List<List<Zmienna>> dajListeListZmiennych() {
        return listaListZmiennych;
    }
    public void dodajListeZmiennych(List<Zmienna> zmienne) {
        listaListZmiennych.add(zmienne);
    }

    public List<Zmienna> dajOstatniaListe() {
        int size = listaListZmiennych.size();
        if (size == 0) {
            return new ArrayList<>();
        }
        return listaListZmiennych.get(size-1);
    }

    public void usunOstatniaListe() {
        listaListZmiennych.remove(listaListZmiennych.size()-1);
    }

    public int dajRozmiarListy() {
        return listaListZmiennych.size();
    }

    public List<Zmienna> dajListePodIndeksem(int i) {
        return listaListZmiennych.get(i);
    }
}
