package be.ac.umons.student.fouretaltruy.quoridor;

public class QuoridorPlayers
{
    private String name;
    private int NbUsedFences=0;
    public final String type="player";
    private int NumOfPlayer, pos_x, pos_y;
    public QuoridorPlayers(int _num, String _name, int _x, int _y)
    {
        name=_name;
        pos_x=_x;
        pos_y=_y;
        NumOfPlayer=_num;
    }
    public void MovePlayer (int _x, int _y)
    {
        int new_x, new_y;
        new_x = pos_x + _x;
        new_y = pos_y + _y;
    }
    public String GetName()
    {
        return name;
    }
    public int GetUsedFences()
    {
        return NbUsedFences;
    }
}