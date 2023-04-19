package be.ac.umons.student.fouretaltruy.quoridor;

// ALTRUY ALAN - JASON FOURET // Creation du plateau de jeu
public class QuoridorTray
{
	private QuoridorCell[][] cells;
	public QuoridorTray()
	{
		cells= new QuoridorCell[10][10];
		for (int x=0; x<10;x++)
		{
			for (int y=0; y<10;y++)
			{
				cells[x][y]= new QuoridorCell(x,y,false, false);
			}
		}
	}
	public void ChangePlayer_inCell(int x, int y, boolean type)
	{
		cells[x][y].ChangePlayer_in(type);
	}
	public void ChangeFence_inCell(int x, int y, boolean type)
	{
		cells[x][y].ChangeFence_in(type);
	}
	public void show()
	{
		String TrayBoard="";
		for (int ligne=0; ligne<10;ligne++)
		{
			for (int cols1=0; cols1<10; cols1++)
			{
				if (cells[ligne][cols1].GetFence_in())
				{
					TrayBoard+= " | ";
				}
				else {TrayBoard+= "  ";}
			}
			TrayBoard+="\n";
			for (int cols=0; cols<9; cols++)
			{
				if (cells[ligne][cols].GetPlayer_in())
				{
					TrayBoard+= " O ";
				}
				else
				{
					TrayBoard+= "  ";
				}
			}
			TrayBoard+="\n";
		}
		System.out.println(TrayBoard);
	}
}
