package be.ac.umons.student.fouretaltruy.quoridor;

public class QuoridorCell
{
    public boolean player_in, fence_in;
    public int pos_x, pos_y;
    public QuoridorCell(int _pos_x, int _pos_y, boolean _player_in, boolean _fence_in)
    {
        player_in=_player_in;
        fence_in=_fence_in;
        pos_x=_pos_x;
        pos_y=_pos_y;
    }
    public void ChangePlayer_in(boolean _type)
    {
        player_in= _type;
    }
    public void ChangeFence_in(boolean _type)
    {
        fence_in= _type;
    }
    /*public boolean CanMove(int player_x, int player_y)
    {

    }*/
    public boolean GetPlayer_in()
    {
		return player_in;
	}
	public boolean GetFence_in()
    {
		return fence_in;
	}
}
