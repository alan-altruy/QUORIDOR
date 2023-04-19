package be.ac.umons.student.fouretaltruy.quoridor;

/// ALTRUY ALAN - JASON FOURET //
public class QuoridorAI extends QuoridorPlayers
{
    private static final long serialVersionUID = 1L;
    private boolean hard;
    private QuoridorGame game;
    private QuoridorTray tray;
    public QuoridorAI(QuoridorGame _game, int _num, String _name, int _x, int _y, boolean _hard)
    {
        super(_game.getTray(), _num, _name, _x, _y);
        game=_game;
        tray=game.getTray();
        hard=_hard;
    }
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
    public void actionEasy()
    {
        int rand= (int)(Math.random()*(3-0));
        if (rand==1 && canPosAIFence());
        else
        {
            new QuoridorPathFind(game).verif2();
        }
    }
    public void actionHard()
    {}
    public boolean canPosAIFence()
    {
        QuoridorPlayers humanPlayer= game.getPlayer(0);
        QuoridorFence fence= game.getFence();
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
    public void setHard()
    {
        hard=true;
    }
}