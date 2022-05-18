package perft;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static perft.PerftSpeed.count;

class PerftSpeedTest {
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
    @org.junit.jupiter.api.Test
    void main() {
        String[] expectcount = new String[]{"", "4", "12", "56", "244", "1396", "8200", "55092",
                "390216", "3005288", "24571284", "212258800", "1939886636", "18429641748", "184042084512"};
        PerftSpeed.perftTest();
        for (int n = 1; n < 14; n++) assertEquals(count[n], expectcount[n]);

    }
}