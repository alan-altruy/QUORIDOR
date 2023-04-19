package be.ac.umons.student.fouretaltruy.quoridor;

import javax.lang.model.util.ElementScanner6;

// ALTRUY ALAN - JASON FOURET // Creation du plateau de jeu
public class QuoridorTray
{
	private final String[]chars= {"●","|","─","■"};
	private QuoridorCell[][] cells;
	public QuoridorTray()
	{
		cells= new QuoridorCell[19][19];
		cells= new QuoridorCell[19][19];
		for (int x=0; x<19;x++)
		{
			for (int y=0; y<19;y++)
			{
				if (x==0 || x==18 || y==0 || y==18)
				{
					cells[x][y]= new QuoridorCell(x, y, 4);
				}
				else
				{
					cells[x][y]= new QuoridorCell(x,y,0);
				}
			}
		}

	}
	public void AddFence(int pos_x, int pos_y, int dir)
	{
		if (dir==0)
		{
			for (int _pos_x=pos_x; _pos_x<=pos_x+2; _pos_x++)
			{
				cells[_pos_x][pos_y].ChangeType(2);
			}
		}
		else if (dir==1)
		{
			for (int _pos_y=pos_y; _pos_y<=pos_y+2; _pos_y++)
			{
				cells[pos_x][_pos_y].ChangeType(3);
			}
		}
	}
	public void ChangeType_inCell(int x, int y, int type)
	{
		cells[x][y].ChangeType(type);
	}
	public boolean VerifAlreadyFenceAndWall(int pos_x, int pos_y, int dir)
	{
		for (int curs=0; curs<=2; curs++)
		{
			int verif0=cells[pos_x+curs][pos_y].GetType();
			int verif1=cells[pos_x][pos_y+curs].GetType();
			if ((dir==0) && verif0==2 || verif0==3 || verif0==4)
			{
				return true;
			}
			else if ((dir==0) && verif1==2 || verif1==3 || verif1==4)
			{
				return true;
			}
		}
		return false;
	}
	public void show()
	{
		String TrayBoard="";
		for (int ligne=0; ligne<19;ligne++)
		{
			for (int cols=0; cols<19; cols++)
			{
				int typeOfCell=cells[ligne][cols].GetType();
				if (typeOfCell!=0)
				{
					TrayBoard+= chars[typeOfCell-1];
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