package be.ac.umons.student.fouretaltruy.quoridor;

// ALTRUY ALAN - JASON FOURET //  Execution du programme principal, fichier exec, racine.
public class QuoridorLoop
{
	public static void main(String[] args)
	{
		/**/
		QuoridorGame game = new QuoridorGame();;
		boolean loop=true;
		int winner;
		int nbPlayers=game.getNbPlayers();
		while (loop)
		{
			for (int nb=0; nb<nbPlayers; nb++)
			{
				game.PlayerAction(nb);
				winner=game.GameHasWinner();
				if (winner!=3)
				{
					game.showWinner(winner);
					nb=4;
					loop=false;
				}
			}
			
		}
	}
}