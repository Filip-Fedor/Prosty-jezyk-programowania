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

    private List<Zmienna> zmienneParametry;

    public DeklaracjaProcedury(String nazwaProcedury, List<String> listaParametrow) {
        this.nazwaProcedury = nazwaProcedury;
        this.listaParametrow = listaParametrow;
    }


    @Override
    public void wypiszInstrukcje() {
        System.out.println("Deklaracja procedury: " + nazwaProcedury);
    }

    @Override
    public void wykonajInstrukcje(Debugger debugger, Srodowisko srodowisko) {
        stworzZmienneZParametrow();
    }

    public void stworzZmienneZParametrow() {
        List<Zmienna> zmienneParametry = new ArrayList<>();
        for (String s : listaParametrow) {
            Zmienna z = new Zmienna(s);
            zmienneParametry.add(z);
        }
        this.zmienneParametry = zmienneParametry;
    }
}
