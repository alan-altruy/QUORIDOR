package be.ac.umons.student.fouretaltruy.quoridor;// ALTRUY ALAN - JASON FOURET //
import java.io.*;
public class QuoridorTray implements Serializable
{
	private static final long serialVersionUID = 1L;
	private final String[]chars = {"●","|","─","■"};
	public QuoridorCell[][] cells;
	/**
         * Permet d'initialiser le plateau du jeu.
         * 
         */
	public QuoridorTray()
	{
		cells= new QuoridorCell[19][19];
		InitCells_Walls_Nothing();
	}
	/**
         * Permet d'initialiser les cellules du plateau élémentaires (cellules vides et murs extérieurs).
         * 
         */
	public void InitCells_Walls_Nothing()
	{
		for (int x=0; x<19;x++)
		{
			for (int y=0; y<19;y++)
			{
				if (x==0 || x==18 || y==0 || y==18)
				{
					cells[x][y]= new QuoridorCell(5);
				}
				else
				{
					cells[x][y]= new QuoridorCell(0);
				}
			}
		}
	}
	/**
         * Permet d'ajouter une barrière au plateau.
         * 
         * @param pos_x
         *            La position x de l'endroit désiré pour positionner la barrière.
         * @param pos_y
         *            La position y de l'endroit désiré pour positionner la barrière.
         * @param dir
         *            La direction de la barrière désirée (0= vertical , 1= horizontal).
         */
	public void AddFence(int pos_x, int pos_y, int dir)
	{
		if (dir==0)
		{
			for (int _pos_x=0; _pos_x<=2; _pos_x++)
			{
				if (_pos_x==0)
				{
					cells[pos_x+_pos_x][pos_y].ChangeType(3);
				}
				else
				{
					cells[pos_x+_pos_x][pos_y].ChangeType(10);
				}
			}
		}
		else if (dir==1)
		{
			for (int _pos_y=0; _pos_y<=2; _pos_y++)
			{
				if (_pos_y==0)
				{
					cells[pos_x][pos_y+_pos_y].ChangeType(4);
				}
				else
				{
					cells[pos_x][pos_y+_pos_y].ChangeType(10);
				}
			}
		}
	}
	/**
         * Permet de changer le type d'une cellule d'instance QuoridorCell
         * 
         * @param x
         *            La position x de la cellule.
         * @param y
         *            La position y de la cellule.
         * @param type
         *            La nouveau type que l'on veut attribuer à la cellule
         */
	public void ChangeType_inCell(int x, int y, int type)
	{
		cells[x][y].ChangeType(type);
	}
	/**
         * Permet de retourner une verifiation permettant de dire si un mur ou une barrière
		 * se situe déjà dans les cellules où il souhaite se placer.
         * 
         * @param pos_x
         *            La position x de l'endroit désiré pour positionner la barrière.
         * @param pos_y
         *            La position y de l'endroit désiré pour positionner la barrière.
         * @param dir
         *            La direction de la barrière désirée (0= vertical , 1= horizontal).
		 * 
		 * @return
		 * 			  La méthode va retourner true si une barrière ou un mur se trouve déjà à cet emplacement
		 *            et retournera false dans le cas contraire.
         */
	public boolean VerifAlreadyFenceAndWall(int pos_x, int pos_y, int dir)
	{
		for (int curs=0; curs<=2; curs++)
		{
			int verif0=cells[pos_x+curs][pos_y].GetType();
			int verif1=cells[pos_x][pos_y+curs].GetType();
			if (dir==0 && (verif0!=0))
			{
				return true;
			}
			else if (dir==1 && (verif1!=0))
			{
				return true;
			}
		}
		return false;
	}
	public int GetTypeIn_Cell(int pos_x, int pos_y)
	{
		return cells[pos_x][pos_y].GetType();
	}
	/*	*
         * Permet d'afficher le plateau
         * 
         */
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