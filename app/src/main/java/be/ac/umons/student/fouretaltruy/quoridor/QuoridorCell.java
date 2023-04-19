package be.ac.umons.student.fouretaltruy.quoridor;// ALTRUY ALAN - JASON FOURET //
import java.io.*;
public class QuoridorCell implements Serializable
{
    private static final long serialVersionUID = 1L;
    private int type;
    /**
         * Permet d'initialiser une cellule du plateau
         * 
         * @param _type
         *            Le type d'objet se trouvant dans la cellule:
         *              si <type>=0   --> rien ne se trouve dans cette cellule
         *              si <type>=1-2 --> un joueur(<player>) se trouve dans cette cellule
         *              si <type>=3   --> une barrière verticale(fence) se trouve dans cette cellule
         *              si <type>=4   --> une barrière horizontale(fence) se trouve dans cette cellule
         *              si <type>=5   --> un mur(wall) se trouve dans cette cellule
         */
    public QuoridorCell(int _type)
    {
        type=_type;
    }
    /**
         * Permet de modifier le type d'une cellule
         * 
         * @param _type
         *            Le nouveau  type d'objet qui se trouvera dans la cellule:
         */
    public void ChangeType(int _type)
    {
        type= _type;
    }
    /**
         * Permet de retourner le type de l'objet se trouvant dans cette cellule
         * 
         * @return
         *            Le nouveau  type d'objet qui se trouvera dans la cellule:
         */
    public int GetType()
    {
        return type;
    }
}
