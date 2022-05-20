package eval;


import java.util.List;
import java.util.Random;

import static oth.Oth.Coups;
import static oth.Oth.Coups.NOMOVE;

public class OthEval {

    public static Coups eval(List<Coups> lcoups) {
        if (lcoups.size() != 0) return lcoups.get(new Random().nextInt(lcoups.size()));
        else return NOMOVE;
    }

}