package perft;

import oth.Oth;

import java.util.List;

import static oth.Constantes.undomove;


//DEPTH  #LEAF NODES   #FULL-DEPTH  #HIGHER
//        ==========================================
//        1            4
//        2           12
//        3           56
//        4          244
//        5         1396
//        6         8200
//        7        55092
//        8       390216
//        9      3005288
//        10     24571284
//        11    212258800  =    212258572  +    228
//        12   1939886636  =   1939886052  +    584
//        13  18429641748  =  18429634780  +   6968
//        14 184042084512  = 184042061172  +  23340
//-----------------------------------------------------
//        Depth 1 : 0.0160349 sec
//        Count = 4
//        Depth 2 : 0.0238381 sec
//        Count = 12
//        Depth 3 : 0.0287492 sec
//        Count = 56
//        Depth 4 : 0.0332539 sec
//        Count = 244
//        Depth 5 : 0.0447566 sec
//        Count = 1396
//        Depth 6 : 0.0673737 sec
//        Count = 8200
//        Depth 7 : 0.1341337 sec
//        Count = 55092
//        Depth 8 : 0.3990822 sec
//        Count = 390216
//        Depth 9 : 1.8116074 sec
//        Count = 3005288
//        Depth 10 : 12.1013382 sec
//        Count = 24571284
//        Depth 11 : 96.0875799 sec
//        Count = 212258800
//        Depth 12 : 834.5599024 sec
//        Count = 1939886636

public class PerftSpeed {
    static final int MAX_DEPTH = 10;

    public static void main(String[] args) {
        perftTest();
    }

    static void perftTest() {

        Oth o = new Oth();
        double t0 = System.nanoTime();
        for (int depth = 1; depth <= MAX_DEPTH; depth++) {
            PerftResult res = perft(new Oth(o), depth);
            double t1 = System.nanoTime();
            System.out.println("Depth " + depth + " : " + (t1 - t0) / 1000000000 + " sec");
            System.out.println("Count = " + res.moveCount);
        }

    }

    static PerftResult perft(Oth o, int depth) {

        PerftResult result = new PerftResult();
        if (depth == 0) {
            result.moveCount++;
            return result;
        }

        o.gen(o.trait);
        List<Oth.Coups> moves = o.lcoups.stream().distinct().toList();
        if (moves.size() != 0) {
            for (Oth.Coups move : moves) {
                o.move = move;
                o.fmove(!undomove);
                PerftResult subPerft = perft(new Oth(o), depth - 1);
                o.fmove(undomove);
                result.moveCount += subPerft.moveCount;

            }
        } else {
            PerftResult subPerft = perft(new Oth(o), depth - 1);
            result.moveCount += subPerft.moveCount;
        }
        return result;
    }

    static class PerftResult {

        public long timeTaken = 0;
        long moveCount = 0;

    }
}