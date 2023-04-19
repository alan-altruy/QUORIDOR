package be.ac.umons.student.fouretaltruy.quoridor;

import javax.lang.model.util.ElementScanner6;

// ALTRUY ALAN - JASON FOURET // Creation du plateau de jeu
public class QuoridorTray
{
	private QuoridorCell[][] cells;
	private QuoridorWall[][] walls;
	public QuoridorTray()
	{
		cells= new QuoridorCell[19][19];
		for (int x=0; x<19;x++)
		{
			for (int y=0; y<19;y++)
			{
				if (x==0 || x==18 || y==0 || y==18)
				{
					cells[x][y]= new QuoridorCell(x, y, false, false, true);
				}
				else
				{
					cells[x][y]= new QuoridorCell(x,y,false, false, false);
				}
			}
		}

	}
	public void AddFence(int pos_x, int pos_y, int dir)
	{
		if (dir==1)
		{
			cells[pos_x][pos_y].ChangeFence_in(true);
			cells[pos_x+1][pos_y].ChangeFence_in(true);
			cells[pos_x+2][pos_y].ChangeFence_in(true);
		}
		else if (dir==2)
		{
			cells[pos_x][pos_y].ChangeFence_in(true);
			cells[pos_x][pos_y+1].ChangeFence_in(true);
			cells[pos_x][pos_y+2].ChangeFence_in(true);
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
		for (int ligne=0; ligne<19;ligne++)
		{
			for (int cols=0; cols<19; cols++)
			{
				if (cells[ligne][cols].GetFence_in())
				{
					TrayBoard+= "|";
				}
				else if (cells[ligne][cols].GetWall_in())
				{
					TrayBoard+= "■";
				}
				else if (cells[ligne][cols].GetPlayer_in())
				{
					TrayBoard+= "●";
				}
				else if (cols%2==0 || ligne%2==0)
				{
					TrayBoard+="□";
				}
				else
				{
					TrayBoard+="◌";
				}
				TrayBoard+=" ";
			}
			TrayBoard+="\n";
		}
		System.out.println(TrayBoard);
	}
}
