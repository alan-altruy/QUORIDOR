package be.ac.umons.student.fouretaltruy.quoridor;
public class QuoridorPlayers
{
    private String name;
    public final String type="player";
    private int NumOfPlayer, pos_x, pos_y;
    private QuoridorFence[] fences;
    public QuoridorPlayers(int _num, String _name, int _x, int _y)
    {
        fences= new QuoridorFence[10];
        name=_name;
        pos_x=_x;
        pos_y=_y;
        NumOfPlayer=_num;
        InitFencesOfPlayer();
    }
    public void InitFencesOfPlayer()
    {
        fences=new QuoridorFence[10];
        int fence_x=3, fence_y=0;
        switch (NumOfPlayer)
        {
            case 2:
                fence_y=24;
                break;
        }
        for (int NbFence=0; NbFence<10; NbFence++)
        {
            fences[NbFence] = new QuoridorFence((fence_x+NbFence*2), fence_y);
        }
    }
    public void MovePlayer (int _x, int _y)
    {
        int new_x, new_y;
        new_x = pos_x + _x;
        new_y = pos_y + _y;
    }
}