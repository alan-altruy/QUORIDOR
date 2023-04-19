// ALTRUY ALAN - JASON FOURET // Affichage du plateau de jeu
package be.ac.umons.student.fouretaltruy.quoridor;
public class QuoridorTray
{
	private QuoridorCell[][] cells;
	public boolean[][] tray = new boolean[27][27];
	public void InitTray()
	{
		for (int x=4; x<21;x+=2)
		{
			for (int y=4; y<21;y+=2)
			{
				cells[x][y]= new QuoridorCell(x,y,"NotAllowed");
			}
		}
	}
	public void ChangeTypeCell(int x, int y, String type)
	{
		cells[x][y].ChangeType(type);
	}
	public void show()
	{
		String str="";
		for (int ligne=0; ligne<17;ligne++)
		{
			for (int cols=0; cols<17; cols++)
			{
				if ((cols%2)!=0 || (ligne%2)!=0)
				{
					if (this.tray[ligne][cols]==false)
					{
						str+=" | ";
					}
					else
					{
						str+=" I ";
					}
				}
				else
				{
					if (this.tray[ligne][cols]==false)
					{
						str+=" # ";
					}
					else
					{
						str+=" O ";
					}
				}
			}
			str+="\n";
		}
		System.out.println(str);
	}
}