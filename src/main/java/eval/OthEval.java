package eval;

import java.util.List;
import java.util.Random;

import static oth.Oth.ETAT.Coups;
import static oth.Oth.ETAT.Coups.NOMOVE;

public class OthEval {

    public static Coups aleatoire_evaluation(List<Coups> lcoups) {
        if (lcoups.size() != 0)
            return lcoups.get(new Random().nextInt(lcoups.size()));
        else return NOMOVE;
    }

}