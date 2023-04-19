package be.ac.umons.student.fouretaltruy.quoridor;

public class QuoridorWall
{
	private int pos_x, pos_y;
	public final String type="wall";
	private char direction;
	public QuoridorWall(int _pos_x, int _pos_y, char _direction)
	{
		pos_x=_pos_x;
		pos_y=_pos_y;
		direction=_direction;
	}
}
