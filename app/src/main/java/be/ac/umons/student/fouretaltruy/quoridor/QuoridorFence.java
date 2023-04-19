package be.ac.umons.student.fouretaltruy.quoridor;

public class QuoridorFence
{
    public final int length=3;
    private int pos_x, pos_y, direction;
    public final String type="fence";
    
    public QuoridorFence(int _pos_x, int _pos_y, int _direction)
    {
        pos_x=_pos_x;
        pos_y=_pos_y;
        direction=_direction;
    }

}
