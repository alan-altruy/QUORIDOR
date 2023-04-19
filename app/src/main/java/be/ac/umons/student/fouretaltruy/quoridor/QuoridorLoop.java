package be.ac.umons.student.fouretaltruy.quoridor;// ALTRUY ALAN - JASON FOURET //  Execution du programme principal, fichier exec, racine.
import java.io.*;
public class QuoridorLoop
{
	private boolean loopGame=true, loopPlayer=true;
	private int winner, nbPlayers=2, nb=0;
	private QuoridorGame game = new QuoridorGame();
	public int start()
	{
		while (loopGame)
		{
			game.newGame=false;
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
					if (game.loaded && game.playerWhoIsPlaying==1)
					{
						nb=1;
					}
					game.loaded=false;
					game.PlayerAction(nb);
					if (!waitAction())
					{
						return 0;
					}
					winner=game.GameHasWinner();
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
		while(game.wait)
		{
			game.gui.showGui();
			if (game.newGame)
			{
				loopPlayer=false;
				game.wait=false;
				nb=4;
			}
		}
		game.wait=true;
		return true;
	}
	public static void main(String[] args)
	{
		QuoridorLoop loop= new QuoridorLoop();
		loop.start();
	}
}