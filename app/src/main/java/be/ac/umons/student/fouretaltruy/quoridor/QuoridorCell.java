package be.ac.umons.student.fouretaltruy.quoridor;
public class QuoridorCell
{
    public String type;
    public int pos_x, pos_y;
    public QuoridorCell(int _pos_x, int _pos_y, String _type)
    {
        type=_type;
        pos_x=_pos_x;
        pos_y=_pos_y;
    }
    public void ChangeType(String _type)
    {
        type=_type;
    }
    public boolean CanMove(int player_x, int player_y)
    {
        return true;
    }
}