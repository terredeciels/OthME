package othello;


import eval.OthEval;
import oth.Oth;

import java.util.ArrayList;

import static java.util.stream.IntStream.range;
import static oth.Constantes.*;
import static oth.Etat.Coups;
import static oth.Etat.Coups.NOMOVE;

public class Othello {

   private int num=0;
    private boolean passe = true;
    private boolean findepartie;
    private int sB;
    private int sN;
    private final Oth o;

    public Othello() {
        o = new Oth();
    }

    public void jouer() {
        while (!findepartie) {
            o.gen(o.trait);
            o.lcoups = o.lcoups.stream().distinct().toList();//sans doublon
            o.move = OthEval.aleatoire_evaluation(o.lcoups);
            passe_findepartie();
            o.trait = -o.trait;
            o.lcoups = new ArrayList<>();
        }
        resultat();
    }

    private void passe_findepartie() {
        if (o.move == NOMOVE) {
            if (passe) {
                findepartie = true;
            } else {
                passe = true;
            }
        } else {
            if (passe) {
                passe = false;
            }
            o.fmove(!undomove);
            affichage();
        }
    }

    private void affichage() {
        for (Coups cps : o.lcoups)
            System.out.println(cps);
        System.out.println("num " + num++);
        System.out.println(o.trait == blanc ? "blanc" : "noir");
        System.out.println(SCASES[o.move.sq0()]);
        System.out.println(new OthPrinter(o));
    }

    private void resultat() {
        System.out.println("fin de partie");
        range(0, 100).forEach(c -> {
            switch (o.etats[c]) {

                case blanc -> sB++;
                case noir -> sN++;
            }
        });
        System.out.println("blancs: " + sB + " noirs: " + sN);
    }

}