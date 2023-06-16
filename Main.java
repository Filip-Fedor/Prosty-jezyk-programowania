import Instrukcje.*;
import Program.Program;
import Wyrazenia.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {


        List<DeklaracjaZmiennej> d1 = new ArrayList<>();
        d1.add(new DeklaracjaZmiennej("n", new Literal(30)));
        Blok b1 = new Blok(d1);

        Wyrazenie f1 = new Odejmowanie(new Zmienna("n"), new Literal(1));
        Petla for1 = new Petla("k", f1);
        b1.dodajInstrukcje(for1);

        List<DeklaracjaZmiennej> d2 = new ArrayList<>();
        d2.add(new DeklaracjaZmiennej("p", new Literal(1)));
        Blok b2 = new Blok(d2);

        b2.dodajInstrukcje(new Przypisanie("k", new Dodawanie(new Zmienna("k"), new Literal(2))));
        for1.dodajInstrukcje(b2);

        Wyrazenie f2 = new Odejmowanie(new Zmienna("k"), new Literal(2));
        Petla for2 = new Petla("i", f2);
        b2.dodajInstrukcje(for2);

        for2.dodajInstrukcje(new Przypisanie("i", new Dodawanie(new Zmienna("i"), new Literal(2))));

        Warunkowa war1 = new Warunkowa(new Modulo(new Zmienna("k"), new Zmienna("i")), new Literal(0), "=");
        war1.dodajPrawda(new Przypisanie("p", new Literal(0)));

        for2.dodajInstrukcje(war1);

        Warunkowa war2 = new Warunkowa(new Zmienna("p"), new Literal(1), "=");
        war2.dodajPrawda(new Print(new Zmienna("k")));


        b2.dodajInstrukcje(war2);

        Program program = new Program(b1);
        program.wykonaj();

    }
}