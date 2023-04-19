package be.ac.umons.student.fouretaltruy.quoridor;// ALTRUY ALAN - JASON FOURET //

/**
 * L'intelligence artificielle du Quoridor qui hérite de QuoridorPlayers
 */
public class QuoridorAI extends QuoridorPlayers
{
    private static final long serialVersionUID = 1L;
    /**
         * difficulté de l'intelligence artificielle
         */
    private boolean hard;
    /**
         * Le jeu dans lequel l'intelligence artificielle joue
         */
    private QuoridorGame game;
    /**
         * Le plateau sur lequel l'intelligence artificielle joue
         */
    private QuoridorTray tray;
    /**
         * Initiliase une intelligence artificielle du Quoridor
         * <ul><li>Initialise un joueur dans la classe mère</li>
         * <li>Enregistre les informations nécessaires (jeu, plateau, difficulté)</li></ul>
         * @param _game : Un jeu dans 
         * @param _num : Le numéro de l'IA
         * @param _name : Le nom de l'IA
         * @param _pos_x : La position x de l'IA sur le plateau
         * @param _pos_y : La potion y de l'IA sur le plateau
         * @param _hard : La difficulté de l'IA
         */
    public QuoridorAI(QuoridorGame _game, int _num, String _name, int _pos_x, int _pos_y, boolean _hard)
    {
        super(_game.getTray(), _num, _name, _pos_x, _pos_y);
        game=_game;
        tray=game.getTray();
        hard=_hard;
    }
    /**
         * L'action de l'intelligence artificielle
         */
    public void action()
    {
        if (hard)
        {
            actionHard();
        }
        else
        {
            actionEasy();
        }
        try {
            Thread.sleep(500);
        } catch (Exception e) {}
    }
    /**
         * L'action de l'intelligence artificielle en difficulté normale
         */
    public void actionEasy()
    {
        int rand= (int)(Math.random()*(3-0));
        if (rand==1 && canSetEasyAIFence());
        else
        {
            new QuoridorPathFind(game).moveSmallPath();
        }
    }
    /**
         * L'action de l'intelligence artificielle en difficulté difficile
         */
    public void actionHard()
    {

    }
    /**
         * Permet de savoir si l'intelligence artificielle en difficulté normale peut poser une barrière
         * @return True si la barrière a été posée
         * <li>False sinon</li>
         */
    public boolean canSetEasyAIFence()
    {
        QuoridorPlayers humanPlayer= game.getPlayer(0);
        QuoridorFence fence= new QuoridorFence(game);
        int pos_x=humanPlayer.getPos_x();
        int pos_y=humanPlayer.getPos_y();
        if (fence.newFence(pos_x+1, pos_y, 1))
        {
            return true;
        }
        else if (fence.newFence(pos_x+1, pos_y-2, 1))
        {
            return true;
        }
        else if (tray.getTypeOfCell(pos_x+1, pos_y-1)==0)
        {
            if (fence.newFence(pos_x+1, pos_y-4, 1))
            {
                return true;
            }
        }
        else if (tray.getTypeOfCell(pos_x+1, pos_y+1)==0)
        {
            if (fence.newFence(pos_x+1, pos_y+2, 1))
            {
                return true;
            }
        }
        return false;
    }
}