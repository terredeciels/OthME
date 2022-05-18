package oth;


import eval.OthEval;

import java.util.ArrayList;

import static java.util.stream.IntStream.range;
import static oth.Constantes.*;
import static oth.Oth.ETAT.Coups;
import static oth.Oth.ETAT.Coups.NOMOVE;

public class Othello {

    private int num;
    private boolean passe;
    private boolean findepartie;
    private int sB;
    private int sN;

    public static void main(String[] args) {
        new Othello().jouer();
    }

    private void jouer() {
        Oth o = new Oth();
        for (num = 0; num < 64; num++) {
            if (findepartie) {
                System.out.println("fin de partie");
                range(0, 100).forEach(c -> {
                    switch (o.etats[c]) {

                        case blanc -> sB++;
                        case noir -> sN++;
                    }
                });
                System.out.println("blancs: " + sB + " noirs: " + sN);
                break;
            }

            jouer(o);
        }

    }

    private void jouer(Oth o) {
        o.gen(o.trait);
        o.lcoups = o.lcoups.stream().distinct().toList();//sans doublon
        System.out.println("nb_coups " + o.lcoups.size());
        for (Coups cps : o.lcoups)
            System.out.println(cps);

        o.move = OthEval.aleatoire_evaluation(o.lcoups);
        if (o.move != NOMOVE) {
            if (passe) passe = false;
            o.domove();
            System.out.println("num " + num);
            System.out.println(o.trait == blanc ? "blanc" : "noir");
            System.out.println(SCASES[o.move.sq0()]);
            System.out.println(o);
            o.trait = -o.trait;
            o.lcoups = new ArrayList<>();
        } else {
            if (passe) {
                findepartie = true;

            }
            o.trait = -o.trait;
            o.lcoups = new ArrayList<>();
            passe = true;
        }
    }


}