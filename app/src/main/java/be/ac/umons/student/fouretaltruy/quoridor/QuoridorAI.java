package be.ac.umons.student.fouretaltruy.quoridor;// ALTRUY ALAN - JASON FOURET //

/**
 * L'intelligence artificielle du Quoridor qui hérite de QuoridorPlayers
 */
public class QuoridorAI extends QuoridorPlayers
{
    private static final long serialVersionUID = 1L;
    /**
         * Difficulté de l'intelligence artificielle
         */
    private boolean hard;
    /**
         * Numéro de l'autre joueur
         */
    private int numOfOtherPlayer;
    /**
         * Le jeu dans lequel l'intelligence artificielle joue
         */
    private QuoridorGame game;
    /**
         * Initiliase une intelligence artificielle du Quoridor
         * <ul><li>Initialise un joueur dans la classe mère (QuoridorPlayers)</li>
         * <li>Enregistre les informations nécessaires (jeu, plateau, difficulté)</li></ul>
         * @param _game : Le jeu dans lequel se trouve l'IA
         * @param _num : Le numéro de l'IA
         * @param _name : Le nom de l'IA
         * @param _pos_x : La position x de l'IA sur le plateau
         * @param _pos_y : La position y de l'IA sur le plateau
         * @param _hard : La difficulté de l'IA
         */
    public QuoridorAI(QuoridorGame _game, int _num, String _name, int _pos_x, int _pos_y, boolean _hard)
    {
        super(_game.getTray(), _num, _name, _pos_x, _pos_y);
        setAi(true);
        game=_game;
        hard=_hard;
        if (_num==0)
        {
            numOfOtherPlayer=1;
        }
        else
        {
            numOfOtherPlayer=0;
        }
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
    }
    /**
         * L'action de l'intelligence artificielle en difficulté normale
         * @return True si l'action s'est bien passée
         * <li>False sinon</li>
         */
    public boolean actionEasy()
    {
        return false;
    }
    /**
         * L'action de l'intelligence artificielle en difficulté difficile
         * @return True si l'action s'est bien passée
         * <li>False sinon</li>
         */
    public boolean actionHard()
    {
        int[] move= new QuoridorPathFind(game).moveSmallPath(getNum());
        QuoridorFence fence= canSetAIFence();
        if (fence!=null && move[0]!=1+16*numOfOtherPlayer)
        {
            fence.newFence();
            return true;
        }
        setPos(move[0], move[1]);
        return true;
    }
    /**
         * Permet de savoir si l'intelligence artificielle en difficulté normale peut poser une barrière
         * @return La barrière qui a été posée
         * <li> Si aucune barrière n'a été posée, return null</li>
         */
    public QuoridorFence canSetAIFence()
    {
        QuoridorFence fence, finalFence=null;
        int nbOfMoves= (new QuoridorPathFind(game).moveSmallPath(numOfOtherPlayer)[2])-(new QuoridorPathFind(game).moveSmallPath(getNum())[2]);
        int verif=nbOfMoves;
        for (int x=1; x<19; x++)
        {
            for (int y=1; y<19; y++)
            {
                for (int dir=0; dir<2; dir++)
                {
                    fence= new QuoridorFence(game, x, y, dir);
                    if (verifFence(fence)>verif)
                    {
                        if (verifFence(fence)!=100)
                        {
                            verif = verifFence(fence);
                            finalFence = new QuoridorFence(game, x, y, dir);
                        }
                    }
                }
            }
        }
        int[] movePlayer= new QuoridorPathFind(game).moveSmallPath(getNum());
        int[] moveOther= new QuoridorPathFind(game).moveSmallPath(numOfOtherPlayer);
        int fencePlayer=getUsedFences(), fenceOther=game.getPlayer(numOfOtherPlayer).getUsedFences();
        if (verif-nbOfMoves>3 && fencePlayer<=fenceOther)
        {
            return finalFence;
        }
        if ((movePlayer[2]>moveOther[2] && fencePlayer<fenceOther) || (moveOther[2]<=movePlayer[2] && moveOther[2]<3))
        {
            return finalFence;
        }
        if (fencePlayer+2<fenceOther)
        {
            return finalFence;
        }
        return null;
    }
    /**
         * Permet de savoir s'il est utile de poser une barrière
         * @param fence : La barrière
         * @return L'écart entre le nombre de déplacement de l'adversaire et de l'IA
         */
    public int verifFence(QuoridorFence fence)
    {
        if (fence.canSetFence())
        {
            int[] moveOtherPlayerBefore= new QuoridorPathFind(game).moveSmallPath(numOfOtherPlayer);
            int[] movePlayerBefore= new QuoridorPathFind(game).moveSmallPath(getNum());
            fence.set();
            int[] moveOtherPlayerAfter= new QuoridorPathFind(game).moveSmallPath(numOfOtherPlayer);
            int[] movePlayerAfter= new QuoridorPathFind(game).moveSmallPath(getNum());
            fence.remove();
            if (moveOtherPlayerBefore[2]-movePlayerBefore[2]<moveOtherPlayerAfter[2]-movePlayerAfter[2])
            {
                return moveOtherPlayerAfter[2]-movePlayerAfter[2];
            }
        }
        return 100;
    }
}