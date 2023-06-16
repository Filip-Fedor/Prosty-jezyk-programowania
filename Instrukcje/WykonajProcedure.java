package Instrukcje;

import Debugger.Debugger;
import Srodowisko.Srodowisko;
import Wyrazenia.Wyrazenie;

import java.util.List;

public class WykonajProcedure extends Instrukcja {


    private String nazwaProcedury;

    private List<Wyrazenie> wyrazenia;

    private WykonajProcedure(String nazwaProcedury, List<Wyrazenie> wyrazenia) {
        this.nazwaProcedury = nazwaProcedury;
        this.wyrazenia = wyrazenia;
    }

    @Override
    public void wykonajInstrukcje(Debugger debugger, Srodowisko srodowisko) {

    }

    @Override
    public void wypiszInstrukcje() {
        System.out.println("Wykonanie procedury" + nazwaProcedury);
    }
}
