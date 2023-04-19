package be.ac.umons.student.fouretaltruy.quoridor;// ALTRUY ALAN - JASON FOURET //

import java.io.Serializable;

/**
 * La classe permettant l'ajout et les vérifications de barrières dans Quridor
 * et qui est implémentéé de Serializable
 */
public class QuoridorFence implements Serializable
{
    private static final long serialVersionUID = 1L;
    /**
         * Plateau sur lequel les barrières sont posées et/ou testées
         */
    private QuoridorTray tray;
    /**
         * Jeu dans lequel les barières sont posées et/ou testées
         */
    private QuoridorGame game;
    /**
         * Initialise une instance de barrières pour Quoridor
         * @param _game : Jeu dans lequel les barrières sont vérifiées et posées
         */
    public QuoridorFence(QuoridorGame _game)
    {
        game=_game;
        tray=game.getTray();
    }
    /**
         * Permet de vérifier si le jeu permet d'ajouter une barrière:
         * <ul><li>S'il le permet, la méthode l'ajoute</li>
         * <li>Sinon elle ne l'ajoute pas</li></ul>
         * @param pos_x : Position x de la barrière
         * @param pos_y : Position y de la barrière
         * @param dir :  Direction de la barrière
         * @return True si la barrière a été ajoutée
         * <li>False sinon</li>
         */
    public boolean newFence(int pos_x,int pos_y,int dir)
    {
        if(canSetFence(pos_x,pos_y,dir))
        {
            add(pos_x, pos_y, dir);
			boolean verif1=game.getUsedFencesPlayer(game.getPlayerWhoIsPlaying())<10;
            boolean verif2=tray.canPass();
            remove(pos_x, pos_y, dir);
            if (verif1 && verif2)
            {
                game.setUsedFencesPlayer();
                add(pos_x, pos_y, dir);
                game.setWait(false);
                return true;
            }
        }
        return false;
    }
    /**
         * Permet de vérifier si le jeu permet d'ajouter une barrière:
         * @param pos_x : Position x de la barrière
         * @param pos_y : Position y de la barrière
         * @param dir :  Direction de la barrière
         * @return True si le jeu permet de poser la barrière
         * <li>False sinon</li>
         */
    public boolean canSetFence(int pos_x, int pos_y, int dir)
    {
        boolean verif1=(pos_x%2==1 && (dir==1 || pos_y%2==1) || pos_x<1 || pos_y<1 || pos_y>16 || pos_x>17);
        boolean verif2=(pos_x%2==0 && (dir==0 || pos_y%2==0) || pos_x<1 || pos_y<1 || pos_x>16 || pos_y>17);
        if  (verif1 || verif2)
        {
            return false;
        }
        else if (tray.verifAlreadyFenceAndWall(pos_x, pos_y, dir))
        {
            return false;
        }
        return true;
    }
    /**
         * Permet d'ajouter une barrière au plateau.
         * @param pos_x : La position x de la barrière
         * @param pos_y : La position y de la barrière
         * @param dir : La direction de la barrière
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
    /**
         * Permet de retirer una barrière du plateau
         * @param pos_x : La position x de la barrière
         * @param pos_y : La position y de la barrière
         * @param dir : La direction de la barrière
         */
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