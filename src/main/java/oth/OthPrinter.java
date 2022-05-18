package oth;

import static java.util.stream.IntStream.range;

public class OthPrinter {
    int[] etats;

    @Override
    public String toString() {
        StringBuilder spos = new StringBuilder();
        range(0, 100).forEach(_case -> {
            if (etats[_case] == Constantes.vide) spos.append("- ");
            else spos.append(print(etats[_case])).append(" ");
            if (_case % 10 == 9) spos.append("\n");
        });
        return spos.toString();
    }

    String print(int etat) {
        return switch (etat) {
            case Constantes.vide -> "_";
            case Constantes.blanc -> "b";
            case Constantes.noir -> "n";
            case Constantes.out -> " ";
            default -> "?";
        };
    }
}
