package be.ac.umons.student.fouretaltruy.quoridor;

// ALTRUY ALAN - JASON FOURET //
public class QuoridorFence
{
    private int pos_x, pos_y;
    /**
         * Permet d'initialiser une barrière
         * 
         * @param _pos_x
         *            Le type d'objet se trouvant dans la cellule:
         * @param _pos_y
         *            Le type d'objet se trouvant dans la cellule:            
         */
    public QuoridorFence(int _pos_x, int _pos_y)
    {
        pos_x=_pos_x;
        pos_y=_pos_y;
    }
}