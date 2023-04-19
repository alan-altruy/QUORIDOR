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
		int nbPlayers=2;
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
			game.wait=true;
			if (!game.loaded)
			{
				game.InitPlayers();
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
							game.loaded=false;
						}
						game.PlayerAction(nb);
						while(game.wait)
        				{
            				System.out.println("Waiting Action");
        				}
        				game.wait=true;
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