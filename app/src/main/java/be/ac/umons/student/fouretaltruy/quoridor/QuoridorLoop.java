package be.ac.umons.student.fouretaltruy.quoridor;

// ALTRUY ALAN - JASON FOURET //  Execution du programme principal, fichier exec, racine.
public class QuoridorLoop
{
	private boolean loopGame=true, loopPlayer=true;
	private int winner, nbPlayers=2, nb=0;
	private QuoridorGame game = new QuoridorGame();
	public int start()
	{
		while (loopGame)
		{
			game.setNewGame(false);
			loopPlayer=true;
			game.showStartScreen();
			if (!waitAction())
			{
				return 0;
			}
			while (loopPlayer)
			{
				for (nb=0; nb<nbPlayers; nb++)
				{
					if (game.getLoaded() && game.getPlayerWhoIsPlaying()==1)
					{
						nb=1;
					}
					game.setLoaded(false);
					game.playerAction(nb);
					if (!waitAction())
					{
						return 0;
					}
					winner=game.hasWinner();
					if (winner!=3)
					{
						game.showWinner(winner);
						if (!waitAction())
						{
							return 0;
						}
						nb=4;
					}
				}
			}
		}
		return 0;
	}
	public boolean waitAction()
	{
		while(game.getWait())
		{
			
			if (game.getNewGame())
			{
				loopPlayer=false;
				game.setWait(false);
				nb=4;
			}
			else
			{
				game.getGui().showGui2();
			}
		}
		game.setWait(true);
		return true;
	}
	public static void main(String[] args)
	{
		QuoridorLoop loop= new QuoridorLoop();
		loop.start();
	}
}
