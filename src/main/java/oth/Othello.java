package oth;


import eval.OthEval;

import java.util.ArrayList;

import static java.util.stream.IntStream.range;
import static oth.Constantes.*;
import static oth.Etat.Coups;
import static oth.Etat.Coups.NOMOVE;

public class Othello {

    private int num;
    private boolean passe = true;
    private boolean findepartie;
    private int sB;
    private int sN;

    public static void main(String[] args) {
        new Othello().jouer();
    }

    void jouer() {
        Oth o = new Oth();

        num = 0;
        while (true) {
            //if (num >= 64) break;
            if (findepartie) {
                resultat(o);
                break;
            }
            jouer(o);
            num++;
        }
    }

    private void jouer(Oth o) {
        o.gen(o.trait);
        o.lcoups = o.lcoups.stream().distinct().toList();//sans doublon
        o.move = OthEval.aleatoire_evaluation(o.lcoups);
        passe_findepartie(o);

        o.trait = -o.trait;
        o.lcoups = new ArrayList<>();
    }

    private void passe_findepartie(Oth o) {
        if (o.move == NOMOVE) if (passe) findepartie = true;
        else passe = true;
        else {
            if (passe) passe = false;
            o.fmove(!undomove);
           // affichage(o);
        }
    }

    private void affichage(Oth o) {
        for (Coups cps : o.lcoups)
            System.out.println(cps);
        System.out.println("num " + num);
        System.out.println(o.trait == blanc ? "blanc" : "noir");
        System.out.println(SCASES[o.move.sq0()]);
        System.out.println(new OthPrinter(o));
    }

    private void resultat(Oth o) {
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