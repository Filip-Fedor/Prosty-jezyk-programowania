package Instrukcje;

import Debugger.Debugger;
import Srodowisko.Srodowisko;
import Wyrazenia.Wyrazenie;
import Wyrazenia.Zmienna;

import java.util.ArrayList;
import java.util.List;

public class WykonajProcedure extends Instrukcja {


    private String nazwaProcedury;

    private List<Wyrazenie> wyrazenia;

    public WykonajProcedure(String nazwaProcedury, List<Wyrazenie> wyrazenia) {
        this.nazwaProcedury = nazwaProcedury;
        this.wyrazenia = wyrazenia;
    }



    @Override
    public void wykonajInstrukcje(Debugger debugger, Srodowisko srodowisko) {
        DeklaracjaProcedury deklaracjaProcedury = srodowisko.dajDeklaracjeProcedury(nazwaProcedury);
        List<Zmienna> zmienneSwoje = deklaracjaProcedury.dajZmienneZParametrow();
        przypiszWartoscZmiennym(zmienneSwoje, srodowisko);
        dodajDoSwojejListyZmienne(srodowisko, zmienneSwoje);


        srodowisko.dodajListeZmiennych(zmienneSwoje);

        for (Instrukcja i : deklaracjaProcedury.dajInstrukcje()) {
            debugger.debugger(i, srodowisko);
            i.wykonajInstrukcje(debugger, srodowisko);
        }

        srodowisko.usunOstatniaListeZmiennych();
    }

    public void dodajDoSwojejListyZmienne(Srodowisko srodowisko, List<Zmienna> zmienneSwoje) {
        for (Zmienna zmiennaOjca : srodowisko.dajOstatniaListe()) {
            int stop = 0;
            for (Zmienna zmiennaSwoja : zmienneSwoje) {
                if (zmiennaOjca.getNazwa().equals(zmiennaSwoja.getNazwa())) {
                    stop = 1;
                    break;
                }
            }
            if (stop == 0) {
                zmienneSwoje.add(zmiennaOjca);
            }
        }
    }

    public void przypiszWartoscZmiennym(List<Zmienna> zmienne, Srodowisko srodowisko) {
        int k = 0;
        for (Zmienna z : zmienne) {
            z.setWartosc(wyrazenia.get(0).oblicz(srodowisko.dajOstatniaListe()));
            k++;
        }
    }

    @Override
    public void wypiszInstrukcje() {
        System.out.println("Wykonanie procedury" + nazwaProcedury);
    }

    public void wypiszZmienne(List<Zmienna> zmienne) {
        for (Zmienna z : zmienne) {
            System.out.println("Nazwa " + z.getNazwa() + "| Wartosc " + z.oblicz(zmienne));
        }
    }
}
