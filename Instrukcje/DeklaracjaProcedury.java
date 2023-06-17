package Instrukcje;

import Debugger.Debugger;
import Srodowisko.Srodowisko;
import Wyrazenia.Zmienna;

import java.util.ArrayList;
import java.util.List;

public class DeklaracjaProcedury extends Instrukcja {

    private String nazwaProcedury;

    private List<String> listaParametrow;

    private List<Instrukcja> instrukcje;


    public DeklaracjaProcedury(String nazwaProcedury, List<String> listaParametrow) {
        this.nazwaProcedury = nazwaProcedury;
        this.listaParametrow = listaParametrow;
        this.instrukcje = new ArrayList<>();
    }

    public String dajNazwaProcedury() {
        return nazwaProcedury;
    }

    @Override
    public void wypiszInstrukcje() {
        System.out.println("Deklaracja procedury: " + nazwaProcedury);
    }

    @Override
    public void wykonajInstrukcje(Debugger debugger, Srodowisko srodowisko) {

    }

    public List<Zmienna> dajZmienneZParametrow() {
        List<Zmienna> zmienneParametry = new ArrayList<>();
        for (String s : listaParametrow) {
            Zmienna z = new Zmienna(s);
            zmienneParametry.add(z);
        }
        return zmienneParametry;
    }


    public List<Instrukcja> dajInstrukcje() {
        return instrukcje;
    }

    public void dodajInstrukcje(Instrukcja i) {
        instrukcje.add(i);
    }

    public String toString() {
        return nazwaProcedury;
    }
}
