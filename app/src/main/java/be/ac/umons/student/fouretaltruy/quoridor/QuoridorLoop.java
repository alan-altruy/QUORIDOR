// ALTRUY ALAN - JASON FOURET //  Execution du programme ainsi que la boucle.
package be.ac.umons.student.fouretaltruy.quoridor;
public class QuoridorLoop
{
	public static void main(String[] args)
	{
		/**/
		QuoridorGame game = new QuoridorGame();;
		int loop=1;
		int nbPlayers=game.InitGame();
		while (loop<nbPlayers+1)
		{

			game.showDisplay();
			loop+=2;
		}
	}
}