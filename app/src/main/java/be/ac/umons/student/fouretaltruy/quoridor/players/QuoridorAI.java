// ALTRUY ALAN - JASON FOURET //

package be.ac.umons.student.fouretaltruy.quoridor.players;

import be.ac.umons.student.fouretaltruy.quoridor.game.QuoridorFence;
import be.ac.umons.student.fouretaltruy.quoridor.game.QuoridorGame;
import be.ac.umons.student.fouretaltruy.quoridor.game.QuoridorPathFind;

/**
 * L'intelligence artificielle du Quoridor qui hérite de QuoridorPlayers
 */
public class QuoridorAI extends QuoridorPlayers {
    private static final long serialVersionUID = 1L;
    /**
     * Difficulté de l'intelligence artificielle
     */
    private final boolean hard;
    /**
     * Numéro de l'autre joueur
     */
    private final int numOfOtherPlayer;
    /**
     * Le jeu dans lequel l'intelligence artificielle joue
     */
    private final QuoridorGame game;

    /**
     * Initiliase une intelligence artificielle du Quoridor
     * <ul><li>Initialise un joueur dans la classe mère (QuoridorPlayers)</li>
     * <li>Enregistre les informations nécessaires (jeu, plateau, difficulté)</li></ul>
     *
     * @param _game  : Le jeu dans lequel se trouve l'IA
     * @param _num   : Le numéro de l'IA
     * @param _name  : Le nom de l'IA
     * @param _pos_x : La position x de l'IA sur le plateau
     * @param _pos_y : La position y de l'IA sur le plateau
     * @param _hard  : La difficulté de l'IA
     */
    public QuoridorAI(QuoridorGame _game, int _num, String _name, int _pos_x, int _pos_y, boolean _hard) {
        super(_game.getTray(), _num, _name, _pos_x, _pos_y);
        setAi(true);
        game = _game;
        hard = _hard;
        if (_num == 0) {
            numOfOtherPlayer = 1;
        } else {
            numOfOtherPlayer = 0;
        }
    }

    /**
     * L'action de l'intelligence artificielle
     */
    public boolean action() {
        if (hard) {
            return actionHard();
        } else {
            return actionEasy();
        }
    }

    /**
     * L'action de l'intelligence artificielle en difficulté difficile
     */
    public boolean actionHard() {
        int[] move = new QuoridorPathFind(game).moveSmallPath(getNum());
        QuoridorFence fence = canSetAIFence();
        if (fence != null && move[2] > 1) {
            fence.newFence();
            return false;
        }
        setPos(move[0], move[1]);
        return true;
    }

    /**
     * L'action de l'intelligence artificielle en difficulté facile
     */
    public boolean actionEasy() {
        int[] move = new QuoridorPathFind(game).moveSmallPath(getNum());
        int[] moveOther = new QuoridorPathFind(game).moveSmallPath(numOfOtherPlayer);
        QuoridorFence fence = canSetAIFence();
        int rand = (int) (Math.random() * ((5) + 1));
        if ((rand == 2 || moveOther[2] < 2) && fence != null) {
            fence.newFence();
            return true;
        }
        setPos(move[0], move[1]);
        return true;
    }

    /**
     * Permet de savoir si l'intelligence artificielle en difficulté normale peut poser une barrière
     *
     * @return La barrière qui a été posée
     * <li> Si aucune barrière n'a été posée, return null</li>
     */
    public QuoridorFence canSetAIFence() {
        QuoridorFence fence, finalFence = null;
        boolean veryGood = false;
        int[] movePlayer = new QuoridorPathFind(game).moveSmallPath(getNum());
        int[] moveOther = new QuoridorPathFind(game).moveSmallPath(numOfOtherPlayer);
        int nbOfMoves = moveOther[2] - movePlayer[2];
        int verif = nbOfMoves, memo;
        for (int x = 1; x < 19; x++) {
            for (int y = 1; y < 19; y++) {
                for (int dir = 0; dir < 2; dir++) {
                    fence = new QuoridorFence(game, x, y, dir);
                    memo = verifFence(fence, movePlayer[2], moveOther[2]);
                    if (memo > verif && memo != 100) {
                        verif = memo;
                        finalFence = new QuoridorFence(game, x, y, dir);
                        if (memo - 5 > verif) {
                            veryGood = true;
                        }
                    }
                }
            }
        }
        int fencePlayer = getUsedFences(), fenceOther = game.getPlayer(numOfOtherPlayer).getUsedFences();
        boolean verif1 = verif - nbOfMoves > 3 && fencePlayer <= fenceOther;
        boolean verif2 = (movePlayer[2] > moveOther[2] && fencePlayer < fenceOther) || (moveOther[2] <= movePlayer[2] && moveOther[2] < 3);
        if (verif1 || verif2 || veryGood || !hard) {
            return finalFence;
        }
        return null;
    }

    /**
     * Permet de savoir s'il est utile de poser une barrière
     *
     * @param fence            : La barrière
     * @param movePlayerBefore : Le nombre de déplacement de l'IA pour gagner
     * @param moveOtherBefore: Le nombre de déplacement de l'Adversaire pour gagner
     * @return L'écart entre le nombre de déplacement de l'adversaire et de l'IA
     */
    public int verifFence(QuoridorFence fence, int movePlayerBefore, int moveOtherBefore) {
        int diffMove = 100;
        if (fence.canSetFence()) {
            fence.set();
            int moveOtherAfter = new QuoridorPathFind(game).moveSmallPath(numOfOtherPlayer)[2];
            int movePlayerAfter = new QuoridorPathFind(game).moveSmallPath(getNum())[2];
            fence.remove();
            if (moveOtherBefore - movePlayerBefore < moveOtherAfter - movePlayerAfter) {
                diffMove = moveOtherAfter - movePlayerAfter;
            }
        }
        return diffMove;
    }
}