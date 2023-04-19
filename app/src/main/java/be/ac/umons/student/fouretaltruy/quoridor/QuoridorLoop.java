package be.ac.umons.student.fouretaltruy.quoridor;// ALTRUY ALAN - JASON FOURET //

/**
 * Classe principale du jeu Quoridor permettant son execution
 */
public class QuoridorLoop
{
	/**
         * Nombre de joueurs total dans le jeu
         */
	private final int nbPlayers=2;
	/**
         * Permet de savoir si l'interface graphique, la fenêtre doit rester ouverte
         */
	private boolean loopGui=true;
	/**
         * Permet de savoir si le jeu lancé 
         */
	private boolean loopGame=true;
	/**
         * Numéro du joueur qui joue
         */
	private int playerWhoIsPlaying=0;
	/**
         * Permet de créer le jeu
         */
	private QuoridorGame game = new QuoridorGame();
	/**
         * Permet de lancer une partie
		 * <ul><li>Lance toutes les interfaces graphiques nécessaires</li>
		 * <li>Vérifie si le jeu a été chargé</li>
		 * <li>Permet au joueur de lancer une action</li>
		 * <li>Vérifie si le jeu possède un gagnant</li>
		 * <li>Vérifie si le jeu doit être fermé</li>
		 * <li>Vérifie si la fenêtre doit être fermé</li></ul>
         */
	public int start()
	{
		int winner;
		while (loopGui)
		{
			game.setNewGame(false);
			game.setNbPlayers(0);
			loopGame=true;
			game.showStartScreen();
			waitAction();
			while (loopGame)
			{
				for (playerWhoIsPlaying=0; playerWhoIsPlaying<nbPlayers; playerWhoIsPlaying++)
				{
					if (game.getLoaded() && game.getPlayerWhoIsPlaying()==1)
					{
						playerWhoIsPlaying=1;
					}
					game.setLoaded(false);
					game.playerAction(playerWhoIsPlaying);
					waitAction();
					winner=game.hasWinner();
					if (winner!=3)
					{
						game.showGui();
						try {
							Thread.sleep(500);
						} catch (Exception e) {}
						game.showWinner(winner);
						waitAction();
					}
				}
			}
		}
		return 0;
	}
	/**
         * Permet d'attendre une action de joueur
         */
	public void waitAction()
	{
		while(game.getWait())
		{
			
			if (game.getNewGame())
			{
				loopGame=false;
				game.setWait(false);
				playerWhoIsPlaying=4;
			}
			else
			{
				game.waitGui();
			}
		}
		game.setWait(true);
	}
	public static void main(String[] args)
	{
		new QuoridorLoop().start();
	}
}