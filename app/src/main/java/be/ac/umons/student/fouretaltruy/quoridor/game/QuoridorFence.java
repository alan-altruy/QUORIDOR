// ALTRUY ALAN - JASON FOURET //

package be.ac.umons.student.fouretaltruy.quoridor.game;

import java.io.Serializable;

/**
 * La classe permettant l'ajout et les vérifications de barrières dans Quoridor
 * et qui est implémentée de Serializable
 */
public class QuoridorFence implements Serializable
{
    private static final long serialVersionUID = 1L;
    /**
         * Position x de la barrière
         */
    private int pos_x;
    /**
         * Position y de la barrière
         */
    private int pos_y;
    /**
         * Direction de la barrière
         */
    private int dir;
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
         * @param _pos_x : Position x de la barrière
         * @param _pos_y : Position y de la barrière
         * @param _dir :  Direction de la barrière
         */
    public QuoridorFence(QuoridorGame _game, int _pos_x, int _pos_y, int _dir)
    {
        pos_x=_pos_x;
        pos_y=_pos_y;
        dir=_dir;
        game=_game;
        tray=game.getTray();
    }
    /**
         * Permet de vérifier si le jeu permet d'ajouter une barrière:
         * <ul><li>S'il le permet, la méthode l'ajoute</li>
         * <li>Sinon elle ne l'ajoute pas</li></ul>
         * @return True si la barrière a été ajoutée
         * <li>False sinon</li>
         */
    public boolean newFence()
    {
        if(canSetFence())
        {
            set();
            game.setUsedFencesPlayer();
            game.setWait(false);
            return true;
        }
        return false;
    }
    /**
         * Permet de vérifier si le jeu permet d'ajouter une barrière:
         * @return True si le jeu permet de poser la barrière
         * <li>False sinon</li>
         */
    public boolean canSetFence()
    {

        boolean verif1=((pos_x%2==1 && (dir==1 || pos_y%2==1)) || pos_x<0 || pos_y<0 || pos_y>19 || pos_x>19);
        boolean verif2=((pos_x%2==0 && (dir==0 || pos_y%2==0)) || pos_x<0 || pos_y<0 || pos_x>19 || pos_y>19);
        if  (verif1 || verif2 || !tray.verifAlreadyFenceAndWall(pos_x, pos_y, dir))
        {
            return false;
        }
        else
        {
            set();
		    verif1=game.getUsedFencesPlayer(game.getPlayerWhoIsPlaying())<10;
            verif2=tray.canPass();
            remove();
            if (verif1 && verif2)
            {
                return true;
            }
        }
        return false;
    }
    /**
         * Permet d'ajouter une barrière au plateau.
         */
	public void set()
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
                    tray.setTypeOfCell(pos_x+_pos_x, pos_y, 6);
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
                    tray.setTypeOfCell(pos_x, pos_y+_pos_y, 7);
                }
			}
		}
    }
    /**
         * Permet de retirer una barrière du plateau
         */
	public void remove()
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