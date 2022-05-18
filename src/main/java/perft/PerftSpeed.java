package perft;

import oth.Oth;

import java.util.List;

import static oth.Oth.ETAT.Coups;

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
//        Depth 1 : 0.0233582 sec
//        Count = 4
//        Depth 2 : 0.0327687 sec
//        Count = 12
//        Depth 3 : 0.0381227 sec
//        Count = 56
//        Depth 4 : 0.0422763 sec
//        Count = 244
//        Depth 5 : 0.0535222 sec
//        Count = 1396
//        Depth 6 : 0.0792142 sec
//        Count = 8200
//        Depth 7 : 0.1552523 sec
//        Count = 55092
//        Depth 8 : 0.4575932 sec
//        Count = 390216
// -----------------------------------------
//        Depth 9 : 2.1796524 sec
//        Count = 3005264 ERROR 3005288
//        Depth 10 : 14.8637683 sec
//        Count = 24571000 ERROR 24571284

public class PerftSpeed {

    public static void main(String[] args) {
        perftTest();
    }

    private static void perftTest() {

        Oth o = new Oth();
        int max_depth = 10;
        double t0 = System.nanoTime();
        for (int depth = 1; depth <= max_depth; depth++) {
            PerftResult res = perft(new Oth(o), depth);
            double t1 = System.nanoTime();
            System.out.println("Depth " + depth + " : " + (t1 - t0) / 1000000000 + " sec");
            System.out.println("Count = " + res.moveCount);
        }

    }

    private static PerftResult perft(Oth o, int depth) {

        PerftResult result = new PerftResult();
        if (depth == 0) {
            result.moveCount++;
            return result;
        }

        o.gen(o.trait);
        List<Coups> moves = o.lcoups.stream().distinct().toList();

//        if (moves.size()==0) o.gen(-o.trait);
//        moves = o.lcoups.stream().distinct().toList();

//        if(moves.size()==0) {
//            System.out.println(o.trait == blanc ? "blanc" : "noir");
//           // System.out.println(SCASES[o.move.sq0()]);
//            System.out.println(o);
//            System.exit(0);
//        }

        for (Coups move : moves) {
            o.move = move;
            if (o.domove()) {
                PerftResult subPerft = perft(new Oth(o), depth - 1);
                o.undomove();
                result.moveCount += subPerft.moveCount;
            }
        }
        return result;
    }

    static class PerftResult {

        public long timeTaken = 0;
        long moveCount = 0;

    }
}