package eval;

import oth.Oth;

import java.util.List;
import java.util.Random;

import static oth.Oth.Coups.NOMOVE;

public class OthEval {

    public static Oth.Coups aleatoire_evaluation(List<Oth.Coups> lcoups) {
        if (lcoups.size() != 0)
            return lcoups.get(new Random().nextInt(lcoups.size()));
        else return NOMOVE;
    }

}