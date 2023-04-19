package be.ac.umons.student.fouretaltruy.quoridor;

// ALTRUY ALAN - JASON FOURET //
public class QuoridorVerifFence
{
    private int pos_x, pos_y;
    private QuoridorTray tray;
    /**
         * Permet de verifier s'il est possible d'initialiser une barrière à l'emplacement choisi
         * 
         * @param _pos_x
         *            Le type d'objet se trouvant dans la cellule:
         * @param _pos_y
         *            Le type d'objet se trouvant dans la cellule:
         * @param _dir
         *            Le type d'objet se trouvant dans la cellule:           
         */
    public QuoridorVerifFence(int _pos_x, int _pos_y, int _dir)
    {
        pos_x=_pos_x;
        pos_y=_pos_y;
    }
    public boolean VerifFence(int pos_x, int pos_y, int dir)
    {
        boolean verif=true;
        if (pos_x<1 || pos_x>17 || pos_y<1 || pos_y>17)
        {
            verif=false;
        }
        else if ((pos_x%2==1 && (dir==1 || pos_y%2==1)) || tray.VerifAlreadyFenceAndWall(pos_x, pos_y, dir))
        {
            verif=false;
        }
        return verif;
    }
}