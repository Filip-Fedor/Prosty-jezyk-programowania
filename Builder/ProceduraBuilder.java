package Builder;

import Instrukcje.*;
import Wyrazenia.Wyrazenie;

import java.util.List;

public class ProceduraBuilder {

    private DeklaracjaProcedury deklaracjaProcedury;


    public ProceduraBuilder(String nazwa, List<String> parametry) {
       deklaracjaProcedury = new DeklaracjaProcedury(nazwa, parametry);
    }


    public ProceduraBuilder przypisanie(String nazwa, Wyrazenie wyrazenie) {
        Przypisanie przypisanie = new Przypisanie(nazwa, wyrazenie);
        deklaracjaProcedury.dodajInstrukcje(przypisanie);
        return this;
    }

    public ProceduraBuilder print(Wyrazenie wyrazenie) {
        Print print = new Print(wyrazenie);
        deklaracjaProcedury.dodajInstrukcje(print);
        return this;
    }

    public ProceduraBuilder petla(Petla petla) {
        deklaracjaProcedury.dodajInstrukcje(petla);
        return this;
    }

    public ProceduraBuilder blok(Blok blok) {
        deklaracjaProcedury.dodajInstrukcje(blok);
        return this;
    }

    public ProceduraBuilder warunkowa(Warunkowa warunkowa) {
        deklaracjaProcedury.dodajInstrukcje(warunkowa);
        return this;
    }


    public DeklaracjaProcedury build() {
        return deklaracjaProcedury;
    }
}
