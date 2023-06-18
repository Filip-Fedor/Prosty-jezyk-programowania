package Instrukcje;

import Debugger.Debugger;
import Srodowisko.Srodowisko;
import Wyrazenia.Zmienna;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.exit;

public class DeklaracjaProcedury extends Instrukcja {

    private String nazwaProcedury;

    private List<String> listaParametrow;

    private Blok blok;


    public DeklaracjaProcedury(String nazwaProcedury, List<String> listaParametrow) {
        this.nazwaProcedury = nazwaProcedury;
        this.listaParametrow = listaParametrow;
        this.blok = new Blok();
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
        blok.wykonajInstrukcje(debugger, srodowisko);
    }

    public List<Zmienna> dajZmienneZParametrow() {
        List<Zmienna> zmienneParametry = new ArrayList<>();
        for (String s : listaParametrow) {
            Zmienna z = new Zmienna(s);
            zmienneParametry.add(z);
        }
        return zmienneParametry;
    }



    public String toString() {
        return nazwaProcedury;
    }

    public List<String> dajListaParametrow() {
        return listaParametrow;
    }

    public Blok dajBlok() {
        return blok;
    }
}
