package Instrukcje;
import Debugger.Debugger;
import Srodowisko.Srodowisko;
import Wyrazenia.*;


import java.util.ArrayList;
import java.util.List;

import static java.lang.System.exit;


public class Blok extends Instrukcja {

    private List<DeklaracjaZmiennej> deklaracjaZmiennych;
    private List<Instrukcja> instrukcje;
    private List<DeklaracjaProcedury> deklaracjaProcedur;

    public Blok(List<DeklaracjaZmiennej> deklaracjaZmiennych, List<DeklaracjaProcedury> deklaracjaProcedur) {
        this.deklaracjaProcedur = deklaracjaProcedur;
        this.deklaracjaZmiennych = deklaracjaZmiennych;
        this.instrukcje = new ArrayList<>();
    }

    public void dodajInstrukcje(Instrukcja i) {
        instrukcje.add(i);
    }


    public List<Zmienna> deklarujZmienne(Debugger debugger, Srodowisko srodowisko) {
        List<Zmienna> noweZmienne = new ArrayList<>();
        try {
            for (DeklaracjaZmiennej deklaracjaZmiennej : deklaracjaZmiennych) {
                debugger.debugger(deklaracjaZmiennej, srodowisko);
                Zmienna z = new Zmienna(deklaracjaZmiennej.getNazwa());
                z.setWartosc(deklaracjaZmiennej.getWartosc());
                for (Zmienna zmienna : noweZmienne) {
                    if (zmienna.getNazwa().equals(z.getNazwa()))
                        throw new IllegalArgumentException("Zmienna o danej nazwie juz istnieje.");
                }
                noweZmienne.add(z);
            }
        } catch (ArithmeticException | IllegalArgumentException e) {
            System.out.println("Wystapil blad: " + e.getMessage());
            System.out.println("Deklaracja zmiennej");
            wypiszZmienne(srodowisko.dajOstatniaListe());
            exit(-1);
        }
        return noweZmienne;
    }

    public void dodajDoSwojejListyZmienne(Srodowisko srodowisko, List<Zmienna> zmienneSwoje) {
        if (srodowisko.dajRozmiarListy() != 0) {
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
    }

    public void deklarujProcedury(Debugger debugger, Srodowisko srodowisko) {
        for (DeklaracjaProcedury deklaracjaProcedury : deklaracjaProcedur) {
            deklaracjaProcedury.wykonajInstrukcje(debugger, srodowisko);
        }
    }



    public void wykonajInstrukcje(Debugger debugger, Srodowisko srodowisko) {
        List<Zmienna> zmienneSwoje = deklarujZmienne(debugger, srodowisko);
        dodajDoSwojejListyZmienne(srodowisko, zmienneSwoje);

        deklarujProcedury(debugger, srodowisko);

        srodowisko.dodajListeZmiennych(zmienneSwoje);
        srodowisko.dodajListeProcedur(deklaracjaProcedur);

        for (Instrukcja i : instrukcje) {
            debugger.debugger(i, srodowisko);
            i.wykonajInstrukcje(debugger, srodowisko);
        }

        srodowisko.usunOstatniaListeZmiennych();
        srodowisko.usunOstatniaListeProcedur();

        if (srodowisko.dajRozmiarListy() == 0) {
            wypiszZmienne(zmienneSwoje);
        }
    }

    public void wypiszZmienne(List<Zmienna> zmienne) {
        for (Zmienna z : zmienne) {
            System.out.println("Nazwa " + z.getNazwa() + "| Wartosc " + z.oblicz(zmienne));
        }
    }

    public void wypiszInstrukcje() {
        System.out.println("Blok");
    }
}