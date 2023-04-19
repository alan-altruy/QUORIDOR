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
        tray=game.getTray();
    }
    public boolean newFence(int pos_x,int pos_y,int dir)
    {
        if(canSetFence(pos_x,pos_y,dir))
        {
            add(pos_x, pos_y, dir);
			QuoridorPathFind pathFind = new QuoridorPathFind(game);
			boolean verif1=game.getUsedFencesPlayer(game.getPlayerWhoIsPlaying())<10;
            boolean verif2=pathFind.verif();
            remove(pos_x, pos_y, dir);
            if (verif1 && verif2)
            {
                game.setUsedFencesPlayer();
                add(pos_x, pos_y, dir);
                return true;
            }
        }
        return false;
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
	public void add(int pos_x, int pos_y, int dir)
	{
		if (dir==0)
		{
			for (int _pos_x=0; _pos_x<=2; _pos_x++)
			{
                if (_pos_x==0)
                {
                    tray.setTypeOfCell(pos_x+_pos_x, pos_y, 3);
                }
                else
                {
                    tray.setTypeOfCell(pos_x+_pos_x, pos_y, 10);
                }
			}
		}
		else if (dir==1)
		{
			for (int _pos_y=0; _pos_y<=2; _pos_y++)
			{
                if (_pos_y==0)
                {
                    tray.setTypeOfCell(pos_x, pos_y+_pos_y, 4);
                }
                else
                {
                    tray.setTypeOfCell(pos_x, pos_y+_pos_y, 10);
                }
			}
		}
	}
	public void remove(int pos_x, int pos_y, int dir)
	{
		if (dir==0)
		{
			for (int _pos_x=0; _pos_x<=2; _pos_x++)
			{
				tray.setTypeOfCell(pos_x+_pos_x, pos_y, 0);
			}
		}
		else if (dir==1)
		{
			for (int _pos_y=0; _pos_y<=2; _pos_y++)
			{
				tray.setTypeOfCell(pos_x, pos_y+_pos_y, 0);
			}
		}
	}
}