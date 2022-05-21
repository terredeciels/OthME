package othello;

public class Partie {
    public static void main(String[] args) {
        int max = 5;
        for (int nb = 0; nb < max; nb++) {
            new Othello().jouer();
        }
    }
}
