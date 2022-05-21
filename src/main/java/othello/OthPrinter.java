package othello;

import oth.Constantes;
import oth.Oth;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static java.util.stream.IntStream.range;

public class OthPrinter implements Constantes {

    final FileWriter writter;
    private final Oth o;
    private int num;
    private int sN;
    private int sB;


    public OthPrinter(Oth o) {
        this.o = o;
        try {
            File toFile = new File(pathname + filename);
            if (toFile.createNewFile()) System.out.println("Fichier: " + toFile.getName());
            else System.out.println("-----------");
            writter = new FileWriter(filename);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        StringBuilder spos = new StringBuilder();
        range(0, 100).forEach(_case -> {
            if (o.etats[_case] == Constantes.vide) spos.append("- ");
            else spos.append(print(o.etats[_case])).append(" ");
            if (_case % 10 == 9) spos.append("\n");
        });
        return spos.toString();
    }

    private String print(int etat) {
        return switch (etat) {
            case vide -> "_";
            case blanc -> "b";
            case noir -> "n";
            case out -> " ";
            default -> "?";
        };
    }

    void affichage() {
        for (Oth.Coups cps : o.lcoups)
            System.out.println(cps);
        System.out.println("num " + num++);
        System.out.println(o.trait == blanc ? "blanc" : "noir");
        System.out.println(SCASES[o.move.sq0()]);
        System.out.println(new OthPrinter(o));
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
