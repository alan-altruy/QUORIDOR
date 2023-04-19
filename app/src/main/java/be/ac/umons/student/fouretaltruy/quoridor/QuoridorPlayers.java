package be.ac.umons.student.fouretaltruy.quoridor;// ALTRUY ALAN - JASON FOURET //
import java.io.*;
public class QuoridorPlayers implements Serializable
{
    private static final long serialVersionUID = 1L;
    private String name;
    private int nbUsedFences;
    private int num, pos_x, pos_y;
    /**
         * Permet d'initialiser un joueur.
         * 
         * @param _num
         *            Le numero associé au joueur.
         * @param _name
         *            Le nom associé au joueur.
         * @param _x
         *            La position x associée au joueur sur le plateau (nombre impair >0 & <=17).
         * @param _y
         *            La position y associée au joueur sur le plateau (nombre impair >0 & <=17).
         */
    public QuoridorPlayers(int _num, String _name, int _x, int _y)
    {
        name=_name;
        pos_x=_x;
        pos_y=_y;
        num=_num;
        nbUsedFences=0;
    }
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
         * Permet de faire bouger le joueur grace à une nouvelle position x, une nouvelle position y.
         * 
         * @param _x
         *            La nouvelle position x associée au joueur sur le plateau (nombre pair).
         * @param _y
         *            La nouvelle position y associée au joueur sur le plateau (nombre pair).
         */
    public void newPos (int _x, int _y)
    {
        pos_x += _x;
        pos_y += _y;
    }
    /**
         * Permet de retourner le nom du joueur.
         * 
         * @return
         *            La fonction va retourner le nom associé au joueur sous forme de String.
         */
    public String getName()
    {
        return name;
    }
    public int getNum()
    {
        return num;
    }
    /**
         * Permet de retourner le nombre de barrières utilisées par le joueur.
         * 
         * @return 
         *            Nombre de barrières utiliséés sous forme de int.
         */
    public int getUsedFences()
    {
        return nbUsedFences;
    }
    public void setUsedFences()
    {
        nbUsedFences++;
    }
    public int getPos_x()
    {
        return pos_x;
    }
    public int getPos_y()
    {
        return pos_y;
    }
    public void setPos_x(int x)
    {
        pos_x=x;
    }
    public void setPos_y(int y)
    {
        pos_y=y;
    }
}