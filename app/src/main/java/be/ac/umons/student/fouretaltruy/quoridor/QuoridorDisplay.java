package be.ac.umons.student.fouretaltruy.quoridor;// Initialement prévu pour afficher Quoridor dans le Terminal

public class QuoridorDisplay
{
    private QuoridorTray tray;
    /**
         * Permet d'initialiser l'afficheur.
         * 
         * @param _tray
         *            Une instance de QuoridorTray qui contient le plateau du jeu.
         */
    public QuoridorDisplay(QuoridorTray _tray)
    {
        tray=_tray;
    }
    /**
         * Met à jour le plateau du jeu, lance la méthode permettant d'afficher le plateau.
         * 
         * @param _tray
         *            Une instance de QuoridorTray contenant le nouveau plateau maj du jeu.
         */
    public void showDisplay(QuoridorTray _tray)
    {
        tray=_tray;
        tray.show();
    }
}
