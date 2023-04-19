package be.ac.umons.student.fouretaltruy.quoridor;// ALTRUY ALAN - JASON FOURET //

import java.io.Serializable;

/**
 * Classe permettant d'instancier un joueur pour le jeu Quoridor qui est implémentée de Serializable
 */
public class QuoridorPlayers implements Serializable
{
    private static final long serialVersionUID = 1L;
    /**
         * Nom du joueur
         */
    private String name;
    /**
         * Nombre de barrières utilisées par le joueur
         */
    private int nbUsedFences;
    /**
         * Numéro du joueur
         */
     private int num;
    /**
         * Position x du joueur
         */
    private int pos_x;
    /**
         * Position y du joueur
         */
    private int pos_y;
    /**
         * Plateau sur lequel se trouve le joueur
         */
        private boolean ai=false;
    private QuoridorTray tray;
    /**
         * Initialise un joueur
         * @param _tray : Le plateau sur lequel le joueur se trouve
         * @param _num : Le numero du joueur
         * @param _name : Le nom du joueur.
         * @param _x : La position x du joueur
         * @param _y : La position y du joueur
         */
    public QuoridorPlayers(QuoridorTray _tray, int _num, String _name, int _x, int _y)
    {
        tray=_tray;
        name=_name;
        num=_num;
        nbUsedFences=0;
        pos_x=_x;
        pos_y=_y;
        set();
    }
    /**
         * Permet de savoir si le joueur a gagné
         * @return True si le joueur est gagnant
         * <li>False sinon</li>
         */
    public boolean areYouTheWinner()
    {
        if (num==0 && pos_x==17)
        {
            return true;
        }
        else if (num==1 && pos_x==1)
        {
            return true;
        }
        return false;
    }
    /**
         * Permet de faire bouger le joueur grace à une nouvelle position x, une nouvelle position y
         * @param _pos_x : La nouvelle position x du joueur
         * @param _pos_y : La nouvelle position y du joueur
         */
    public void setPos(int _pos_x, int _pos_y)
    {
        remove();
        pos_x=_pos_x;
        pos_y=_pos_y;
        set();
    }
    /**
         * Permet de faire bouger le joueur en lui additionnant des composantes x et y
         * @param _pos_x : La composante x
         * @param _pos_y : La composante y
         */
    public void newPos (int _x, int _y)
    {
        remove();
        pos_x += _x;
        pos_y += _y;
        set();
    }
    /**
         * Permet d'ajouter le joueur au plateau
         */
    public void set()
    {
        tray.setTypeOfCell(pos_x, pos_y, num+1);
    }
    /**
         * Permet de retirer le joueur du plateau
         */
    public void remove()
    {
        tray.setTypeOfCell(pos_x, pos_y, 0);
    }
    /**
         * Permet de retourner le nom du joueur
         * @return Nom du joueur
         */
    public String getName()
    {
        return name;
    }
    /**
         * Permet de retourner le numéro du joueur
         * @return Numéro du joueur
         */
    public int getNum()
    {
        return num;
    }
    /**
         * Permet de retourner le nombre de barrières utilisées par le joueur.
         * @return Nombre de barrières utilisées par le joueur
         */
    public int getUsedFences()
    {
        return nbUsedFences;
    }
    /**
         * Permet d'incrémenter le nombre de barrières utilisées par le joueur
         */
    public void setUsedFences()
    {
        nbUsedFences++;
    }
    /**
         * Permet de retourner la position x du joueur
         * @return Position x du joueur
         */
    public int getPos_x()
    {
        return pos_x;
    }
    /**
         * Permet de retourner la position y du joueur
         * @return Position y du joueur
         */
    public int getPos_y()
    {
        return pos_y;
    }
    /**
         * Permet de savoir si le joueur est une intelligence artificielle
         * @return True s'il est une AI
         * <li>False sinon</li>
         */
     public boolean isAnAi()
    {
        return ai;
    }
    /**
         * Permet de définir une intelligence artificielle
         */
     public void setAi(boolean _ai)
    {
        ai= _ai;
    }
}