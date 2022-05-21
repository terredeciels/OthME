package othello;

import oth.Constantes;
import oth.Oth;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static eval.OthEval.eval;
import static java.util.stream.IntStream.range;
import static oth.Oth.Coups.NOMOVE;

public class Othello implements Constantes {

    static int nb;
    static int max = 5;
    private static FileWriter writter;
    OthPrinter othprint;
    Oth o;
    private boolean passe = true;
    private boolean findepartie;
    private int sN;
    private int sB;


    public Othello() {
        o = new Oth();
        othprint = new OthPrinter(o);
    }

    public static void main(String[] args) {
        max = 5;
        try {
            File toFile = new File(pathname + filename);
            if (toFile.createNewFile()) System.out.println("Fichier: " + toFile.getName());
            else System.out.println("-----------");
            writter = new FileWriter(filename);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (nb = 1; nb <= max; nb++) {
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
        resultat();
        if (nb == max)
            try {
                writter.close();
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

    void resultat() {
        sN = 0;
        sB = 0;
        range(0, 100).forEach(c -> {
            switch (o.etats[c]) {

                case blanc -> sB++;
                case noir -> sN++;
            }
        });
        String R = sB > sN ? "1" : (sN > sB ? "0" : "0.5");
        System.out.println(R + "," + sB + "," + sN);
        try {
            // R = sB > sN ? "1" : (sN > sB ? "0" : "0.5");
            writter.write(R + "," + sB + "," + sN);
            writter.write("\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}