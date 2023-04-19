// ALTRUY ALAN - JASON FOURET //

package be.ac.umons.student.fouretaltruy.quoridor.main;

import be.ac.umons.student.fouretaltruy.quoridor.game.QuoridorGame;

/**
 * Classe principale du jeu Quoridor permettant son execution
 */
public class QuoridorLoop {
    /**
     * Permet de savoir si le jeu lancé
     */
    private boolean loopGame = true;
    /**
     * Numéro du joueur qui joue
     */
    private int playerWhoIsPlaying = 0;
    /**
     * Permet de créer le jeu
     */
    private final QuoridorGame game = new QuoridorGame();

    /**
     * Permet de lancer une partie
     * <ul><li>Lance toutes les interfaces graphiques nécessaires</li>
     * <li>Vérifie si le jeu a été chargé</li>
     * <li>Permet au joueur de lancer une action</li>
     * <li>Vérifie si le jeu possède un gagnant</li>
     * <li>Vérifie si le jeu doit être fermé</li>
     * <li>Vérifie si la fenêtre doit être fermé</li></ul>
     */
    public void start() {
        int winner;
        while (!game.isClosed()) {
            game.setNewGame(false);
            game.setNbPlayers(-1);
            loopGame = true;
            game.showStartScreen();
            waitAction();
            while (loopGame) {
                int nbPlayers = 2;
                for (playerWhoIsPlaying = 0; playerWhoIsPlaying < nbPlayers; playerWhoIsPlaying++) {
                    if (game.getLoaded() && game.getPlayerWhoIsPlaying() == 1) {
                        playerWhoIsPlaying = 1;
                    }
                    game.setLoaded(false);
                    game.playerAction(playerWhoIsPlaying);
                    waitAction();
                    winner = game.hasWinner();
                    if (winner != 3) {
                        game.showWinner(winner);
                        waitAction();
                    }
                }
            }
        }
    }

    /**
     * Permet d'attendre une action de joueur
     */
    public void waitAction() {
        while (game.getWait()) {
            if (game.getNewGame()) {
                loopGame = false;
                game.setWait(false);
                playerWhoIsPlaying = 4;
            } else {
                game.showGui();
            }
            if (game.isClosed()) System.exit(0);
        }
        game.setWait(true);
    }

    public static void main(String[] args) {
        new QuoridorLoop().start();
    }
}