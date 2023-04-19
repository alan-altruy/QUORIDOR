package be.ac.umons.student.fouretaltruy.quoridor;

import java.io.Serializable;
public class QuoridorFence implements Serializable
{
    private static final long serialVersionUID = 1L;
    private QuoridorTray tray;
    private QuoridorGame game;
    public QuoridorFence(QuoridorGame _game)
    {
        game=_game;
        tray=game.tray;
    }
    public void newFence(int pos_x,int pos_y,int dir)
    {
        if(canSetFence(pos_x,pos_y,dir))
        {
            addFence(pos_x, pos_y, dir);
            QuoridorPathFind pathFind = new QuoridorPathFind(game);
            boolean verif1=pathFind.verif();
            remFence(pos_x, pos_y, dir);
            if (game.players[game.playerWhoIsPlaying].GetUsedFences()<10 && verif1)
            {
                game.players[game.playerWhoIsPlaying].setUsedFences();
                addFence(pos_x, pos_y, dir);
                game.wait=false;
            }
        }
    }
    public boolean canSetFence(int pos_x, int pos_y, int dir)
    {
        boolean verif1=(pos_x%2==1 && (dir==1 || pos_y%2==1));
        boolean verif2=(pos_x%2==0 && (dir==0 || pos_y%2==0));
        boolean verif3=tray.verifAlreadyFenceAndWall(pos_x, pos_y, dir);
        if  (verif1 || verif2 || verif3)
        {
            return false;
        }
        return true;
    }
    /**
         * Permet d'ajouter une barrière au plateau.
         * 
         * @param pos_x
         *            La position x de l'endroit désiré pour positionner la barrière.
         * @param pos_y
         *            La position y de l'endroit désiré pour positionner la barrière.
         * @param dir
         *            La direction de la barrière désirée (0= vertical , 1= horizontal).
         */
	public void addFence(int pos_x, int pos_y, int dir)
	{
		if (dir==0)
		{
			for (int _pos_x=0; _pos_x<=2; _pos_x++)
			{
				if (_pos_x==0)
				{
					tray.cells[pos_x+_pos_x][pos_y].ChangeType(3);
				}
				else
				{
					tray.cells[pos_x+_pos_x][pos_y].ChangeType(10);
				}
			}
		}
		else if (dir==1)
		{
			for (int _pos_y=0; _pos_y<=2; _pos_y++)
			{
				if (_pos_y==0)
				{
					tray.cells[pos_x][pos_y+_pos_y].ChangeType(4);
				}
				else
				{
					tray.cells[pos_x][pos_y+_pos_y].ChangeType(10);
				}
			}
		}
	}
	public void remFence(int pos_x, int pos_y, int dir)
	{
		if (dir==0)
		{
			for (int _pos_x=0; _pos_x<=2; _pos_x++)
			{
				if (_pos_x==0)
				{
					tray.cells[pos_x+_pos_x][pos_y].ChangeType(0);
				}
				else
				{
					tray.cells[pos_x+_pos_x][pos_y].ChangeType(0);
				}
			}
		}
		else if (dir==1)
		{
			for (int _pos_y=0; _pos_y<=2; _pos_y++)
			{
				if (_pos_y==0)
				{
					tray.cells[pos_x][pos_y+_pos_y].ChangeType(0);
				}
				else
				{
					tray.cells[pos_x][pos_y+_pos_y].ChangeType(0);
				}
			}
		}
	}
}