package be.ac.umons.student.fouretaltruy.quoridor;// ALTRUY ALAN - JASON FOURET //  Execution du programme principal, fichier exec, racine.
import java.io.*;
public class QuoridorLoop
{
	public static void main(String[] args)
	{
		/**/
		QuoridorGame game;
		boolean loop=true, loopPlayer=true;
		int winner;
		int nbPlayers=0;
		game = new QuoridorGame();
		while (loop)
		{
			game.newGame=false;
			loopPlayer=true;
			game.showStartScreen();
			while(game.wait)
			{
				System.out.println("Waiting Action");
				if (game.closeGame)
				{
					loop=false;
					loopPlayer=false;
				}
			}
			if (!game.loaded)
			{
				game.InitPlayers();
			}
			if (loop)
			{
				nbPlayers=2;
				game.wait=true;
			}
			while (loopPlayer)
			{
				for (int nb=0; nb<nbPlayers; nb++)
				{
					if (game.newGame)
					{
						loopPlayer=false;
						nb=4;
					}
					else
					{
						if (game.loaded && game.playerWhoIsPlaying==1)
						{
							nb=1;
						}
						game.PlayerAction(nb);
						game.loaded=false;
						winner=game.GameHasWinner();
						if (winner!=3)
						{
							game.showWinner(winner);
							nb=4;
						}
					}
				}
			}
		}
	}
}
