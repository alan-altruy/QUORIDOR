package be.ac.umons.student.fouretaltruy.quoridor;

// ALTRUY ALAN - JASON FOURET //  Execution du programme ainsi que la boucle.
public class QuoridorLoop
{
	public static void main(String[] args)
	{
		/**/
		QuoridorGame game = new QuoridorGame();;
		int loop=0;
		int nbPlayers=game.getNbPlayers();
		while (loop<nbPlayers)
		{
			game.showDisplay();
			game.PlayerAction(loop);
			game.showDisplay();
			loop++;
		}
	}
}
