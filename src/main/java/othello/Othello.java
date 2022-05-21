package othello;


import oth.Oth;

import java.io.IOException;
import java.util.ArrayList;

import static eval.OthEval.eval;
import static oth.Oth.Coups.NOMOVE;

public class Othello {

    final OthPrinter othprint;
    Oth o;
    private boolean passe = true;
    private boolean findepartie;


    public Othello() {
        o = new Oth();
        othprint = new OthPrinter(o);
    }
    public static void main(String[] args) {
        int max = 5;
        for (int nb = 0; nb < max; nb++) {
            new Othello().jouer();
        }
    }
    public void jouer() {
        findepartie = false;
        passe = false;
        o.lcoups = new ArrayList<>();
        while (true) {
            if (!findepartie) {
                o.gen(o.trait);
                o.move = eval(o.lcoups.stream().distinct().toList());
                passe_et_findepartie();
                o.changeside();
            } else break;
        }
        othprint.resultat();
        try {
            othprint.writter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void passe_et_findepartie() {
        if (o.move == NOMOVE) if (passe) findepartie = true;
        else passe = true;
        else {
            if (passe) passe = false;
            o.fmove(!o.undomove);
            // othprint.affichage();
        }
    }


}